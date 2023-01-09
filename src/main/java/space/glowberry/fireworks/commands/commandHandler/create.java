package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.classes.FireworkProperty;
import space.glowberry.fireworks.classes.Point;
import space.glowberry.fireworks.classes.PointPool;
import space.glowberry.fireworks.commands.CommandHandler;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.controller.ConfigManager;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class create implements CommandHandler {

    private CommandSender sender;
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        // fw create <pointName>
        System.out.println(Arrays.toString(args));
        this.sender = sender;
        if(!sender.hasPermission(Objects.requireNonNull(Factory.getConfig().getString("permission")))){
            sender.sendMessage(translate(Factory.getLanguage().getString("NoPermission")));
            return true;
        }
        if(!(sender instanceof Player)){
            sender.sendMessage(translate(Factory.getLanguage().getString("PlayerOnly")));
            return true;
        }
        Player player = (Player) sender;
        Location location = player.getLocation();
        Point point = new Point(args[1], location);
        FireworkProperty property = new FireworkProperty();
        if(args.length == 2){
            YamlConfiguration Default = Factory.getDefaultConfig();
            List<Integer> RGB_1 = Default.getIntegerList("point.colors.color");
            Color color = Color.fromRGB(RGB_1.get(0), RGB_1.get(1), RGB_1.get(2));
            List<Integer> RGB_2 = Default.getIntegerList("point.fade_colors.fade_color");
            Color f_color = Color.fromRGB(RGB_2.get(0), RGB_2.get(1), RGB_2.get(2));
            property.setColor(color);
            property.setFadeColor(f_color);
            property.setFlicker(Default.getBoolean("point.flicker"));
            property.setTrail(Default.getBoolean("point.trail"));
            property.setPower(Default.getInt("point.power"));
            property.setType(FireworkEffect.Type.valueOf(Default.getString("point.shape")));
            point.setProperty(property);
            PointPool.getInstance().add(point);
        }
        if(args.length == 3 && args[2].equals("randomly")){
            property.randomize(true, true);
            point.setProperty(property);
            PointPool.getInstance().add(point);
        }
        try {
            Main.configManager.saveAllPoints();
            String message = Factory.getLanguage().getString("success-on-create");
            assert message != null;
            message = message.replaceAll("%pointName%", point.getName());
            sender.sendMessage(translate(message));
            return true;
        } catch (IOException e) {
            sender.sendMessage(translate(Factory.getLanguage().getString("IO-Exception")));
        }
        sendHelp();
        return true;
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        return null;
    }


    @Override
    public boolean canHandle(String[] args) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name) && (args.length == 2 || args.length == 3));
    }

    @Override
    public void sendHelp() {
        String message = Factory.getLanguage().getString("helps.create");
        assert message != null;
        sender.sendMessage(translate(message));
    }
}
