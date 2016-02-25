package com.RoyalNinja.ItemSystem.Durability;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.RoyalNinja.EngraveMMO.ItemGenerator.DurabilityUtil;

import net.md_5.bungee.api.ChatColor;

public class WeaponDurability implements Listener {
	
	@EventHandler
	public void onWeaponLooseDura(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			
			if (p.getInventory().getItemInHand() != null) {

				ItemStack i = p.getInventory().getItemInHand();
				
				if (!(i.hasItemMeta() && i.getItemMeta().hasLore())) return;
				
				for (String s : i.getItemMeta().getLore()) {
					if (s.contains(ChatColor.GOLD + "Durability:")) {
						if (!e.isCancelled() && e.getDamage()>0) {
							
							DurabilityUtil du = new DurabilityUtil();
							
							Integer currentDura = du.getCurrentDurability(i);
							
							if (currentDura == 0) {
								p.sendMessage(ChatColor.RED + "Your item " + i.getItemMeta().getDisplayName() + ChatColor.RED + " has broken.");
								p.getInventory().remove(i);
								p.updateInventory();
								p.getWorld().playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
								return;
							}
							
							du.setDurability(i, currentDura-1);
							
							p.updateInventory();
							
							return;
							
						}
					}
				}
			}
		}
	}

}
