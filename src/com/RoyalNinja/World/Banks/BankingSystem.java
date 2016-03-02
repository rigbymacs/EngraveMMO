package com.RoyalNinja.World.Banks;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class BankingSystem implements CommandExecutor, Listener {

	public ArrayList<String> addingBank = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		
		
		if (cmd.getName().equalsIgnoreCase("addbank")) {
			
			if (addingBank.contains(p.getName())) {
				addingBank.remove(p.getName());
				p.sendMessage(ChatColor.GREEN + "Removed from bank editing mode.");
				return true;
			}else {
				addingBank.add(p.getName());
				p.sendMessage(ChatColor.GREEN + "Added to bank editing mode.");
				return true;
			}
			
		}
		
		return false;
	}
}
