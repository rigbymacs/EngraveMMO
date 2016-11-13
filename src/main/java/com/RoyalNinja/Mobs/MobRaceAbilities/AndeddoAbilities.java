package com.RoyalNinja.Mobs.MobRaceAbilities;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.RoyalNinja.EngraveMMO.Main;
import com.RoyalNinja.EngraveMMO.ParticleEffect;
import com.RoyalNinja.EngraveMMO.SettingsManager;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class AndeddoAbilities extends BukkitRunnable implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void run() {
		
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			
			if (settings.getMobData().getConfigurationSection(e.getUniqueId().toString()) != null) {
				
				if (!(settings.getMobData().getString(e.getUniqueId().toString() + ".Race").equals("Andeddo"))) return;
				
				Integer mobLevel = settings.getMobData().getInt(e.getUniqueId().toString() + ".Level");
				
				Random r = new Random();
				
				if (e instanceof Spider) {
					Spider ent = (Spider) e;
					if (ent.getTarget() instanceof Player) {
						if (r.nextInt(100) <= mobLevel/2) {
							
							for (Block b : new com.RoyalNinja.EngraveMMO.Line(ent.getEyeLocation(), ent.getTarget().getEyeLocation())) {
								
								ParticleEffect particle = new ParticleEffect();
								
								for (Player p : Bukkit.getServer().getOnlinePlayers()) {
									particle.sendParticlePacket((CraftPlayer)p, EnumParticle.SLIME, b.getLocation(), (float)0, (float)0, (float)0, (float)1, 50);
								}
								
								ent.getTarget().damage(mobLevel*5);
								ent.getTarget().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 2));
								
							}
							
						}
					}
				}
				
			}
			
			
		}
		
		
		
	}
	
	
	@EventHandler
	public void onSpawnZombieAbility(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (settings.getMobData().getConfigurationSection(e.getEntity().getUniqueId().toString()) == null) return;
			
			Entity andeddo = e.getEntity();
			
			if (settings.getMobData().getString(andeddo.getUniqueId().toString() + ".Race").equals("Andeddo")) {
				
				Integer mobLevel = settings.getMobData().getInt(andeddo.getUniqueId().toString() + ".Level");
				
				
				Random r = new Random();
				
				
				if (r.nextInt(100) <= mobLevel/2) {
					
					final Zombie zombie = (Zombie) andeddo.getWorld().spawnEntity(andeddo.getLocation(), EntityType.ZOMBIE);
					zombie.setBaby(false);
					zombie.setVillager(true);
					LivingEntity livingminion = (LivingEntity) zombie;
					
					livingminion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
					
					Integer minionLevel = mobLevel/4;
					
					final Hologram holo = HologramsAPI.createHologram(Main.plugin, livingminion.getLocation());
				    ItemStack skull = new ItemStack(Material.SKULL_ITEM);
				    holo.appendItemLine(skull);
				    
				    holo.appendTextLine(ChatColor.GRAY.toString() + "Undead Minion" + ChatColor.GOLD + " [" + ChatColor.GOLD + "Lvl " + ChatColor.RED + minionLevel + ChatColor.GOLD + "]");
				   
				      Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
				          public void run() {
				            if (zombie.isDead()) {
				              holo.delete();
				            } else {
				              holo.teleport(zombie.getLocation().add(0.0D, 3.2D, 0.0D));
				            }
				          }
				        }, 1L, 1L);
					
					
				}
				
				
			}
			
			
			
		}
	}

}
