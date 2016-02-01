package com.RoyalNinja.ItemSystem;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class WeaponDamage implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			
			Player p = (Player) e.getDamager();
			
			if (p.getItemInHand() != null) {
				
				ItemStack weapon = p.getInventory().getItemInHand();
				
				Double minDmg=0.0, maxDmg=0.0;
				
				if (weapon.hasItemMeta() && weapon.getItemMeta().hasLore()) {
					for (String line : weapon.getItemMeta().getLore()) {
						if (line.contains(ChatColor.GOLD + "DMG: ")) {

					      String dmgString;
					      
						  dmgString = line.replaceAll("DMG: ", "").replaceAll(" ", "");
				
						  minDmg = Double.parseDouble(ChatColor.stripColor(dmgString.split("-")[0]));
						  maxDmg = Double.parseDouble(ChatColor.stripColor(dmgString.split("-")[1]));
						}
					}
				}else return;
				
				Random r = new Random();
				
				Integer dmg = r.nextInt(maxDmg.intValue() - minDmg.intValue()) + minDmg.intValue();
				
				e.setDamage(dmg);
			}
			
			
		}
	}

}
