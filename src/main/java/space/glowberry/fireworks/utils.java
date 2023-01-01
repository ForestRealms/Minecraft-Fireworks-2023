package space.glowberry.fireworks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class utils {

    private static final String version = "2.0-Beta";

    public static String getVersion(){
        return version;
    }
    public static YamlConfiguration config = getConfiguration();

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
    public static File getConfigurationFile(){
        return new File(Main.DataFolder, "config.yml");
    }

    /**
     * Load the configuration file
     * @return YamlConfiguration file
     */
    private static YamlConfiguration getConfiguration(){
        return YamlConfiguration.loadConfiguration(getConfigurationFile());
    }

    /**
     * Save the configuration file
     * @throws IOException If I/O problems raised.
     */
    public static void saveConfiguration() throws IOException {
        config.save(getConfigurationFile());
    }
}
