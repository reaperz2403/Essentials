package reaperz.plugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import reaperz.plugin.utils.FileBuilder;

public class EVENT_Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Integer joined = 0;

        FileBuilder file = new FileBuilder("plugins//Plugin//Players//"+e.getPlayer().getUniqueId().toString(), "database");
        file.setValue("joined", joined +1);

    }


}
