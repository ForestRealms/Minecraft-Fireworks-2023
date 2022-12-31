package space.glowberry.fireworks;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class Main extends JavaPlugin {
    public static File DataFolder;

    @Override
    public void onEnable() {
        utils.OutputConsoleMessage(utils.translate("&aEnabling Minecraft Fireworks Plugin"));
        DataFolder = getDataFolder();

    }

    @Override
    public void onDisable() {
        utils.OutputConsoleMessage(utils.translate("&cDisabling Fireworks Plugin"));
    }
}
