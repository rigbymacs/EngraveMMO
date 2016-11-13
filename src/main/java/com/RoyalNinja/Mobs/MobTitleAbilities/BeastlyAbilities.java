package com.RoyalNinja.Mobs.MobTitleAbilities;

import java.util.Random; 

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.RoyalNinja.EngraveMMO.SettingsManager;

public class BeastlyAbilities extends BukkitRunnable {
	
	SettingsManager settings = SettingsManager.getInstance();

	public void run() {
		
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(e.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getUniqueId().toString() + ".Modifier").equals("Beastly"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				LivingEntity ent = (LivingEntity) e;
				
					if (((Creature) ent).getTarget() instanceof Player) {
						if (r.nextInt(100) <= mobLevel/2) {
							double pitch = ((ent.getLocation().getPitch() + 90) * Math.PI) / 180;
							double yaw  = ((ent.getLocation().getYaw() + 90)  * Math.PI) / 180;
							double x = Math.sin(pitch) * Math.cos(yaw);
							double y = Math.sin(pitch) * Math.sin(yaw);
							double z = Math.cos(pitch);
							Vector vector = new Vector(x, z, y);
							ent.setVelocity(vector.multiply(2));
						}
					}
				
			}
			
			
		}
		
		
		
	}

	

}
