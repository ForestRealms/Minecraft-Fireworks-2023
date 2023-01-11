package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.classes.*;
import space.glowberry.fireworks.commands.CommandHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class addPoint implements CommandHandler {

    private CommandSender sender;
    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public boolean canHandle(String[] args) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name) && args.length >= 3);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /fw addPoint <loopName> <pointName1> [pointName2] ...
        this.sender = sender;

        String loopName = args[1];
        if (!LoopPool.getInstance().LoopIsExist(loopName)) {
            String message = Factory.getLanguage().getString("LoopNotExist");
            assert message != null;
            message = message.replaceAll("%loopName%", loopName);
            sender.sendMessage(translate(message));
            return true;
        }

        Loop loop = LoopPool.getInstance().getLoop(loopName);

        if(loop.getLoopState() == LoopState.WORKING){
            String message = Factory.getLanguage().getString("LoopIsWorking");
            assert message != null;
            message = message.replaceAll("%loopName%", loopName);
            sender.sendMessage(translate(message));
            return true;
        }

        List<String> pointNames = new CopyOnWriteArrayList<>(args);
        pointNames.remove(0);
        pointNames.remove(0);
        for (String pointName : pointNames) {
            if(!PointPool.getInstance().PointIsExist(pointName)){
                String message = Factory.getLanguage().getString("PointNotExist");
                assert message != null;
                message = message.replaceAll("%pointName%", pointName);
                sender.sendMessage(translate(message));
                pointNames.remove(pointName);
            }
        }

        List<String> pointNameList = new ArrayList<>();
        for (Point point : loop.getPoints()) {
            pointNameList.add(point.getName());
        }

        for (String pointName : pointNames) {
            Point point = PointPool.getInstance().getPoint(pointName);
            if(pointNameList.contains(pointName)){
                String message = Factory.getLanguage().getString("PointAlreadyExistInLoop");
                assert message != null;
                message = message.replaceAll("%pointName%", pointName).replaceAll("%loopName%", loopName);
                sender.sendMessage(translate(message));
                return true;
            }
            loop.addPoint(point);
            try {
                Main.configManager.saveAllLoops();
            } catch (IOException e) {
                String message = Factory.getLanguage().getString("IO-Exception");
                sender.sendMessage(translate(message));
            }
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
