package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.RoyalNinja.EngraveMMO.SettingsManager;


public class GiantAbilities implements Listener {

	SettingsManager settings = SettingsManager.getInstance();

	@EventHandler
	public void onGolemAbilities(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			
			if (settings.getMobData().getConfigurationSection(e.getDamager().getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Race").equals("Giant"))) return;
				
				final Integer mobLevel = settings.getMobData().getInt(e.getDamager().getUniqueId().toString() + ".Level");
				
				final Player player = (Player) e.getEntity();
				
				Random r = new Random();
				
				if (r.nextInt(100) <= mobLevel/2) {
					player.setVelocity(new Vector(0,1,0));
				}
				
			}
		}
	}

}
