package com.RoyalNinja.World.Banks;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.RoyalNinja.EngraveMMO.Map.Banking.BankUtil;

import net.md_5.bungee.api.ChatColor;

public class BankingSystem implements CommandExecutor, Listener {

	public ArrayList<String> editingBank = new ArrayList<String>();
	public ArrayList<String> removingBank = new ArrayList<String>();
	
	public HashMap<String, Location> bankRemoveLocation = new HashMap<String, Location>();
	
	@EventHandler
	public void onPlayerAddBank(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		
		if (editingBank.contains(p.getName())) {
			if (e.getBlock().getType().equals(Material.CHEST)) {
				
				p.sendMessage(ChatColor.AQUA + "Bank has been successfully" + ChatColor.GREEN + " CREATED.");
				
				BankUtil bu = new BankUtil();
				
				bu.createBank(p.getWorld().getName(), e.getBlock().getLocation().getX(), e.getBlock().getLocation().getY(), e.getBlock().getLocation().getZ());
				
			}
		}
		
	}
	@EventHandler
	public void onPlayerEditRemoveBank(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		
		if (editingBank.contains(p.getName())) {
			if (e.getBlock().getType().equals(Material.CHEST)) {
				
				p.sendMessage(ChatColor.AQUA + "Are you sure you want to remove this bank permenantly? Type " + ChatColor.GREEN + "Y" + ChatColor.GRAY + "/" + ChatColor.RED + "N");
				
				removingBank.add(p.getName());
				bankRemoveLocation.put(p.getName(), e.getBlock().getLocation());
				
			}
		}
		
	}
	@EventHandler
	public void onPlayerRemoveBank(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if (removingBank.contains(p.getName())) {
			
			if (e.getMessage().contains("N")) {
				p.sendMessage(ChatColor.GREEN + "Removing canceled.");
				removingBank.remove(p.getName());
				bankRemoveLocation.remove(p.getName());
				return;
			}
			if (e.getMessage().contains("Y")) {
				p.sendMessage(ChatColor.AQUA + "Bank has been successfully" + ChatColor.RED + " DELETED.");
				
				BankUtil bu = new BankUtil();
				
				bu.removeBank(bankRemoveLocation.get(p.getName()));
				
				removingBank.remove(p.getName());
				bankRemoveLocation.remove(p.getName());
				return;
			}
			
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("editbank")) {
			if (editingBank.contains(p.getName())) {
				editingBank.remove(p.getName());
				p.sendMessage(ChatColor.GREEN + "Removed from bank editing mode.");
				return true;
			}else {
				editingBank.add(p.getName());
				p.sendMessage(ChatColor.GREEN + "Added to bank editing mode.");
				return true;
			}
		}
		return false;
	}
}
