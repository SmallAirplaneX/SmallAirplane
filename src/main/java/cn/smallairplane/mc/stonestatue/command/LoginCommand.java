package cn.smallairplane.mc.stonestatue.command;

import cn.smallairplane.mc.stonestatue.util.PlayerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LoginCommand implements CommandExecutor {
    PlayerUtil playerUtil = new PlayerUtil();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        playerUtil.setLogin(sender.getName(),true);
        return false;
    }
}