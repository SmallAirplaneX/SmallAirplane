package cn.smallairplane.mc.stonestatue;

import cn.smallairplane.mc.stonestatue.command.LoginCommand;
import cn.smallairplane.mc.stonestatue.command.RegisterCommand;
import cn.smallairplane.mc.stonestatue.event.PlayerEvent;
import cn.smallairplane.mc.stonestatue.util.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public final class StoneStatue extends JavaPlugin {
    /**
     * 此插件的实例
     */
    private static StoneStatue instance = null;

    /**
     * 配置信息
     */
    HashMap<String,Object> config = new HashMap<>();
    /**
     * 返回插件的实例
     *
     * @return 插件实例
     */
    public static StoneStatue getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        //初始化
        initialize();
    }

    private void initialize() {
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(),this);
        Objects.requireNonNull(getCommand("register")).setExecutor(new RegisterCommand());
        Objects.requireNonNull(getCommand("login")).setExecutor(new LoginCommand());
        ConfigurationSerialization.registerClass(PlayerData.class);
        initConfig();
        upData();
    }

    private void upData() {
    }

    private void initConfig() {
        saveDefaultConfig();

        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", true);
        }
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
        config.clear();
        for (String name : cfg.getConfigurationSection("StoneStatue").getKeys(false)) {
            boolean v = cfg.getBoolean("StoneStatue." + name);
            config.put(name, v);
        }
        if ((boolean)config.get("whetherToEnable")) {
            getLogger().info("配置文件已载入");
        }
    }

    @Override
    public void onDisable() {
        instance =null;
    }

}
