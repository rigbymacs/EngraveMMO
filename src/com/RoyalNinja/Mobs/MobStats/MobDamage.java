package com.RoyalNinja.Mobs.MobStats;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.RoyalNinja.EngraveMMO.SettingsManager;

public class MobDamage implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onMobDamage(EntityDamageByEntityEvent e) {
		
		if (e.getDamager() instanceof Arrow) {
			
			Arrow a = (Arrow) e.getDamager();
			
			if (a.getShooter() instanceof LivingEntity) {
				if (settings.getMobData().getConfigurationSection(((LivingEntity)a.getShooter()).getUniqueId().toString()) != null) {
					
					Entity shooter = (Entity) a.getShooter();
					
					Integer MobDamageMult=0, MobLevel;
					
					MobLevel = settings.getMobData().getInt(shooter.getUniqueId().toString() + ".Level");
					
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Orc")) {
						MobDamageMult = 200;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Andeddo")) {
						MobDamageMult = 400;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Pixie")) {
						MobDamageMult = 700;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Giant")) {
						MobDamageMult = 900;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Aeres")) {
						MobDamageMult = 1000;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Acolyte")) {
						MobDamageMult = 1200;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Warlock")) {
						MobDamageMult = 1400;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Hopeless")) {
						MobDamageMult = 1600;
					}
					if (settings.getMobData().getString(shooter.getUniqueId().toString() + ".Race").equals("Sphinx")) {
						MobDamageMult = 1800;
					}
					
					Random r = new Random();
					Integer random = r.nextInt(24) + 1;
					
					e.setDamage((MobDamageMult*(MobLevel/50))*random);
					
				}
			}
				
		}
		
		
		if (settings.getMobData().getConfigurationSection(e.getDamager().getUniqueId().toString()) != null) {
			
			Integer MobDamageMult=0, MobLevel;
			
			MobLevel = settings.getMobData().getInt(e.getDamager().getUniqueId().toString() + ".Level");
			
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Orc")) {
				MobDamageMult = 200;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Andeddo")) {
				MobDamageMult = 400;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Pixie")) {
				MobDamageMult = 700;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Giant")) {
				MobDamageMult = 900;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Aeres")) {
				MobDamageMult = 1000;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Acolyte")) {
				MobDamageMult = 1200;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Warlock")) {
				MobDamageMult = 1400;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Hopeless")) {
				MobDamageMult = 1600;
			}
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Sphinx")) {
				MobDamageMult = 1800;
			}
			
			Random r = new Random();
			Integer random = r.nextInt(24) + 1;
			
			e.setDamage((MobDamageMult*(MobLevel/50))*random);
			
		}
	}

}
