package com.RoyalNinja.LevelingSystem;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.RoyalNinja.Main;
import com.RoyalNinja.EngraveMMO.ActionBarUtil;
import com.RoyalNinja.EngraveMMO.SettingsManager;
import com.RoyalNinja.EngraveMMO.LevelingSystem.LevelingSystem;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

import net.md_5.bungee.api.ChatColor;

public class ExpGainMobs implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();

	public static HashMap<Hologram, Integer> life = new HashMap<Hologram, Integer>();
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		LivingEntity ent = (LivingEntity) e.getEntity();
		if (e.getDamager() instanceof Player) e.setDamage(ent.getMaxHealth()*2);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (settings.getMobData().getConfigurationSection(e.getEntity().getUniqueId().toString()) != null) {
			
			if (!(e.getEntity().getKiller() instanceof Player)) return;
			
			Player p = (Player) e.getEntity().getKiller();
			
			Entity ent = e.getEntity();
			
			Random r = new Random();
			
			Double mobXPMult=0.0;
			Double xpRandom = 2 + (5 - 2) * r.nextDouble();
			
			Integer mobLevel = settings.getMobData().getInt(ent.getUniqueId().toString() + ".Level");
			
			String race = settings.getMobData().getString(ent.getUniqueId().toString() + ".Race");
			
			if (race.equals("Orc")) mobXPMult=1.0;
			if (race.equals("Andeddo")) mobXPMult=1.5;
			if (race.equals("Pixie")) mobXPMult=2.0;
			if (race.equals("Giant")) mobXPMult=2.5;
			if (race.equals("Aeres")) mobXPMult=3.0;
			if (race.equals("Acolyte")) mobXPMult=3.5;
			if (race.equals("Hopeless")) mobXPMult=4.0;
			if (race.equals("Sphinx")) mobXPMult=4.5;
			
			Double expGain = (double) Math.round((mobXPMult*mobLevel)*xpRandom);
			
			LevelingSystem ls = new LevelingSystem();
			
			if (mobLevel - ls.getLevelFromPlayer(p) > 5) {
				ActionBarUtil.sendActionBar(p, ChatColor.GOLD + "+" + ChatColor.RED + "0 " + ChatColor.GOLD + "EXP");
				return;
			}
			
			ls.addPlayerXP(p, expGain);
			ActionBarUtil.sendActionBar(p, ChatColor.GOLD + "+" + ChatColor.AQUA + expGain + ChatColor.GOLD + " EXP");
			
			Hologram hologram = HologramsAPI.createHologram(Main.plugin, ent.getLocation().add(0, 1, 0));
			hologram.getVisibilityManager().setVisibleByDefault(false);
			hologram.getVisibilityManager().showTo(p);
			TextLine textLine = hologram.appendTextLine(ChatColor.GOLD + "+ " + ChatColor.AQUA + expGain + ChatColor.GOLD + " EXP");
			life.put(hologram, 4);
			
			ls.updatePlayerLevel(p);

			Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.plugin, new Runnable() {
				public void run() {
					for (Hologram g : life.keySet()) {
						if (g.isDeleted()) return;
						g.teleport(g.getLocation().subtract(0, 0.01, 0));
					}
				}
			}, 1L, 1L);
			Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.plugin, new Runnable() {
				public void run() {
					for (Hologram g : life.keySet()) {
						if (life.get(g) > 0) {
							life.put(g, life.get(g) - 1);
						}
						else if(life.get(g) <= 0) {
							if (g.isDeleted()) return;
							g.delete();
							life.remove(g);
						}
					}
				}
			}, 20L, 20L);
		}
	}

}
