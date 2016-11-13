package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.RoyalNinja.EngraveMMO.ParticleEffect;
import com.RoyalNinja.EngraveMMO.SettingsManager;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class OrcAbilities extends BukkitRunnable {

	SettingsManager settings = SettingsManager.getInstance();
	
	@Override
	public void run() {
		
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			if (e instanceof Zombie) {
				
				Zombie orc = (Zombie) e;
				
				if (settings.getMobData().getConfigurationSection(orc.getUniqueId().toString()) != null) {
					
					if (!(settings.getMobData().getString(orc.getUniqueId().toString() + ".Race").equals("Orc"))) return;
					
					if (orc.getTarget() instanceof Player) {
						
						Integer orclevel = settings.getMobData().getInt(orc.getUniqueId().toString() + ".Level");
						
						Random r = new Random();
						

						if (r.nextInt(100) <= orclevel/2) {
							
							//Supersmash
							ParticleEffect particleEffect = new ParticleEffect();
							
							for (Player p : Bukkit.getServer().getOnlinePlayers()) {
								particleEffect.sendParticlePacket((CraftPlayer) p, EnumParticle.EXPLOSION_LARGE, orc.getLocation(), (float) 0, (float) 0, (float) 0, (float) 2, 1);	
							}
							
							for (Entity near : orc.getNearbyEntities(5, 5, 5)) {
								if (near instanceof Player) {
									LivingEntity livingnear = (LivingEntity) near;
									livingnear.damage(orclevel*5);

								       
							        Location orcLocation = orc.getEyeLocation();
							        Location playerThrowLocation = livingnear.getEyeLocation();
							       
							        double x = playerThrowLocation.getX() - orcLocation.getX();
							        double y = playerThrowLocation.getY() - orcLocation.getY();
							        double z = playerThrowLocation.getZ() - orcLocation.getZ();
							       
							        Vector throwVector = new Vector(x,y,z);
							       
							        throwVector.normalize();
							        throwVector.multiply(1.0D);
							        throwVector.setY(1.0D);
							       
							        livingnear.setVelocity(throwVector);
								}
							}
							
						}
						
						if (r.nextInt(100) <= orclevel/2) {
							double pitch = ((orc.getLocation().getPitch() + 90) * Math.PI) / 180;
							double yaw  = ((orc.getLocation().getYaw() + 90)  * Math.PI) / 180;
							double x = Math.sin(pitch) * Math.cos(yaw);
							double y = Math.sin(pitch) * Math.sin(yaw);
							double z = Math.cos(pitch);
							Vector vector = new Vector(x, z, y);
							orc.setVelocity(vector.multiply(1.2));
						}
						
						
					}
				}
	
			}
		}
		
	}
	
	

}
