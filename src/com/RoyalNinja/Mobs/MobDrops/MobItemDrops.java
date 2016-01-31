package com.RoyalNinja.Mobs.MobDrops;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.RoyalNinja.EngraveMMO.MobHandler.MobRace;
import com.RoyalNinja.EngraveMMO.SettingsManager;
import com.RoyalNinja.EngraveMMO.ItemGenerator.WeaponGenerator;
import com.RoyalNinja.EngraveMMO.ItemGenerator.WeaponGenerator.Rarity;

public class MobItemDrops implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onMobDrop(EntityDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			
			Player p = (Player) e.getEntity().getKiller();
			Entity ent = e.getEntity();
			
			if (settings.getMobData().getConfigurationSection(ent.getUniqueId().toString()) != null) {
				
				e.getDrops().clear();
				
				WeaponGenerator wg = new WeaponGenerator();
				
				Integer mobLevel = settings.getMobData().getInt(ent.getUniqueId().toString() + ".Level");
				MobRace mobRace = MobRace.ORC;
				
				String stringRace = settings.getMobData().getString(ent.getUniqueId().toString() + ".Race");

				if (stringRace.equals("Orc")) mobRace = MobRace.ORC;
				if (stringRace.equals("Andeddo")) mobRace = MobRace.ANDEDDO;
				if (stringRace.equals("Pixie")) mobRace = MobRace.PIXIE;
				if (stringRace.equals("Giant")) mobRace = MobRace.GIANT;
				if (stringRace.equals("Aeres")) mobRace = MobRace.AERES;
				if (stringRace.equals("Acolyte")) mobRace = MobRace.ACOLYTE;
				if (stringRace.equals("Hopeless")) mobRace = MobRace.HOPELESS;
				if (stringRace.equals("Sphinx")) mobRace = MobRace.SPHINX;

				Random r = new Random();

				Integer dropChance = r.nextInt(100);
				Integer dropChanceDecimal = r.nextInt(1000);
				Integer rarityChance = r.nextInt(100);
				
				Rarity weaponRarity = Rarity.Common;

				if (rarityChance <= 100 && rarityChance > 20) weaponRarity = Rarity.Common;
				if (rarityChance <= 20 && rarityChance > 10) weaponRarity = Rarity.Uncommon;
				if (rarityChance <= 10 && rarityChance > 6) weaponRarity = Rarity.Epic;
				if (rarityChance <= 6 && rarityChance > 3) weaponRarity = Rarity.Heroic;
				if (rarityChance <= 3 && rarityChance > 1) weaponRarity = Rarity.Legendary;
				if (rarityChance == 1) weaponRarity = Rarity.Mythical;

				if (mobRace.equals(MobRace.ORC)) {
					if (dropChance <= 100 && dropChance > 90) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				if (mobRace.equals(MobRace.ANDEDDO)) {
					if (dropChance <= 90 && dropChance > 83) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				if (mobRace.equals(MobRace.PIXIE)) {
					if (dropChance <= 83 && dropChance > 78) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				if (mobRace.equals(MobRace.GIANT)) {
					if (dropChance <= 78 && dropChance > 75) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				if (mobRace.equals(MobRace.AERES)) {
					if (dropChance <= 75 && dropChance > 73) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				if (mobRace.equals(MobRace.ACOLYTE)) {
					if (dropChance <= 73 && dropChance > 72) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				if (mobRace.equals(MobRace.HOPELESS)) {
					if (dropChanceDecimal <= 1000 && dropChanceDecimal > 995) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				if (mobRace.equals(MobRace.SPHINX)) {
					if (dropChanceDecimal <= 995 && dropChanceDecimal > 993) {
						ItemStack weapon = wg.generateRandomWeapon(mobLevel, mobRace, weaponRarity);
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), weapon);
					}
				}
				
				
			}
			
		}
	}

}
