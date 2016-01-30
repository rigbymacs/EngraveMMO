package com.RoyalNinja.AntiBuild.Mobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class ChangeBlockEvent implements Listener {
	
	@EventHandler
	public void onEndermanPickupBlock(EntityChangeBlockEvent e) {
		e.setCancelled(true);
	}

}
