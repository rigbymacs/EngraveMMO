package com.RoyalNinja.Cutscenes.Listeners;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.RoyalNinja.Cutscenes.Commands.CutsceneCommands;

public class SaveCutsceneLocations implements Listener {
	
	@EventHandler
	public void onPlayerCutsceneMove(PlayerMoveEvent e) {
		Player p = (Player) e.getPlayer();
		
		if (CutsceneCommands.cutsceneMode.contains(p.getName())) {
			List<Location> cutsceneLocations = CutsceneCommands.currentCutscene.get(p.getName());
			
			cutsceneLocations.add(e.getTo());
			
			CutsceneCommands.currentCutscene.put(p.getName(), cutsceneLocations);
		}
	}

}
