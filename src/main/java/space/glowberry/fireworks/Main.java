package space.glowberry.fireworks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import space.glowberry.fireworks.commands.fw;
import space.glowberry.fireworks.controller.ConfigManager;
import space.glowberry.fireworks.controller.Factory;

import java.io.File;
import java.util.Objects;


public final class Main extends JavaPlugin {
    public static File DataFolder;
    public static Plugin plugin;
    public static ConfigManager configManager;

    @Override
    public void onEnable() {
        utils.OutputConsoleMessage(utils.translate("&aEnabling Minecraft Fireworks Plugin"));
        plugin = this;
        DataFolder = getDataFolder();
        saveResource("config.yml", false);
        saveResource("zh-cn.yml", false);
        utils.OutputConsoleMessage(Factory.getLanguage().getString("CheckingConfigurationFile"));
        configManager = new ConfigManager(Factory.getConfigFile(), Factory.getConfig());
        Bukkit.getPluginCommand("fw").setExecutor(new fw());
        Objects.requireNonNull(Bukkit.getPluginCommand("fireworks")).setExecutor(new fw());
    }

    @Override
    public void onDisable() {
        utils.OutputConsoleMessage(utils.translate("&cDisabling Fireworks Plugin"));
    }
}
