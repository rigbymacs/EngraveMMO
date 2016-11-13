package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random; 

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.ParticleEffect;
import com.RoyalNinja.EngraveMMO.SettingsManager;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class PixieAbilities extends BukkitRunnable {

	SettingsManager settings = SettingsManager.getInstance();
	
	public void run() {
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(e.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getUniqueId().toString() + ".Race").equals("Pixie"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				if (e instanceof Endermite) {
					Endermite ent = (Endermite) e;
					if (ent.getTarget() instanceof Player) {
						if (r.nextInt(100) <= mobLevel/2) {
							
							for (Block b : new com.RoyalNinja.EngraveMMO.Line(ent.getEyeLocation(), ent.getTarget().getEyeLocation())) {
								
								ParticleEffect particle = new ParticleEffect();
								
								for (Player p : Bukkit.getServer().getOnlinePlayers()) {
									particle.sendParticlePacket((CraftPlayer)p, EnumParticle.SMOKE_NORMAL, b.getLocation(), (float)0, (float)0, (float)0, (float)1, 50);
								}
								
								ent.getTarget().damage(mobLevel*5);
								ent.getTarget().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
								
							}
							
						}
						if (r.nextInt(100) <= mobLevel) {
							
							Integer chance = r.nextInt(2) + 1;
							
							if (chance == 1) {
								ent.teleport(ent.getLocation().add(0,4,0));
							}
							if (chance == 2) {
								ent.teleport(ent.getLocation().add(4,0,0));
							}
							if (chance == 3) {
								ent.teleport(ent.getLocation().add(0,0,4));
							}
							}
							
							
						}
					}
				}
				
			}
		}
}
