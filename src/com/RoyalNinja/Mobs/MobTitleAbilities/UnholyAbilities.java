package com.RoyalNinja.Mobs.MobTitleAbilities;

import java.util.Random; 

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.RoyalNinja.EngraveMMO.Main;
import com.RoyalNinja.EngraveMMO.ParticleEffect;
import com.RoyalNinja.EngraveMMO.SettingsManager;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class UnholyAbilities implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onDarkExplosionAbility(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			
			if (settings.getMobData().getConfigurationSection(e.getEntity().getUniqueId().toString()) == null) return;
			
			final LivingEntity mob = (LivingEntity) e.getEntity();
			
			if (settings.getMobData().getString(mob.getUniqueId().toString() + ".Modifier").equals("Corrupt")) {
				
				Integer mobLevel = settings.getMobData().getInt(mob.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				final ParticleEffect particle = new ParticleEffect();
				
				if (r.nextInt(100) <= mobLevel/2) {
					
                       for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                          particle.sendParticlePacket((CraftPlayer)online, EnumParticle.SMOKE_LARGE, mob.getLocation().add(0, 1, 0), (float)0.2, (float)0.2, (float)0.2, (float)0.5, 200);	
                       }
                       for (Entity near : mob.getNearbyEntities(5, 5, 5)) {
							if (near instanceof Player) {
								LivingEntity livingnear = (LivingEntity) near;
								livingnear.damage(mobLevel*5);

							       
						        Location orcLocation = mob.getEyeLocation();
						        Location playerThrowLocation = livingnear.getEyeLocation();
						       
						        double x = playerThrowLocation.getX() - orcLocation.getX();
						        double y = playerThrowLocation.getY() - orcLocation.getY();
						        double z = playerThrowLocation.getZ() - orcLocation.getZ();
						       
						        Vector throwVector = new Vector(x,y,z);
						       
						        throwVector.normalize();
						        throwVector.multiply(2.0D);
						        throwVector.setY(1.0D);
						       
						        livingnear.setVelocity(throwVector);
							}
						}
                       
				}
				
				if (r.nextInt(100) <= mobLevel/4) {
					final Zombie minion = (Zombie) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.ZOMBIE);
					minion.setBaby(true);
					minion.setVillager(false);
					LivingEntity livingminion = (LivingEntity) minion;
					
					livingminion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
					
					Integer minionLevel = mobLevel/4;
					
					final Hologram holo = HologramsAPI.createHologram(Main.plugin, livingminion.getLocation());
				    ItemStack skull = new ItemStack(Material.SKULL_ITEM);
				    holo.appendItemLine(skull);
				    holo.appendTextLine(ChatColor.GRAY.toString() + "Dark Minion" + ChatColor.GOLD + " [" + ChatColor.GOLD + "Lvl " + ChatColor.RED + minionLevel + ChatColor.GOLD + "]");
				   
				      Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
				          public void run() {
				            if (minion.isDead()) {
				              holo.delete();
				            } else {
				              holo.teleport(minion.getLocation().add(0.0D, 3.2D, 0.0D));
				            }
				          }
				        }, 1L, 1L);
				      
				}
				
				
			}
			
		}
	}

}
