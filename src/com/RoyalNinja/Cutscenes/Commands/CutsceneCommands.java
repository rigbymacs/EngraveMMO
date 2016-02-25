package com.RoyalNinja.Cutscenes.Commands;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.RoyalNinja.Cutscenes.CutsceneUtil;
import com.RoyalNinja.EngraveMMO.SettingsManager;

import net.md_5.bungee.api.ChatColor;

public class CutsceneCommands implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	public static List<String> cutsceneMode = new ArrayList<String>();
	
	public static HashMap<String, List<Location>> currentCutscene = new HashMap<String, List<Location>>();
	public static HashMap<String, String> currentCutsceneName = new HashMap<String, String>();
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("cutscene")) {
			
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED + "Invalid format");
				return true;
			}
			
			if (!p.isOp()) return true;
			
			if (args[0].equalsIgnoreCase("create")) {
				
				if (args.length != 2) {
					p.sendMessage(ChatColor.RED + "Invalid format");
					return true;
				}
				
				if (settings.getCinematics().getConfigurationSection(args[1]) != null) { 
					p.sendMessage(ChatColor.RED + "Cinematic Name " + ChatColor.GOLD + args[1] + ChatColor.RED + " is already taken.");
					return true;
				}
				
				if (cutsceneMode.contains(p.getName())) {
					p.sendMessage(ChatColor.RED + "You are already in cutscene creation mode. Type /cs discard to discard this cutscene.");
					return true;
				}
				
				else {
					cutsceneMode.add(p.getName());
					List<Location> cutscene = new ArrayList<Location>();
					cutscene.add(p.getLocation());
					currentCutscene.put(p.getName(), cutscene);
					currentCutsceneName.put(p.getName(), args[1]);
					p.sendMessage(ChatColor.AQUA + "Cutscene mode activated. " + ChatColor.GOLD + "WALK & LOOK " + ChatColor.AQUA + "around to create the cutscene. Type /cs complete to finish creating the cutscene.");
					return true;
				}
			}
			
			if (args[0].equalsIgnoreCase("discard")) {
				
				if (!cutsceneMode.contains(p.getName())) {
					p.sendMessage(ChatColor.RED + "You are not creating a cutscene.");
					return true;
				}
				
				cutsceneMode.remove(p.getName());
				currentCutscene.remove(p.getName());
				currentCutsceneName.remove(p.getName());
				
				p.sendMessage(ChatColor.AQUA + "Cutscene successfully " + ChatColor.RED + "discarded.");
				
				return true;
					
				
			}
			if (args[0].equalsIgnoreCase("complete")) {
				
				if (!cutsceneMode.contains(p.getName())) {
					p.sendMessage(ChatColor.RED + "You are not creating a cutscene.");
					return true;
				}
				
				String cutsceneName = currentCutsceneName.get(p.getName());
				
				List<Location> cinematicLocations = currentCutscene.get(p.getName());

				settings.getCinematics().createSection(cutsceneName);
				
				for (int i = 1; i < cinematicLocations.size(); i++) {
					settings.getCinematics().set(cutsceneName + "." + i + ".World", cinematicLocations.get(i).getWorld().getName());
					settings.getCinematics().set(cutsceneName + "." + i + ".X", cinematicLocations.get(i).getX());
					settings.getCinematics().set(cutsceneName + "." + i + ".Y", cinematicLocations.get(i).getY());
					settings.getCinematics().set(cutsceneName + "." + i + ".Z", cinematicLocations.get(i).getZ());
					settings.getCinematics().set(cutsceneName + "." + i + ".Pitch", cinematicLocations.get(i).getPitch());
					settings.getCinematics().set(cutsceneName + "." + i + ".Yaw", cinematicLocations.get(i).getYaw());
					settings.getCinematics().set(cutsceneName + ".Length", cinematicLocations.size() - 1);
					settings.saveCinematics();
				}
				settings.saveCinematics();
				
				p.sendMessage(ChatColor.AQUA + "Cutscene " + ChatColor.GOLD + cutsceneName + ChatColor.AQUA + " successfully " + ChatColor.GREEN + "created.");
				
				cutsceneMode.remove(p.getName());
				
				if (settings.getCinematics().getList("Cutscenes") == null) { 
					List<String> cutscenes = new ArrayList<String>();
					cutscenes.add(cutsceneName);
					settings.getCinematics().set("Cutscenes", cutscenes);
					settings.saveCinematics();
				}else {
					List<String> cutscenes = (List<String>) settings.getCinematics().getList("Cutscenes");
					cutscenes.add(cutsceneName);
					settings.getCinematics().set("Cutscenes", cutscenes);
					settings.saveCinematics();
				}
				
				return true;
			}
			
			
			if (args[0].equalsIgnoreCase("play")) {
				if (args.length != 2) {
					p.sendMessage(ChatColor.RED + "Invalid format");
					return true;
				}
				if (settings.getCinematics().getConfigurationSection(args[1]) == null) {
					p.sendMessage(ChatColor.RED + "The cutscene " + ChatColor.GOLD + args[1] + ChatColor.RED + " does not exist");
					return true;
				}
				try {
					CutsceneUtil.class.newInstance().playCutscene(p, args[1], (long) 0.001);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (args[0].equalsIgnoreCase("remove")) {
				if (args.length != 2) {
					p.sendMessage(ChatColor.RED + "Invalid format");
					return true;
				}
				String cutscene = args[1];
				
				if (settings.getCinematics().getConfigurationSection(cutscene) == null) {
					p.sendMessage(ChatColor.RED + "The cutscene " + ChatColor.GOLD + cutscene + ChatColor.RED + " does not exist");
					return true;
				}
				List<String> cutscenes = (List<String>) settings.getCinematics().getList("Cutscenes");
				
				settings.getCinematics().set(cutscene, null);
				cutscenes.remove(cutscene);
				settings.getCinematics().set("Cutscenes", cutscenes);
				settings.saveCinematics();
				p.sendMessage(ChatColor.AQUA + "Cutscene " + ChatColor.GOLD + cutscene + ChatColor.AQUA + " has been removed permenantly!");
				return true;
					
			}
		}
		
		return true;
	}

	
	
	
}
