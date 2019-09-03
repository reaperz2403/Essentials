package reaperz.plugin.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import reaperz.plugin.utils.Nick;

public class CMD_nick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("nick")) {
            Nick.nickPlayer((Player) sender, args[0]);
            sender.sendMessage("you are now nicked as §6" + args[0]);
        }

        return false;
    }
}
