package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.classes.Loop;
import space.glowberry.fireworks.classes.LoopPool;
import space.glowberry.fireworks.classes.Point;
import space.glowberry.fireworks.commands.CommandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class loops implements CommandHandler {

    private CommandSender sender;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // fw loops
        this.sender = sender;

        if(args.length > 1){
            sendHelp();
            return true;
        }

        List<String> messages = new ArrayList<>();
        YamlConfiguration language = Factory.getLanguage();

        for (Loop loop : LoopPool.getInstance().getAll()) {
            List<String> m = language.getStringList("loops");
            List<Point> points = loop.getPoints();
            String[] pointNames = new String[points.size()];
            for (int i = 0; i < pointNames.length; i++) {
                pointNames[i] = points.get(i).getName();
            }
            m.replaceAll(s -> s
                    .replaceAll("%loopName%", loop.getName())
                    .replaceAll("%pointNames%", Arrays.toString(pointNames))
                    .replaceAll("%loopState%", loop.getLoopState().toString())
                    .replaceAll("%ticks%", String.valueOf(loop.getInterval()))
                    .replaceAll("%second%", String.valueOf(loop.getInterval()/20.0)));
            messages.addAll(m);
        }

        for (String message : messages) {
            sender.sendMessage(translate(message));
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }


    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public boolean canHandle(String[] args) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name));
    }

    @Override
    public void sendHelp() {

    }
}
