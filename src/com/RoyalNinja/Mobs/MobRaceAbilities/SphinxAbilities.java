package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.MobHandler;
import com.RoyalNinja.EngraveMMO.ParticleEffect;
import com.RoyalNinja.EngraveMMO.SettingsManager;
import com.RoyalNinja.EngraveMMO.MobHandler.MobModifier;
import com.RoyalNinja.EngraveMMO.MobHandler.MobRace;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class SphinxAbilities extends BukkitRunnable implements Listener {

	SettingsManager settings = SettingsManager.getInstance();
	
	public void run() {
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(e.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getUniqueId().toString() + ".Race").equals("Sphinx"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				if (e instanceof PigZombie) {
					PigZombie ent = (PigZombie) e;
					if (ent.getTarget() instanceof Player) {
						if (r.nextInt(100) <= mobLevel/2) {
							
							for (Block b : new com.RoyalNinja.EngraveMMO.Line(ent.getEyeLocation(), ent.getTarget().getEyeLocation())) {
								
								ParticleEffect particle = new ParticleEffect();
								
								for (Player p : Bukkit.getServer().getOnlinePlayers()) {
									particle.sendParticlePacket((CraftPlayer)p, EnumParticle.FLAME, b.getLocation(), (float)0, (float)0, (float)0, (float)0.1, 50);
								}
								
								ent.getTarget().damage(mobLevel*50);
								ent.getTarget().setFireTicks(100);
								
							}
							
						}
						if (r.nextInt(100) <= mobLevel/3) {
							
								
								ParticleEffect particle = new ParticleEffect();
								
								for (Player p : Bukkit.getServer().getOnlinePlayers()) {
									particle.sendParticlePacket((CraftPlayer)p, EnumParticle.SMOKE_NORMAL, ent.getLocation().add(0, 1, 0), (float)0, (float)0, (float)0, (float)1, 50);
								}
								
								ent.getTarget().damage(mobLevel*50);
								ent.getTarget().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
								
						}
							
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onSphinxAbilities(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof PigZombie) {
			
			if (settings.getMobData().getConfigurationSection(e.getEntity().getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getEntity().getUniqueId().toString() + ".Race").equals("Sphinx"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getEntity().getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				if (r.nextInt(100) <= mobLevel/4) {
					MobHandler mh = new MobHandler();
					mh.spawnMonster(e.getEntity().getLocation(), MobRace.PIXIE, MobModifier.GODLY, mobLevel/2);
				}
				
			}
		}
	}
	
	

}
