package com.RoyalNinja.MySQL;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.RoyalNinja.EngraveMMO.MobHandler;
import com.RoyalNinja.EngraveMMO.MobHandler.MobModifier;
import com.RoyalNinja.EngraveMMO.MobHandler.MobRace;
import com.RoyalNinja.EngraveMMO.LevelingSystem.LevelingSystem;

public class PlayerLevelSetup implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		
		LevelingSystem ls = new LevelingSystem();
		
		MobHandler mh = new MobHandler();
		
		mh.spawnMonster(p.getLocation(), MobRace.ORC, MobModifier.BEASTLY, 1);

		if (!ls.levelingDataContainsPlayer(p)) {
			ls.setupPlayerLevelingData(p);
		}else return;
	}

}
