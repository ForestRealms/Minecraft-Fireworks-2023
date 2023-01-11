package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.classes.Loop;
import space.glowberry.fireworks.classes.LoopPool;
import space.glowberry.fireworks.classes.Point;
import space.glowberry.fireworks.classes.PointPool;
import space.glowberry.fireworks.commands.CommandHandler;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class launch implements CommandHandler {
    // fw launch <name1> [name2] ...
    private CommandSender sender;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        List<String> pointNames = new CopyOnWriteArrayList<>(Arrays.asList(args));
        pointNames.remove("launch");

        for (String pointName : pointNames) {
            if (!PointPool.getInstance().PointIsExist(pointName)) {
                String message = Factory.getLanguage().getString("PointNotExist");
                assert message != null;
                message = message.replaceAll("%pointName%", pointName);
                sender.sendMessage(translate(message));
                pointNames.remove(pointName);
            }
        }


        for (String pointName : pointNames) {
            for (Point point : PointPool.getInstance().getAll()) {

                if(point.getName().equals(pointName)){
                    point.launch();
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> pointNames = new ArrayList<>(Arrays.asList(args));
        pointNames.remove("launch");
        List<String> nameList = PointPool.getInstance().getNameList();
        List<String> result = new LinkedList<>(nameList);
        for (String pointName : pointNames) {
            result.remove(pointName);
        }
        return result;
    }


    @Override
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public boolean canHandle(String[] args) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name) && args.length >= 2);
    }

    @Override
    public void sendHelp() {
        String message = Factory.getLanguage().getString("helps.launch");
        assert message != null;
        sender.sendMessage(translate(message));
    }


}
