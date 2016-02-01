package com.RoyalNinja.Mobs.MobActionBarHealth;

import org.bukkit.Bukkit;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.RoyalNinja.EngraveMMO.ActionBarUtil;

import net.md_5.bungee.api.ChatColor;

public class ShowMobHealth implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && !e.getEntity().isDead()) {
			
			Player p = (Player) e.getDamager();
			LivingEntity mob = (LivingEntity) e.getEntity();
			Integer healthPercent = (int) ((Math.round(mob.getHealth())/mob.getMaxHealth())*100);
			
			if (!(mob instanceof Zombie) && !(mob instanceof Spider) && !(mob instanceof Endermite) && !(mob instanceof IronGolem) && !(mob instanceof Skeleton) && !(mob instanceof Witch)) return;

			if (healthPercent > 90) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + 
				ChatColor.GRAY + "[" +
				ChatColor.GREEN + "||||||||||||||||||||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 80 && healthPercent <= 90) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "||||||" + ChatColor.GREEN + "||||||||||||||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 70 && healthPercent <= 80) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "|||||||||" + ChatColor.GREEN + "|||||||||||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 60 && healthPercent <= 70) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "|||||||||||" + ChatColor.GREEN + "|||||||||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 50 && healthPercent <= 60) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "|||||||||||||" + ChatColor.GREEN + "|||||||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 40 && healthPercent <= 50) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "|||||||||||||||" + ChatColor.YELLOW + "|||||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 30 && healthPercent <= 40) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "|||||||||||||||||" + ChatColor.YELLOW + "|||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 20 && healthPercent <= 30) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "|||||||||||||||||||" + ChatColor.RED + "|||||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent > 10 && healthPercent <= 20) {
				ActionBarUtil.sendActionBar(p,ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() +  ChatColor.GRAY + "[" +
				ChatColor.GRAY + "|||||||||||||||||||||" + ChatColor.RED + "|||||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent <= 10 && healthPercent > 5) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "||||||||||||||||||||||||" + ChatColor.DARK_RED + "||||||"
				+ ChatColor.GRAY + "]");
			}
			if (healthPercent <= 5) {
				ActionBarUtil.sendActionBar(p, ChatColor.RED.toString() + Math.round(mob.getHealth()) 
				+ ChatColor.GRAY + "/" + 
				ChatColor.RED + mob.getMaxHealth() + ChatColor.GRAY + "[" +
				ChatColor.GRAY + "||||||||||||||||||||||||||||||"
				+ ChatColor.GRAY + "]");
			}
			
		}
	}
	
}
