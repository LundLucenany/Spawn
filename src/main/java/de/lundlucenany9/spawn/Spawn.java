package de.lundlucenany9.spawn;

import de.lundlucenany9.spawn.commands.SetSpawnCommand;
import de.lundlucenany9.spawn.commands.SpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Spawn extends JavaPlugin {

    public static double x;
    public static double y;
    public static double z;
    public static String worldName;
    public static World world;
    public static Location spawnLocation;
    public static Permission sendPermission;

    //messages
    public static String mNotPlayer;
    public static String mPlayerNotOnline;
    public static String mTeleport;
    public static String mPlayerTeleport;
    public static String mSpawnSet;

    @Override
    public void onEnable() {
        this.saveResource("config.yml", false);
        mSpawnSet = this.getConfig().getString("messages.spawnSet");
        mPlayerTeleport = this.getConfig().getString("messages.playerTeleported");
        mTeleport = this.getConfig().getString("messages.teleported");
        mNotPlayer = this.getConfig().getString("messages.notPlayer");
        mPlayerNotOnline = this.getConfig().getString("messages.playerNotOnline");
        x = this.getConfig().getDouble("spawn.x");
        y = this.getConfig().getDouble("spawn.y");
        z = this.getConfig().getDouble("spawn.z");
        worldName = this.getConfig().getString("spawn.world");
        assert worldName != null;
        world = Bukkit.getWorld(worldName);
        spawnLocation = new Location(world,x, y, z);
        sendPermission = new Permission("spawn.send", PermissionDefault.OP);
        //commands
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
    }

    @Override
    public void onDisable() {
        this.getConfig().set("spawn.x", x);
        this.getConfig().set("spawn.y", y);
        this.getConfig().set("spawn.z", z);
        this.getConfig().set("spawn.world", worldName);
        try {
            this.getConfig().save(this.getDataFolder() + "/config.yml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
