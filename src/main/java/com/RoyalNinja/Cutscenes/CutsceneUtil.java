package com.RoyalNinja.Cutscenes;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.RoyalNinja.EngraveMMO.Main;
import com.RoyalNinja.EngraveMMO.SettingsManager;

public class CutsceneUtil {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public HashMap<String, Boolean> inCutscene = new HashMap<String, Boolean>();
	public HashMap<String, Integer> currentCutscenePart = new HashMap<String, Integer>();
	
	Integer task;
	
	public void playCutscene(final Player player, final String cutscene, Long speed) {
		
		if (settings.getCinematics().getConfigurationSection(cutscene) == null) return;
		
		final Location startLocation = player.getLocation();

		if (!currentCutscenePart.containsKey(player.getName())) {
			currentCutscenePart.put(player.getName(), 1);
		}
		
		for (Player online : Bukkit.getServer().getOnlinePlayers()) {
			online.hidePlayer(player);
		}
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            public void run() {
            	
        		Integer cutsceneLength = settings.getCinematics().getInt(cutscene + ".Length");
            	Integer playerCutscenePart = currentCutscenePart.get(player.getName());

            	if (playerCutscenePart == cutsceneLength || playerCutscenePart > cutsceneLength) {
            		for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            			online.showPlayer(player);
            		}
            		player.teleport(startLocation);
            		currentCutscenePart.put(player.getName(), 1);
            		Bukkit.getScheduler().cancelTask(task);
            		return;
            	}else {
            		
                	Location nextPoint = new Location(Bukkit.getWorld(settings.getCinematics().getString(cutscene + "." + playerCutscenePart + ".World")),
                			settings.getCinematics().getDouble(cutscene + "." + playerCutscenePart + ".X"),
                			settings.getCinematics().getDouble(cutscene + "." + playerCutscenePart + ".Y"),
                			settings.getCinematics().getDouble(cutscene + "." + playerCutscenePart + ".Z"), 
                			(float) settings.getCinematics().getDouble(cutscene + "." + playerCutscenePart + ".Yaw"),
                			(float) settings.getCinematics().getDouble(cutscene + "." + playerCutscenePart + ".Pitch"));
                	
            		player.teleport(nextPoint);
            		
            		currentCutscenePart.put(player.getName(), playerCutscenePart + 1);	
            	}
            }
        }, speed, speed);
		
		
		
	}
	
	public void stopCutscene(Player player) {
		
	}
	
	
	public boolean inCutscene(Player player) {
		
		return false;
	}
	
	
	
	
	
	
}
