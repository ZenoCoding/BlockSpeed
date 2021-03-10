package me.tycho.BlockSpeed.commands;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.google.common.primitives.Ints;

import me.tycho.BlockSpeed.Main;
import me.tycho.BlockSpeed.TabCompleters.MainTab;


public class MainCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	
	public MainCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("bs").setExecutor(this);
		plugin.getCommand("bs").setTabCompleter(new MainTab());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(args.length == 0) {
			p.sendMessage(ChatColor.DARK_RED + "This is an invalid command. Try /bs help for more info.");

		} else if (args[0].equals("start")) {
			if(Main.enabled == true) { 
				p.sendMessage(ChatColor.DARK_RED + "BlockSpeed is already initialized.");
			} else {
				if(args.length == 1) {
					p.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "BlockSpeed has been initialized with the default milestone of 50, and with no cap.");
					Main.enabled = true;
					Main.milestone = 50;
					Main.cap = -1;
					return false;
				}
				if (Ints.tryParse(args[1]) == null ||
						Ints.tryParse(args[1]) < 1 ) {
					p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Milestone and Cap values must be both above one and an integer.");
					return false;
			    } else if (args.length == 2) {
					p.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "BlockSpeed has been initialized with a milestone of " + args[1] + ", and with no cap.");
					Main.enabled = true;
					Main.milestone = Ints.tryParse(args[1]);
					Main.cap = -1;
					return false;
				} else if(Ints.tryParse(args[2]) == null || Ints.tryParse(args[2]) < 1 ) {
					p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Milestone and Cap values must be both above one and an integer.");
					return false;
				} else if (args.length == 3){
					p.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "BlockSpeed has been initialized with a milestone of " + args[1] + ", and a cap of " + args[2] + ".");
					Main.enabled = true;
					Main.milestone = Ints.tryParse(args[1]);
					Main.cap = Ints.tryParse(args[2]);
				} else {
					p.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have specified too many arguments!! Type /bs help for more info.");
				}
				p.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "BlockSpeed has been initialized.");
				Main.enabled = true;
				Main.milestone = Ints.tryParse(args[1]);
			}
		} else if (args[0].equals("stop")) {
			if(Main.enabled == false) {
				p.sendMessage(ChatColor.DARK_RED + "BlockSpeed is already off.");
			} else {
				p.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "BlockSpeed has been terminated, and the speed effect has been removed from all players.");
				List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
				for(Integer i = 0;i < list.size();i++) {
					list.get(i).removePotionEffect(PotionEffectType.SPEED);
					list.get(i).removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				}
				Main.enabled = false;
			}
		} else if (args[0].equals("help")) {
			p.sendMessage(ChatColor.YELLOW + "Help for BlockSpeed.");
			p.sendMessage(ChatColor.GREEN + "BlockSpeed is a mechanic where every time you break a certain amount of blocks, you get the speed effect, forever. The more blocks you break the more speed you get. If you die, your counter resets. It is a good fit for UHC events, and people looking for a twist in their survival.");
			p.sendMessage(ChatColor.RED + "Commands:");
			p.sendMessage(ChatColor.GREEN + "/bs start <milestone> <cap>. This initializes the mechanic. The milestone is how many blocks you need to break to get the speed effect, and how many needed to increment it. The cap is how many times you can do this, and when you reach the cap, you will get resistance two. Both the milestone and cap are optional, and  will be set to their defaults if not specified.");
			p.sendMessage(ChatColor.GREEN + "To make it so that there is no cap, simply set the cap as -1. This will let the plugin know that the cap value will not be in effect.");
			p.sendMessage(ChatColor.GREEN + "/bs stop Stops the mechanic. Other plugins and features will work as well, such as /plugman unload or restarting the server.");
			p.sendMessage(ChatColor.GREEN + "/bs help Shows this message!");
			p.sendMessage(ChatColor.YELLOW + "Aliases:");
			p.sendMessage(ChatColor.GREEN + "blockspeed, bs");
			p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Note: The mechanic will reset if you reload the plugin or the server.");
		} else {
			p.sendMessage(ChatColor.DARK_RED + "This is an invalid command. Try /bs help for more info.");
		}
		return false;
	}
}
