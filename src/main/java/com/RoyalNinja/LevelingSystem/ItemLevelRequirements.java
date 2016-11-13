package com.RoyalNinja.LevelingSystem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import com.RoyalNinja.EngraveMMO.LevelingSystem.LevelingSystem;

import net.md_5.bungee.api.ChatColor;

public class ItemLevelRequirements implements Listener {
	
	//Armor
	@EventHandler(priority = EventPriority.LOWEST)
	public void onInventoryClick2(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
				
		
		if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.AIR) && e.getCurrentItem().equals(null)) return;

		LevelingSystem ls = new LevelingSystem();

		ItemStack cursor = e.getCursor();
		
		if ((cursor != null) && (cursor.getType() != Material.AIR) && (
		        (cursor.getType() == Material.LEATHER_HELMET) || 
		        (cursor.getType() == Material.LEATHER_HELMET) || 
		        (cursor.getType() == Material.LEATHER_CHESTPLATE) || 
		        (cursor.getType() == Material.LEATHER_LEGGINGS) || 
		        (cursor.getType() == Material.LEATHER_BOOTS) || 
		        (cursor.getType() == Material.CHAINMAIL_HELMET) || 
		        (cursor.getType() == Material.CHAINMAIL_CHESTPLATE) || 
		        (cursor.getType() == Material.CHAINMAIL_LEGGINGS) || 
		        (cursor.getType() == Material.CHAINMAIL_BOOTS) || 
		        (cursor.getType() == Material.IRON_HELMET) || 
		        (cursor.getType() == Material.IRON_CHESTPLATE) || 
		        (cursor.getType() == Material.IRON_LEGGINGS) || 
		        (cursor.getType() == Material.IRON_BOOTS) || 
		        (cursor.getType() == Material.GOLD_HELMET) || 
		        (cursor.getType() == Material.GOLD_CHESTPLATE) || 
		        (cursor.getType() == Material.GOLD_LEGGINGS) || 
		        (cursor.getType() == Material.GOLD_BOOTS) || 
		        (cursor.getType() == Material.DIAMOND_HELMET) || 
		        (cursor.getType() == Material.DIAMOND_CHESTPLATE) || 
		        (cursor.getType() == Material.DIAMOND_LEGGINGS) || 
		        (cursor.getType() == Material.DIAMOND_BOOTS))) {
			
			Integer levelReq=0;
			Integer playerLevel = ls.getLevelFromPlayer(p);	

			if (cursor.hasItemMeta() && cursor.getItemMeta().hasLore()) {
				for (String line : cursor.getItemMeta().getLore()) {
					if (line.contains(ChatColor.GOLD + "Level Req: ")) {
						levelReq = Integer.parseInt(ChatColor.stripColor(line.replace("Level Req: ", "")));
					}
				}
			}else return;
			

			if (levelReq > playerLevel && e.getSlotType().equals(SlotType.ARMOR)) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "You are not high enough level to use this item!");
				p.updateInventory();
			}else return;
			
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onInventoryClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
				
		
		if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.AIR) && e.getCurrentItem().equals(null)) return;

		LevelingSystem ls = new LevelingSystem();

		ItemStack currentItem = e.getCurrentItem();
		
		if ((currentItem != null) && (currentItem.getType() != Material.AIR) && (
		        (currentItem.getType() == Material.LEATHER_HELMET) || 
		        (currentItem.getType() == Material.LEATHER_HELMET) || 
		        (currentItem.getType() == Material.LEATHER_CHESTPLATE) || 
		        (currentItem.getType() == Material.LEATHER_LEGGINGS) || 
		        (currentItem.getType() == Material.LEATHER_BOOTS) || 
		        (currentItem.getType() == Material.CHAINMAIL_HELMET) || 
		        (currentItem.getType() == Material.CHAINMAIL_CHESTPLATE) || 
		        (currentItem.getType() == Material.CHAINMAIL_LEGGINGS) || 
		        (currentItem.getType() == Material.CHAINMAIL_BOOTS) || 
		        (currentItem.getType() == Material.IRON_HELMET) || 
		        (currentItem.getType() == Material.IRON_CHESTPLATE) || 
		        (currentItem.getType() == Material.IRON_LEGGINGS) || 
		        (currentItem.getType() == Material.IRON_BOOTS) || 
		        (currentItem.getType() == Material.GOLD_HELMET) || 
		        (currentItem.getType() == Material.GOLD_CHESTPLATE) || 
		        (currentItem.getType() == Material.GOLD_LEGGINGS) || 
		        (currentItem.getType() == Material.GOLD_BOOTS) || 
		        (currentItem.getType() == Material.DIAMOND_HELMET) || 
		        (currentItem.getType() == Material.DIAMOND_CHESTPLATE) || 
		        (currentItem.getType() == Material.DIAMOND_LEGGINGS) || 
		        (currentItem.getType() == Material.DIAMOND_BOOTS))) {
			
			Integer levelReq=0;
			Integer playerLevel = ls.getLevelFromPlayer(p);	

			if (currentItem.hasItemMeta() && currentItem.getItemMeta().hasLore()) {
				for (String line : currentItem.getItemMeta().getLore()) {
					if (line.contains(ChatColor.GOLD + "Level Req: ")) {
						levelReq = Integer.parseInt(ChatColor.stripColor(line.replace("Level Req: ", "")));
					}
				}
			}else return;
			

			if (levelReq > playerLevel && e.isShiftClick()) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "You are not high enough level to use this item!");
				p.updateInventory();
			}else return;
			
		}
	}
		
	//Weapons
	@EventHandler
	public void onSlotSwitch(PlayerItemHeldEvent e) {
		Player p = (Player) e.getPlayer();
		
		ItemStack newItem = p.getInventory().getItem(e.getNewSlot());
		
		LevelingSystem ls = new LevelingSystem();
		
		Integer levelReq=0;
		Integer playerLevel = ls.getLevelFromPlayer(p);
		
		if (newItem == null) return;
		
		if (newItem.hasItemMeta() && newItem.getItemMeta().hasLore()) {
			for (String line : newItem.getItemMeta().getLore()) {
				if (line.contains(ChatColor.GOLD + "Level Req: ")) {
					levelReq = Integer.parseInt(ChatColor.stripColor(line.replace("Level Req: ", "")));
				}
			}
		}else return;
		
		if (levelReq > playerLevel) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You are not high enough level to use this item!");
			p.getInventory().setHeldItemSlot(e.getPreviousSlot());
			p.updateInventory();
		}else return;
		
	}

}
