package com.RoyalNinja.Mobs.MobTitleAbilities;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.RoyalNinja.EngraveMMO.LocationManager;
import com.RoyalNinja.EngraveMMO.SettingsManager;

public class SilentAbilities implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onTeleportAbility(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) return;
		
		if (settings.getMobData().getString(e.getEntity().getUniqueId().toString() + ".Modifier") != null) {
			if (settings.getMobData().getString(e.getEntity().getUniqueId().toString() + ".Modifier").equals("Silent")) {
				Random r = new Random();
				
				Integer level = settings.getMobData().getInt(e.getEntity().getUniqueId().toString() + ".Level");
				
				if (r.nextInt(100) <= level/2) {
					if (!e.getEntity().isDead()) {
						LivingEntity mob = (LivingEntity) e.getEntity();
						Player p = (Player) e.getDamager();
						
						LocationManager lh = new LocationManager();
						
						mob.teleport(lh.getBlockBehindPlayer(p).getLocation());
					}
				}
			}
		}
	}
	@EventHandler
	public void onPoisonAbility(EntityDamageByEntityEvent e) {
		if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Modifier") != null) {
			if (settings.getMobData().getString(e.getDamager().getUniqueId().toString() + ".Modifier").equals("Silent")) {
				Random r = new Random();

				Integer level = settings.getMobData().getInt(e.getDamager().getUniqueId().toString() + ".Level");
				
				if (r.nextInt(100) <= level/2) {
					if (!e.getEntity().isDead()) {
						LivingEntity mob = (LivingEntity) e.getEntity();
						
						mob.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
					}
				}
			}
		}
	}

}
