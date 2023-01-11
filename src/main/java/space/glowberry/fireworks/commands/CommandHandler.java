package space.glowberry.fireworks.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import space.glowberry.fireworks.Factory;

import java.util.Objects;

public interface CommandHandler extends TabExecutor {

    CommandSender getSender();

    boolean canHandle(String[] args);

    default void sendHelp(){
        getSender().sendMessage(translate("&c&l ========== &dCommand Help &c&l =========="));
        String[] split = this.getClass().getName().split("\\.");
        String message = Factory.getLanguage().getString("helps." + split[split.length - 1]);
        assert message != null;
        getSender().sendMessage(translate(message));
        getSender().sendMessage(translate("&c&l ========================================="));
    }
    default String translate(String msg){
        if(msg == null){
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
