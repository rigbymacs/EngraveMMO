package com.RoyalNinja;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.RoyalNinja.AntiBuild.Mobs.ChangeBlockEvent;
import com.RoyalNinja.Cutscenes.Commands.CutsceneCommands;
import com.RoyalNinja.Cutscenes.Listeners.SaveCutsceneLocations;
import com.RoyalNinja.EngraveMMO.MySQLUtil;
import com.RoyalNinja.EngraveMMO.SettingsManager;
import com.RoyalNinja.EngraveMMO.ItemGenerator.DurabilityUtil;
import com.RoyalNinja.GUI.SpawnEnderDragon;
import com.RoyalNinja.ItemSystem.ArmorHealthThread;
import com.RoyalNinja.ItemSystem.SetPlayerHealthThread;
import com.RoyalNinja.ItemSystem.WeaponDamage;
import com.RoyalNinja.ItemSystem.Durability.ArmorDurability;
import com.RoyalNinja.ItemSystem.Durability.WeaponDurability;
import com.RoyalNinja.LevelingSystem.ExpGainMobs;
import com.RoyalNinja.LevelingSystem.ItemLevelRequirements;
import com.RoyalNinja.Mobs.MobActionBarHealth.ShowMobHealth;
import com.RoyalNinja.Mobs.MobConfig.RemoveConfigMob;
import com.RoyalNinja.Mobs.MobDrops.MobItemDrops;
import com.RoyalNinja.Mobs.MobRaceAbilities.AcolyteAbilities;
import com.RoyalNinja.Mobs.MobRaceAbilities.AeresAbilities;
import com.RoyalNinja.Mobs.MobRaceAbilities.AndeddoAbilities;
import com.RoyalNinja.Mobs.MobRaceAbilities.GiantAbilities;
import com.RoyalNinja.Mobs.MobRaceAbilities.HopelessAbilities;
import com.RoyalNinja.Mobs.MobRaceAbilities.OrcAbilities;
import com.RoyalNinja.Mobs.MobRaceAbilities.PixieAbilities;
import com.RoyalNinja.Mobs.MobRaceAbilities.SphinxAbilities;
import com.RoyalNinja.Mobs.MobStats.MobDamage;
import com.RoyalNinja.Mobs.MobTitleAbilities.BeastlyAbilities;
import com.RoyalNinja.Mobs.MobTitleAbilities.GodlyAbilities;
import com.RoyalNinja.Mobs.MobTitleAbilities.SilentAbilities;
import com.RoyalNinja.Mobs.MobTitleAbilities.UnholyAbilities;
import com.RoyalNinja.MySQL.PlayerEconomySetup;
import com.RoyalNinja.MySQL.PlayerLevelSetup;

public class Main extends JavaPlugin {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public static Plugin plugin;

	public static int OrcAbilityTimer;
	public static int BeastlyAbilityTimer;
	public static int GodlyAbilityTimer;
	public static int AndeddoAbilityTimer;
	public static int PixieAbilityTimer;
	public static int AeresAbilityTimer;
	public static int AcolyteAbilityTimer;
	public static int HopelessAbilityTimer;
	public static int SphinxAbilityTimer;
	public static int ArmorHealthTimer;
	public static int SetPlayerHealthTimer;
	public static int BossBarTimer;
	
	
	
	@Override
	public void onEnable() {
		AeresAbilityTimer = getServer().getScheduler().scheduleAsyncRepeatingTask(this,
				new AeresAbilities(), 100l, 100l);
		OrcAbilityTimer = getServer().getScheduler().scheduleAsyncRepeatingTask(this,
			new OrcAbilities(), 100l, 100l);
		BeastlyAbilityTimer = getServer().getScheduler().scheduleAsyncRepeatingTask(this,
				new BeastlyAbilities(), 100L, 100L);
		GodlyAbilityTimer = getServer().getScheduler().scheduleAsyncRepeatingTask(this,
				new GodlyAbilities(), 100L, 100L);
		AndeddoAbilityTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new AndeddoAbilities(), 100L, 100L);
		PixieAbilityTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new PixieAbilities(), 50L, 50L);
		AcolyteAbilityTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new AcolyteAbilities(), 50L, 50L);
		HopelessAbilityTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new HopelessAbilities(), 100L, 100L);
		SphinxAbilityTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new SphinxAbilities(), 100L, 100L);
		ArmorHealthTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new ArmorHealthThread(), 20L, 20L);
		SetPlayerHealthTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new SetPlayerHealthThread(), 20L, 20L);
		BossBarTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new SpawnEnderDragon(), 20L, 20L);
		
		
		plugin = this;
		
		this.getCommand("cutscene").setExecutor(new CutsceneCommands());
		
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerEconomySetup(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SilentAbilities(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ShowMobHealth(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new RemoveConfigMob(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SaveCutsceneLocations(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MobDamage(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new UnholyAbilities(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GodlyAbilities(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new AndeddoAbilities(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GiantAbilities(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new AcolyteAbilities(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SphinxAbilities(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ChangeBlockEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MobItemDrops(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLevelSetup(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ExpGainMobs(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ItemLevelRequirements(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WeaponDamage(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WeaponDurability(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ArmorDurability(), this);


		settings.setup(this);
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.GREEN + "--------------------------------");
		console.sendMessage("                                ");
		console.sendMessage(ChatColor.AQUA + "EngraveMMO " + ChatColor.GREEN + "Enabled");
		console.sendMessage("                                ");
		console.sendMessage(ChatColor.AQUA + "Connecting to MySQL...");
		console.sendMessage("                                ");
		MySQLUtil.MySQL();
		console.sendMessage(ChatColor.GREEN + "Connected to MySQL");
		console.sendMessage("                                ");
		console.sendMessage(ChatColor.GREEN + "--------------------------------");
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("dura")) {

			Player p = (Player) sender;
			
			DurabilityUtil du = new DurabilityUtil();
			
			du.setDurability(p.getItemInHand(), 1);
			
			return true;
		}
		return true;
	}
	
	public void onDisable() {
		
		for(String key : settings.getMobData().getKeys(false)){
			settings.getMobData().set(key, null);
			settings.saveMobData();
		}
		
		for (Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			if (!(e instanceof Player)) {
				e.remove();
			}
		}
	}
	
}
