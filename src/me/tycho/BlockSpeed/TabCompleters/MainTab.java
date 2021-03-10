package me.tycho.BlockSpeed.TabCompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class MainTab implements TabCompleter{
	
	List<String> arguments = new ArrayList<String>();
	List<String> mileargs = new ArrayList<String>();
	List<String> capargs = new ArrayList<String>();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (arguments.isEmpty()) {
			arguments.add("help");
			arguments.add("start");
			arguments.add("stop");
		}
		if (mileargs.isEmpty()) {
			mileargs.add("10");
			mileargs.add("30");
			mileargs.add("50");
			mileargs.add("100");
		}
		if (capargs.isEmpty()) {
			capargs.add("-1");
			capargs.add("10");
			capargs.add("50");
		}
		
		List<String> results = new ArrayList<String>();
		if (args.length == 1) {
			for (String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					results.add(a);
				}
			}
			return results;
		} else if (args.length == 2 && args[0].equals("start")) {
			for (String b : mileargs) {
				if(b.toLowerCase().startsWith(args[1].toLowerCase())) {
					results.add(b);
				}
			}
			return results;
		} else if (args.length == 3 && args[0].equals("start")) {
			for (String c : capargs) {
				if(c.toLowerCase().startsWith(args[2].toLowerCase())) {
					results.add(c);
				}
			}
			return results;
		}
		
		return null;
	}

}
