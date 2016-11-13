package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.ParticleEffect;
import com.RoyalNinja.EngraveMMO.SettingsManager;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class AeresAbilities extends BukkitRunnable {

	SettingsManager settings = SettingsManager.getInstance();
	
	public void run() {
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(e.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getUniqueId().toString() + ".Race").equals("Aeres"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				if (e instanceof Skeleton) {
					Skeleton ent = (Skeleton) e;
					if (ent.getTarget() instanceof Player) {
						if (r.nextInt(100) <= mobLevel/2) {
							
							for (Block b : new com.RoyalNinja.EngraveMMO.Line(ent.getEyeLocation(), ent.getTarget().getEyeLocation())) {
								
								ParticleEffect particle = new ParticleEffect();
								
								for (Player p : Bukkit.getServer().getOnlinePlayers()) {
									particle.sendParticlePacket((CraftPlayer)p, EnumParticle.FIREWORKS_SPARK, b.getLocation(), (float)0.1, (float)0.1, (float)0.1, (float)0.1, 50);
								}
								ent.getTarget().damage(mobLevel*5);
							}
							
						}
					}
				}
				
			}
		}
	}
}
