package cn.smallairplane.mc.stonestatue.util;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.player.PlayerLoginEvent;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class PlayerData  implements ConfigurationSerializable{
    String hostName;
    String password;

    InetAddress ip;
    InetAddress realIp;

    boolean login;
    boolean autoLogin;

    public PlayerData(String hostName, String password, InetAddress ip, InetAddress realIp, boolean login, boolean autoLogin) {
        this.hostName = hostName;
        this.password = password;
        this.ip = ip;
        this.realIp = realIp;
        this.login = login;
        this.autoLogin = autoLogin;
    }

    public PlayerData(PlayerLoginEvent event) {
        this.ip = event.getAddress();
        this.realIp = event.getRealAddress();
        this.hostName = event.getHostname();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    @Override
    public Map<String,Object> serialize() {
        Map<String,Object> map = new HashMap<>();
        map.put("hostName",hostName);
        map.put("password",password);
        map.put("ip",ip );
        map.put("realIp",realIp);
        map.put("login",login);
        map.put("autoLogin",autoLogin);
        return map;
    }



    public static PlayerData deserialize(Map<String, Object> map) {
        return new PlayerData((String) map.get("hostName"), (String) map.get("password"), (InetAddress)map.get("ip") , (InetAddress) map.get("realIp"), (boolean)map.get("login") , (boolean)map.get("autoLogin"));
    }
}
