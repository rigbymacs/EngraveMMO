package com.RoyalNinja.ItemSystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.SettingsManager;

import net.md_5.bungee.api.ChatColor;

public class ArmorHealthThread extends BukkitRunnable {

	SettingsManager settings = SettingsManager.getInstance();
	
	public void run() {
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			Integer helmHP=0, chestHP=0, leggsHP=0, bootsHP=0;
			
			if (p.getInventory().getHelmet() != null) {
				if (p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getHelmet().getItemMeta().hasLore()) {
					for (String line : p.getInventory().getHelmet().getItemMeta().getLore()) {
						if (line.contains(ChatColor.GOLD + "Health: ")) {
							helmHP = (int) Double.parseDouble(ChatColor.stripColor(line.replace("Health: ", "")));
						}
					}
				}
			}else helmHP=0;
			
			if (p.getInventory().getChestplate() != null) {
				if (p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getChestplate().getItemMeta().hasLore()) {
					for (String line : p.getInventory().getChestplate().getItemMeta().getLore()) {
						if (line.contains(ChatColor.GOLD + "Health: ")) {
							chestHP = (int) Double.parseDouble(ChatColor.stripColor(line.replace("Health: ", "")));
						}
					}
				}
			}else chestHP=0;
			
			if (p.getInventory().getLeggings() != null) {
				if (p.getInventory().getLeggings().hasItemMeta() && p.getInventory().getLeggings().getItemMeta().hasLore()) {
					for (String line : p.getInventory().getLeggings().getItemMeta().getLore()) {
						if (line.contains(ChatColor.GOLD + "Health: ")) {
							leggsHP = (int) Double.parseDouble(ChatColor.stripColor(line.replace("Health: ", "")));
						}
					}
				}
			}else leggsHP=0;
			
			if (p.getInventory().getBoots() != null) {
				if (p.getInventory().getBoots().hasItemMeta() && p.getInventory().getBoots().getItemMeta().hasLore()) {
					for (String line : p.getInventory().getBoots().getItemMeta().getLore()) {
						if (line.contains(ChatColor.GOLD + "Health: ")) {
							bootsHP = (int) Double.parseDouble(ChatColor.stripColor(line.replace("Health: ", "")));
						}
					}
				}
			}else bootsHP=0;
			
			Integer totalHealth = helmHP+chestHP+leggsHP+bootsHP+500;
			
			settings.getPlayerData().set(p.getUniqueId().toString() + ".Health", totalHealth);
			settings.savePlayerData();
		}
		
	}
	
	

}
