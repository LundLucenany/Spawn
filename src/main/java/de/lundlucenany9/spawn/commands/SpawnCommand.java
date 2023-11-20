package de.lundlucenany9.spawn.commands;

import de.lundlucenany9.spawn.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player) && args.length == 0){
            sender.sendMessage(Spawn.mNotPlayer);
            return false;
        }
        if(args.length >= 1 && sender.hasPermission(Spawn.sendPermission)){
            if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                sender.sendMessage(Spawn.mPlayerNotOnline);
                return false;
            }
            Bukkit.getPlayer(args[0]).teleport(Spawn.spawnLocation);
            Bukkit.getPlayer(args[0]).sendMessage(Spawn.mTeleport);
            sender.sendMessage(Spawn.mPlayerTeleport.replace("$PLAYER$", Bukkit.getPlayer(args[0]).getDisplayName()));
            return true;
        }
        Player p = (Player) sender;
        p.teleport(Spawn.spawnLocation);
        p.sendMessage(Spawn.mTeleport);
        return true;
    }
}
