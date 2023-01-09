package space.glowberry.fireworks.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.TabExecutor;

import java.util.Objects;

public interface CommandHandler extends TabExecutor {

    boolean canHandle(String[] args);

    void sendHelp();

    default String translate(String msg){
        if(msg == null){
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
