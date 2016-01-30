package com.RoyalNinja.Mobs.MobTitleAbilities;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.RoyalNinja.EngraveMMO.SettingsManager;

public class ShiftedAbilities implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler(priority = EventPriority.LOW)
	public void onShiftedDamage(EntityDamageByEntityEvent e) {
		if (settings.getMobData().getConfigurationSection(e.getDamager().getUniqueId().toString()) != null) {
			
			Entity shifted = e.getDamager();
			
			Double lastDamage = shifted.getLastDamageCause().getDamage();
			
			Integer MobDamageMult=0;
			
			Integer mobLevel = settings.getMobData().getInt(shifted.getUniqueId().toString() + ".Level");
			
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
			
			e.setDamage((lastDamage/(mobLevel/4))*MobDamageMult);
			
			
		}
	}

}
