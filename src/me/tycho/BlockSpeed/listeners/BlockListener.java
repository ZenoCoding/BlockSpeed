package me.tycho.BlockSpeed.listeners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.tycho.BlockSpeed.Main;

public class BlockListener implements Listener{

	public Main plugin;
	
	public BlockListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void BlockListener(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(Main.enabled == false) {
			return;
		}
		
		Collection<Material> disabled = new ArrayList<Material>();
		disabled.add(Material.FLOWER_POT);
		disabled.add(Material.CORNFLOWER);
		disabled.add(Material.WITHER_ROSE);
		disabled.add(Material.SUGAR_CANE);
		disabled.add(Material.ROSE_BUSH);
		disabled.add(Material.SUGAR_CANE);
		disabled.add(Material.SUNFLOWER);
		disabled.add(Material.SUGAR_CANE);
		disabled.add(Material.AIR);
		disabled.add(Material.VINE);
		for(Material i : disabled) {
			if(e.getBlock().getType() == i) {
				return;
			} else if(e.getBlock().getType().toString().toLowerCase().contains("leaves")) {
				return;
			} else if(e.getBlock().getType().toString().toLowerCase().contains("sapling")) {
				return;
			} else if(e.getBlock().getType().toString().toLowerCase().contains("flower")) {
				return;
			} else if(e.getBlock().getType().toString().toLowerCase().contains("grass")) {
				return; 
			} else if(e.getBlock().getType().toString().toLowerCase().contains("flower")) {
				return;
			} else if(e.getBlock().getType().toString().toLowerCase().contains("pot")) {
				return;
			}
		}
		
		if(Main.blocksbroken.containsKey(p.getDisplayName())) {
			Integer a = Main.blocksbroken.get(p.getDisplayName());
			Main.blocksbroken.replace(p.getDisplayName(), a+1);
			
			if(Main.blocksbroken.get(p.getDisplayName()) >= Main.milestone) {
				Main.blocksbroken.replace(p.getDisplayName(), 0);
				Integer effectstrength = -1;
				Collection<PotionEffect> effects = p.getActivePotionEffects();
				Object[] effects2 = effects.toArray();
				for(Integer i = 0;i < effects2.length;i++) {
					PotionEffect effect = (PotionEffect) effects2[i];
					if(effect.getType().getName().equals("SPEED")) {
						effectstrength = effect.getAmplifier();
					}
				}
				if(effectstrength+1 < Main.cap && Main.cap != -1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, effectstrength+1));
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have broken " + String.valueOf((effectstrength+2)*Main.milestone) + " blocks, and now have a speed effect of " + String.valueOf(effectstrength+2));
				} else {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have reached the cap for breaking speed. Here's a reward for going all the way.");
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
				}
			}
		} else {
			Main.blocksbroken.put(p.getDisplayName(), 1);
		}
	}
}
