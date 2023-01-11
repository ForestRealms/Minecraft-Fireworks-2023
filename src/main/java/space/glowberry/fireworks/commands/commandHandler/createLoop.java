package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.Exceptions.ZeroIntervalError;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.classes.Loop;
import space.glowberry.fireworks.classes.LoopPool;
import space.glowberry.fireworks.classes.PointPool;
import space.glowberry.fireworks.commands.CommandHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class createLoop implements CommandHandler {
    private CommandSender sender;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //fw createLoop <LoopName> interval=? <point1> [point2] ...
        this.sender = sender;
        if(args.length < 4){
            sendHelp();
            return true;
        }
        if (!args[2].split("=")[0].equals("interval")){
            sendHelp();
            return true;
        }
        try {
            Integer.valueOf(args[2].split("=")[1]);
        }catch (NumberFormatException e){
            sendHelp();
            return true;
        }


        String loopName = args[1];
        if(LoopPool.getInstance().LoopIsExist(loopName)){
            String message = Factory.getLanguage().getString("LoopAlreadyExist");
            assert message != null;
            message = message.replaceAll("%loopName%", loopName);
            sender.sendMessage(translate(message));
            return true;
        }
        List<String> pointNames = new CopyOnWriteArrayList<>(Arrays.asList(args));
        pointNames.remove(loopName);
        pointNames.remove("createLoop");
        pointNames.remove(0);
        for (String pointName : pointNames) {
            if (!PointPool.getInstance().PointIsExist(pointName)) {
                String message = Factory.getLanguage().getString("PointNotExist");
                assert message != null;
                message = message.replaceAll("%pointName%", pointName);
                sender.sendMessage(translate(message));
                pointNames.remove(pointName);
            }
        }

        Loop loop;
        try {
            loop = new Loop(
                    loopName,
                    PointPool.getInstance().getPoints(pointNames, CopyOnWriteArrayList.class), Integer.parseInt(args[2].split("=")[1])
            );
        } catch (IllegalArgumentException | InstantiationException | IllegalAccessException exception){
            String message = Factory.getLanguage().getString("failed-on-createLoop");
            assert message != null;
            message = message.replaceAll("%loopName%", loopName);
            sender.sendMessage(translate(message));
            return true;
        } catch (ZeroIntervalError e) {
            String message = Factory.getLanguage().getString("ZeroInterval");
            assert message != null;
            sender.sendMessage(translate(message));
            return true;
        }

        LoopPool.getInstance().add(loop);

        try {
            Main.configManager.saveAllLoops();
            String message = Factory.getLanguage().getString("success-on-createLoop");
            assert message != null;
            message = message.replaceAll("%loopName%", loop.getName());
            sender.sendMessage(translate(message));
            return true;
        } catch (IOException e) {
            sender.sendMessage(translate(Factory.getLanguage().getString("IO-Exception")));

        }
        sendHelp();
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //fw createLoop <LoopName> interval=? <point1> [point2] ...
        List<String> result = new LinkedList<>();
        switch (args.length){
            case 2:
                result.add("<loopName>");
                return result;
            case 3:
                result.add("interval=");
                return result;
        }
        if(args.length >= 4){
            List<String> pointNames = new ArrayList<>(Arrays.asList(args));
            pointNames.remove(0);
            pointNames.remove(0);
            pointNames.remove(0);
            List<String> nameList = PointPool.getInstance().getNameList();
            result.addAll(nameList);
            for (String pointName : pointNames) {
                result.remove(pointName);
            }
            return result;
        }
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
        return (Objects.equals(args[0], name) && (args.length >= 2));
    }

}
