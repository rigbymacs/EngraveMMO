package com.RoyalNinja.GUI;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.GUI.BossBarHandler;
import com.RoyalNinja.EngraveMMO.LevelingSystem.LevelingSystem;

public class SpawnEnderDragon extends BukkitRunnable {

	
	public void run() {
		
		LevelingSystem ls = new LevelingSystem();
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			try {
				
				BossBarHandler.sendSpawnPacket(p, ChatColor.GOLD + "Level: " + ChatColor.AQUA + ls.getLevelFromPlayer(p) + "  " + ChatColor.GREEN + p.getHealth() + ChatColor.GOLD + "/" + ChatColor.GREEN + p.getMaxHealth() + "  " + ChatColor.GOLD + "EXP: " + ChatColor.AQUA + ls.getPercent(p) + "%", 400);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
