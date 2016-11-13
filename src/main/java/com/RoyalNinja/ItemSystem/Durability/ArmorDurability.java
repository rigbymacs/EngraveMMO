package com.RoyalNinja.ItemSystem.Durability;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.RoyalNinja.EngraveMMO.ItemGenerator.DurabilityUtil;

import net.md_5.bungee.api.ChatColor;

public class ArmorDurability implements Listener {
	
	@EventHandler
	public void onWeaponLooseDura(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if (p.getInventory().getHelmet() != null) {
				ItemStack i = p.getInventory().getHelmet();
				if (!(i.hasItemMeta() && i.getItemMeta().hasLore())) return;
				for (String s : i.getItemMeta().getLore()) {
					if (s.contains(ChatColor.GOLD + "Durability:")) {
						if (!e.isCancelled() && e.getDamage()>0) {
							DurabilityUtil du = new DurabilityUtil();
							Integer currentDura = du.getCurrentDurability(i);
							if (currentDura == 0) {
								p.sendMessage(ChatColor.RED + "Your item " + i.getItemMeta().getDisplayName() + ChatColor.RED + " has broken.");
								p.getInventory().setHelmet(new ItemStack(Material.AIR));
								p.updateInventory();
								p.getWorld().playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
								return;
							}
							Integer durabilityLoss = (int) (e.getDamage()/1000);
							
							if (durabilityLoss < 1) durabilityLoss = 1;
							
							du.setDurability(i, currentDura-durabilityLoss);
							p.updateInventory();
							return;
						}
					}
				}
			}

			if (p.getInventory().getChestplate() != null) {
				ItemStack i = p.getInventory().getChestplate();
				if (!(i.hasItemMeta() && i.getItemMeta().hasLore())) return;
				for (String s : i.getItemMeta().getLore()) {
					if (s.contains(ChatColor.GOLD + "Durability:")) {
						if (!e.isCancelled() && e.getDamage()>0) {
							DurabilityUtil du = new DurabilityUtil();
							Integer currentDura = du.getCurrentDurability(i);
							if (currentDura == 0) {
								p.sendMessage(ChatColor.RED + "Your item " + i.getItemMeta().getDisplayName() + ChatColor.RED + " has broken.");
								p.getInventory().setChestplate(new ItemStack(Material.AIR));
								p.updateInventory();
								p.getWorld().playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
								return;
							}
							Integer durabilityLoss = (int) (e.getDamage()/1000);
							
							if (durabilityLoss < 1) durabilityLoss = 1;
							
							du.setDurability(i, currentDura-durabilityLoss);
							p.updateInventory();
							return;
						}
					}
				}
			}
			if (p.getInventory().getLeggings() != null) {
				ItemStack i = p.getInventory().getLeggings();
				if (!(i.hasItemMeta() && i.getItemMeta().hasLore())) return;
				for (String s : i.getItemMeta().getLore()) {
					if (s.contains(ChatColor.GOLD + "Durability:")) {
						if (!e.isCancelled() && e.getDamage()>0) {
							DurabilityUtil du = new DurabilityUtil();
							Integer currentDura = du.getCurrentDurability(i);
							if (currentDura == 0) {
								p.sendMessage(ChatColor.RED + "Your item " + i.getItemMeta().getDisplayName() + ChatColor.RED + " has broken.");
								p.getInventory().setLeggings(new ItemStack(Material.AIR));
								p.updateInventory();
								p.getWorld().playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
								return;
							}
							Integer durabilityLoss = (int) (e.getDamage()/1000);
							
							if (durabilityLoss < 1) durabilityLoss = 1;
							
							du.setDurability(i, currentDura-durabilityLoss);
							p.updateInventory();
							return;
						}
					}
				}
			}
			if (p.getInventory().getBoots() != null) {
				ItemStack i = p.getInventory().getBoots();
				if (!(i.hasItemMeta() && i.getItemMeta().hasLore())) return;
				for (String s : i.getItemMeta().getLore()) {
					if (s.contains(ChatColor.GOLD + "Durability:")) {
						if (!e.isCancelled() && e.getDamage()>0) {
							DurabilityUtil du = new DurabilityUtil();
							Integer currentDura = du.getCurrentDurability(i);
							if (currentDura == 0) {
								p.sendMessage(ChatColor.RED + "Your item " + i.getItemMeta().getDisplayName() + ChatColor.RED + " has broken.");
								p.getInventory().setBoots(new ItemStack(Material.AIR));
								p.updateInventory();
								p.getWorld().playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
								return;
							}
							Integer durabilityLoss = (int) (e.getDamage()/1000);
							
							if (durabilityLoss < 1) durabilityLoss = 1;
							
							du.setDurability(i, currentDura-durabilityLoss);
							p.updateInventory();
							return;
						}
					}
				}
			}
			
			
		}
	}

}
