package reaperz.plugin.main;

import com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import reaperz.plugin.cmds.CMD_nick;
import reaperz.plugin.listener.EVENT_Join;
import reaperz.plugin.utils.Nick;

public class main extends JavaPlugin {

    private static main instance;
    public static main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.instance = this;
        instCommands();
        instListener();
        System.out.println("HAAAAAAAAAAAAALLLLOOOOO");
        Nick.name.equals(Nick.getField(GameProfile.class, "name"));
    }

    @Override
    public void onDisable() {

    }

    public void instCommands() {
        getCommand("nick").setExecutor(new CMD_nick());
    }
    public void instListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EVENT_Join(), this);
    }
}
