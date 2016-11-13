package com.RoyalNinja.ItemSystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.SettingsManager;

public class SetPlayerHealthThread extends BukkitRunnable {

	public void run() {
		
		SettingsManager settings = SettingsManager.getInstance();
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			Integer health = settings.getPlayerData().getInt(p.getUniqueId().toString() + ".Health");
			
			p.setMaxHealth(health);
			p.setHealthScale(20.0);
			p.setHealthScaled(true);
			
		}
		
	}
	
	

}
