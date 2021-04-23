package cn.smallairplane.mc.stonestatue.event;

import cn.smallairplane.mc.stonestatue.util.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class PlayerEvent implements Listener {
    PlayerUtil playerUtil = new PlayerUtil();

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event){
        playerUtil.add(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerJoinEvent(PlayerJoinEvent event){
        playerUtil.setLogin(event.getPlayer().getName(),false);
        playerUtil.AutoLogin(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerMoveEvent(PlayerMoveEvent event){
        event.setCancelled(!playerUtil.isLogin(event.getPlayer().getName()));
    }

}
