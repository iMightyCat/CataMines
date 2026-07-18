package de.c4t4lysm.catamines.commands.cmcommands;

import de.c4t4lysm.catamines.CataMines;
import de.c4t4lysm.catamines.commands.CommandInterface;
import de.c4t4lysm.catamines.schedulers.MineManager;
import de.c4t4lysm.catamines.utils.mine.components.CataMineResetMode;
import de.c4t4lysm.catamines.utils.mine.mines.CuboidCataMine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetAllResetModeCommand implements CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if (!sender.hasPermission("catamines.setallresetmode")) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.No-Permission"));
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(CataMines.PREFIX + "/cm setallresetmode <time|percentage|both>");
            return true;
        }

        String modeArg = args[1];
        if (!(modeArg.equalsIgnoreCase("time")
                || modeArg.equalsIgnoreCase("percentage")
                || modeArg.equalsIgnoreCase("both")
                || modeArg.equalsIgnoreCase("time_percentage"))) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.Mine.Invalid-Reset-Mode"));
            return true;
        }

        // Map "both" alias to TIME_PERCENTAGE
        String enumName = modeArg.equalsIgnoreCase("both") ? "TIME_PERCENTAGE" : modeArg.toUpperCase();
        CataMineResetMode mode = CataMineResetMode.valueOf(enumName);

        int count = 0;
        for (CuboidCataMine mine : MineManager.getInstance().getMines()) {
            mine.setResetMode(mode);
            mine.save();
            count++;
        }

        sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Commands.Set-All-Reset-Mode")
                .replaceAll("%mode%", enumName)
                .replaceAll("%count%", String.valueOf(count)));

        return true;
    }
}
