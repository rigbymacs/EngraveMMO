package com.RoyalNinja.MySQL;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.RoyalNinja.EngraveMMO.Economy;
import com.RoyalNinja.EngraveMMO.MobHandler;
import com.RoyalNinja.EngraveMMO.MobHandler.MobModifier;
import com.RoyalNinja.EngraveMMO.MobHandler.MobRace;

public class PlayerEconomySetup implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		
		Economy econ = new Economy();
		
		MobHandler mh = new MobHandler();
		
		mh.spawnMonster(p.getLocation(), MobRace.SPHINX, MobModifier.BEASTLY, 100);
		p.setHealthScale(20);
		p.setFlying(true);
		
		if (!econ.economyDataContainsPlayer(p)) {
			econ.setupPlayerEconomyData(p);
		}else return;
	}

}
