package space.glowberry.fireworks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;

public class utils {

    private static final String version = "2.0-Beta";

    public static String getVersion(){
        return version;
    }

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
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    /**
     * Get the default configuration file Instance
     * @return The configuration file
     */
    public File getConfigurationFile(){
        return new File(Main.DataFolder, "config.yml");
    }
}
