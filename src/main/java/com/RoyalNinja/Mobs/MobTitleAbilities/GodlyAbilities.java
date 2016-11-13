package com.RoyalNinja.Mobs.MobTitleAbilities;

import java.util.Random; 

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.SettingsManager;

public class GodlyAbilities extends BukkitRunnable implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();

	@EventHandler
	public void onGodlySmite(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			
			if (settings.getMobData().getConfigurationSection(e.getDamager().getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Modifier").equals("Godly"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getDamager().getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				if (r.nextInt(100) <= mobLevel/2) {
					
					((LivingEntity) e.getEntity()).damage(mobLevel*5);
					e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
					
				}
				
			}
			
		}
	}
	
	
	public void run() {
		
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(e.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getUniqueId().toString() + ".Modifier").equals("Godly"))) return;
				
				LivingEntity ent = (LivingEntity) e;
				
				if (((Creature) ent).getTarget() != null) {
					((Creature) ent).getTarget().teleport(ent);
				}
				
				
			}
			
			
		}
		
		
		
	}

	

}
