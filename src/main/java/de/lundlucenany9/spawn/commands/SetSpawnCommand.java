package de.lundlucenany9.spawn.commands;

import de.lundlucenany9.spawn.Spawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Spawn.mNotPlayer);
            return false;
        }
        Player p = (Player) sender;
        Spawn.spawnLocation = p.getLocation();
        Spawn.x = Spawn.spawnLocation.getX();
        Spawn.y = Spawn.spawnLocation.getY();
        Spawn.z = Spawn.spawnLocation.getZ();
        Spawn.world = Spawn.spawnLocation.getWorld();
        Spawn.worldName = Spawn.spawnLocation.getWorld().getName();
        p.sendMessage(Spawn.mSpawnSet);
        return true;
    }
}
