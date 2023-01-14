package space.glowberry.fireworks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class utils {


    /**
     * Translate the message string with alternative char "&"
     * @param msg The message to translate
     * @return the translated message
     */
    public static String translate(String msg){
        if(msg == null){
            return "";
        }else{
            return ChatColor.translateAlternateColorCodes('&',msg);
        }
    }

    /**
     * Output a message in the console interface
     * @param msg The message expected to show in the console interface
     */
    public static void OutputConsoleMessage(String msg){
        Bukkit.getConsoleSender().sendMessage(translate(msg));
    }



}
