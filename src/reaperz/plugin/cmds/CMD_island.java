package reaperz.plugin.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_island implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("island")) {
            if(p.hasPermission("")) {

            }
        }

        return false;
    }
}
