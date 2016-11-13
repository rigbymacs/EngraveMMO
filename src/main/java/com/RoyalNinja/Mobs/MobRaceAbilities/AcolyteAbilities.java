package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random; 

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.SettingsManager;

public class AcolyteAbilities extends BukkitRunnable implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void run() {

		for (Entity ent : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(ent.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(ent.getUniqueId().toString() + ".Race").equals("Acolyte"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(ent.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
					if (((Creature) ent).getTarget() instanceof Player) {
						if (r.nextInt(100) <= mobLevel) {
							Integer MobDamageMult=1200;
							Integer randomDivisor = r.nextInt(3) + 2;
							
							((Creature) ent).getTarget().damage((MobDamageMult*mobLevel)/randomDivisor);
						}
					}
				
			}
			
			
		}
	}
	
	@EventHandler
	public void onAcolyteAbilities(EntityDamageByEntityEvent e) {
		
			if (!(e.getDamager() instanceof Player)) return;
			if (settings.getMobData().getConfigurationSection(e.getEntity().getUniqueId().toString()) != null) {
				if (settings.getMobData().getString(e.getEntity().getUniqueId().toString() + ".Race").equals("Acolyte")) {
					Random r = new Random();
				
					Integer level = settings.getMobData().getInt(e.getEntity().getUniqueId().toString() + ".Level");
				
					if (r.nextInt(100) <= level/2) {
						if (!e.getDamager().isDead()) {
							LivingEntity mob = (LivingEntity) e.getDamager();
						
							mob.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
						}
					}
				}
			}
		
	}
	

}
