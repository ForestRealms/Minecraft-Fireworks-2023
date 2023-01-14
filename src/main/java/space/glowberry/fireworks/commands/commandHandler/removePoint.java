package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.classes.*;
import space.glowberry.fireworks.commands.CommandHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class removePoint implements CommandHandler {

    private CommandSender sender;
    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public boolean canHandle(String[] args) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name) && args.length >= 2);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /fw removePoint <loopName> <pointName1> [pointName2] ...
        this.sender = sender;

        if(args.length == 1 || args.length == 2){
            sendHelp();
            return true;
        }

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

        if(pointNameList.size() >= loop.getPointNameList().size()){
            sender.sendMessage(translate(Factory.getLanguage().getString("AtLeastOneOfPointShouldBeReserved")));
            return true;
        }

        for (String pointName : pointNames) {
            if(!pointNameList.contains(pointName)){
                String message = Factory.getLanguage().getString("PointNotExistInLoop");
                assert message != null;
                message = message.replaceAll("%pointName%", pointName).replaceAll("%loopName%", loopName);
                sender.sendMessage(translate(message));
                continue;
            }
            loop.removePoint(pointName);
            String message = Factory.getLanguage().getString("success-on-removePoint");
            assert message != null;
            message = message.replaceAll("%loopName%", loopName).replaceAll("%pointName%", pointName);
            sender.sendMessage(translate(message));
            try {
                Main.configManager.saveAllLoops();
            } catch (IOException e) {
                message = Factory.getLanguage().getString("IO-Exception");
                sender.sendMessage(translate(message));
                return true;
            }
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2){
            return LoopPool.getInstance().getNameList();
        }

        if(args.length >= 3){
            Loop loop = LoopPool.getInstance().getLoop(args[1]);
            if(loop == null){
                return null;
            }
            List<String> pointNames = new LinkedList<>(Arrays.asList(args));
            pointNames.remove(0);
            pointNames.remove(0);


            List<String> nameList = loop.getPointNameList();
            List<String> result = new LinkedList<>(nameList);
            for (String pointName : pointNames) {
                result.remove(pointName);
            }
            return result;
        }

        return null;
    }
}
