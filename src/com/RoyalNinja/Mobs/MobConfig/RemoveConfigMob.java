package com.RoyalNinja.Mobs.MobConfig;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.RoyalNinja.EngraveMMO.SettingsManager;

public class RemoveConfigMob implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (settings.getMobData().getString(e.getEntity().getUniqueId().toString()) != null) {
			settings.getMobData().set(e.getEntity().getUniqueId().toString(), null);
			settings.saveMobData();
		}
	}

}
