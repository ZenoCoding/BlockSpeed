package me.tycho.BlockSpeed;

import org.bukkit.plugin.java.JavaPlugin;

import me.tycho.BlockSpeed.commands.MainCommand;
import me.tycho.BlockSpeed.listeners.BlockListener;

import java.util.*;

public class Main extends JavaPlugin {
	public static Boolean enabled = false;
	public static Integer milestone = 0;
	public static Integer cap = 0;
	public static HashMap<String, Integer> blocksbroken = new HashMap<>();
	
	@Override
	public void onEnable() {
		new MainCommand(this);
		new BlockListener(this);
	}
}
