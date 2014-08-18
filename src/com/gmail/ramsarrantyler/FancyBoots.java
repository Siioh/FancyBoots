package com.gmail.ramsarrantyler;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public final class FancyBoots extends JavaPlugin {
	
	//Add the beginning boots as ItemStacks to be used in enabling and disabling.
	final ItemStack leatherboots = new ItemStack(Material.LEATHER_BOOTS, 1);
	final ItemStack chainboots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
	final ItemStack ironboots = new ItemStack(Material.IRON_BOOTS, 1);
	final ItemStack goldboots = new ItemStack(Material.GOLD_BOOTS, 1);
	final ItemStack diamondboots = new ItemStack(Material.DIAMOND_BOOTS, 1);
	//Now set ArrayList<String> lore for each type of boot.
	final ArrayList<String> fireboot_lore = new ArrayList<String>();
	
	@Override
	public void onEnable(){
		
		//Set the lore that all FIRE boots will have.
		fireboot_lore.add(ChatColor.DARK_PURPLE + "Active Particle Effect: Flames");
		leatherboots.getItemMeta().setLore(fireboot_lore);
		chainboots.getItemMeta().setLore(fireboot_lore);
		ironboots.getItemMeta().setLore(fireboot_lore);
		goldboots.getItemMeta().setLore(fireboot_lore);
		diamondboots.getItemMeta().setLore(fireboot_lore);
		
		//Fire particle boot recipes.
		ShapedRecipe fire_leatherboots = new ShapedRecipe(leatherboots);
		fire_leatherboots.shape("BCB", "BAB", "BCB");
		fire_leatherboots.setIngredient('A', Material.LEATHER_BOOTS);
		fire_leatherboots.setIngredient('B', Material.LEATHER);
		fire_leatherboots.setIngredient('C', Material.FLINT_AND_STEEL);
		getServer().addRecipe(fire_leatherboots);
		
		ShapedRecipe fire_chainboots = new ShapedRecipe(chainboots);
		fire_chainboots.shape("BCB", "CAC", "BCB");
		fire_chainboots.setIngredient('A', Material.CHAINMAIL_BOOTS);
		fire_chainboots.setIngredient('B', Material.CLAY_BALL);
		fire_chainboots.setIngredient('C', Material.FLINT_AND_STEEL);
		getServer().addRecipe(fire_chainboots);
		
		ShapedRecipe fire_ironboots = new ShapedRecipe(ironboots);
		fire_ironboots.shape("BCB", "CAC", "BCB");
		fire_ironboots.setIngredient('A', Material.IRON_BOOTS);
		fire_ironboots.setIngredient('B', Material.IRON_INGOT);
		fire_ironboots.setIngredient('C', Material.FLINT_AND_STEEL);
		getServer().addRecipe(fire_ironboots);
		
		ShapedRecipe fire_goldboots = new ShapedRecipe(goldboots);
		fire_goldboots.shape("BCB", "CAC", "BCB");
		fire_goldboots.setIngredient('A', Material.GOLD_BOOTS);
		fire_goldboots.setIngredient('B', Material.GOLD_INGOT);
		fire_goldboots.setIngredient('C', Material.FLINT_AND_STEEL);
		getServer().addRecipe(fire_goldboots);
		
		ShapedRecipe fire_diamondboots = new ShapedRecipe(diamondboots);
		fire_diamondboots.shape("BCB", "CAC", "BCB");
		fire_diamondboots.setIngredient('A', Material.DIAMOND_BOOTS);
		fire_diamondboots.setIngredient('B', Material.DIAMOND);
		fire_diamondboots.setIngredient('C', Material.FLINT_AND_STEEL);
		getServer().addRecipe(fire_diamondboots);	
		//Done adding recipes, now to finish enabling.
		
		getConfig().options().copyDefaults(true);
	    saveConfig();
	    new FancyBootsListener(this);
	    this.getServer().getPluginManager().registerEvents(new FancyBootsListener(this), this);
	    getLogger().info("FancyBoots has been enabled!");
	}
	
	public class FancyBootsListener implements Listener {
		public FancyBoots plugin;
		
		public FancyBootsListener(FancyBoots plugin) {
			this.plugin = plugin;
		}
		
		@EventHandler
		public void onMove(PlayerMoveEvent evt){
			Player player = evt.getPlayer();
			ItemStack boots = player.getEquipment().getBoots();
			World world = player.getWorld();
			Location loc = player.getLocation();
			if(boots.getItemMeta().hasLore()){
				if(boots.getItemMeta().getLore().equals("Active Particle Effect: Flames")){
					if (Cooldowns.tryCooldown(player, "particle", 1000)) {
	        			world.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 0);
					}
				}
			}
		}
		
	}
	@Override
	public void onDisable(){
		//Hax0rs way to remove recipes avoiding glitches.
		//ShapedRecipe fire_leatherboots = new ShapedRecipe(leatherboots);
		//fire_leatherboots.shape("CCC", "CCC", "CCC");
		//fire_leatherboots.setIngredient('C', Material.AIR);
		//getServer().addRecipe(fire_leatherboots);
		
		getLogger().info("FancyBoots has been disabled!");
	}
}
