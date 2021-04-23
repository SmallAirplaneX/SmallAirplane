package cn.smallairplane.mc.stonestatue.util;

import cn.smallairplane.mc.stonestatue.StoneStatue;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlayerUtil {
    static HashMap<String , PlayerData> player = new HashMap<>();
    File file = new File(StoneStatue.getInstance().getDataFolder(), "playerData.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static HashMap<String, PlayerData> getPlayer() {
        return player;
    }

    public PlayerUtil() {
        for (String name: cfg.getKeys(false)){
            player.put(name,(PlayerData) cfg.get(name));
        }
    }

    public void add(PlayerLoginEvent event) {

        player.put(event.getPlayer().getName(),new PlayerData(event));
        save();
    }

    public void setLogin(String name,boolean b) {
        if (player.containsKey(name)){
            player.get(name).setLogin(b);
        }
    }

    public boolean isLogin(String name) {
        if (player.containsKey(name)){
            return player.get(name).isLogin();
        }
        return true;
    }

    public void AutoLogin(PlayerJoinEvent event) {

    }
    public void save(){
        cfg.set();

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
