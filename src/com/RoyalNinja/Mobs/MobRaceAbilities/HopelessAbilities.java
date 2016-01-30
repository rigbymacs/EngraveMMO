package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.ParticleEffect;
import com.RoyalNinja.EngraveMMO.SettingsManager;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class HopelessAbilities extends BukkitRunnable {

	SettingsManager settings = SettingsManager.getInstance();
	
	public void run() {
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(e.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getUniqueId().toString() + ".Race").equals("Hopeless"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				if (e instanceof Enderman) {
					Enderman ent = (Enderman) e;
					
					if (ent.getTarget() instanceof Player) {
						
						if (r.nextInt(100) <= mobLevel/2) {
							
							for (Block b : new com.RoyalNinja.EngraveMMO.Line(ent.getEyeLocation(), ent.getTarget().getEyeLocation())) {
								
								ParticleEffect particle = new ParticleEffect();
								
								for (Player p : Bukkit.getServer().getOnlinePlayers()) {
									particle.sendParticlePacket((CraftPlayer)p, EnumParticle.EXPLOSION_LARGE, b.getLocation(), (float)0, (float)0, (float)0, (float)0, 10);
								}
								ent.getTarget().damage(mobLevel*25);
							}
						}

						if (r.nextInt(100) <= mobLevel/2) {
							
							ent.getTarget().teleport(ent.getLocation());
							
						}
						if (r.nextInt(100) <= mobLevel/2) {
							
							ent.teleport(ent.getTarget().getLocation());
							
						}
						
					}
					
					
				}
				
			}
				
		}
	}
		
}
