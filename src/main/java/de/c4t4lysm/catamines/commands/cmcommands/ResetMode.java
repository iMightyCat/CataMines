package de.c4t4lysm.catamines.commands.cmcommands;

import de.c4t4lysm.catamines.CataMines;
import de.c4t4lysm.catamines.commands.CommandInterface;
import de.c4t4lysm.catamines.schedulers.MineManager;
import de.c4t4lysm.catamines.utils.mine.components.CataMineResetMode;
import de.c4t4lysm.catamines.utils.mine.mines.CuboidCataMine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ResetMode implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if (!sender.hasPermission("catamines.resetmode")) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.No-Permission"));
            return true;
        }

        if (args.length != 3) {
            sender.sendMessage(CataMines.PREFIX + "/cm resetmode <mine> <mode>");
            return true;
        }

        if (!MineManager.getInstance().getMineListNames().contains(args[1])) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.Mine.Not-Exist"));
            return true;
        }

        if (!(args[2].equalsIgnoreCase("time") || args[2].equalsIgnoreCase("percentage") || args[2].equalsIgnoreCase("both") || args[2].equalsIgnoreCase("time_percentage"))) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.Mine.Invalid-Reset-Mode"));
            return true;
        }

        CuboidCataMine mine = MineManager.getInstance().getMine(args[1]);

        // Map "both" alias to TIME_PERCENTAGE
        String modeArg = args[2].equalsIgnoreCase("both") ? "TIME_PERCENTAGE" : args[2].toUpperCase();
        mine.setResetMode(CataMineResetMode.valueOf(modeArg));

        sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Commands.Reset-Mode")
                .replaceAll("%mine%", mine.getName())
                .replaceAll("%mode%", modeArg));

        mine.save();

        return true;
    }
}
