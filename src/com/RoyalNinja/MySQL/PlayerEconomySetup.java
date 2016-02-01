package com.RoyalNinja.MySQL;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.RoyalNinja.EngraveMMO.Economy;
import com.RoyalNinja.EngraveMMO.ItemGenerator.ArmorGenerator;
import com.RoyalNinja.EngraveMMO.ItemGenerator.WeaponGenerator;
import com.RoyalNinja.EngraveMMO.ItemGenerator.WeaponGenerator.Rarity;
import com.RoyalNinja.EngraveMMO.MobHandler.MobRace;

public class PlayerEconomySetup implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		
		Economy econ = new Economy();
		
		ArmorGenerator ag = new ArmorGenerator();
		WeaponGenerator wg = new WeaponGenerator();
		
		p.getInventory().addItem(ag.generateRandomArmor(1, MobRace.SPHINX, Rarity.Legendary));
		p.getInventory().addItem(wg.generateRandomWeapon(1, MobRace.SPHINX, Rarity.Legendary));
		
		if (!econ.economyDataContainsPlayer(p)) {
			econ.setupPlayerEconomyData(p);
		}else return;
	}

}
