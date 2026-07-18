package de.c4t4lysm.catamines.commands.cmcommands;

import de.c4t4lysm.catamines.CataMines;
import de.c4t4lysm.catamines.commands.CommandInterface;
import de.c4t4lysm.catamines.schedulers.MineManager;
import de.c4t4lysm.catamines.utils.mine.mines.CuboidCataMine;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetAllMineCommand implements CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if (!sender.hasPermission("catamines.setallmine")) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.No-Permission"));
            return true;
        }

        // /cm setallmine timer <seconds>
        // /cm setallmine percentage <percentage>
        if (args.length != 3) {
            sender.sendMessage(CataMines.PREFIX + "/cm setallmine timer <seconds>");
            sender.sendMessage(CataMines.PREFIX + "/cm setallmine percentage <percentage>");
            return true;
        }

        String subCommand = args[1].toLowerCase();

        switch (subCommand) {
            case "timer":
            case "time": {
                int seconds;
                try {
                    seconds = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.Not-Number"));
                    return true;
                }

                if (seconds <= 0 || seconds > 1000000) {
                    sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.Mine.Invalid-Range")
                            .replaceAll("%start%", "1")
                            .replaceAll("%end%", "1000000"));
                    return true;
                }

                int count = 0;
                for (CuboidCataMine mine : MineManager.getInstance().getMines()) {
                    mine.setResetDelay(seconds);
                    mine.setCountdown(seconds);
                    mine.save();
                    count++;
                }

                sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Commands.Set-All-Mine-Timer")
                        .replaceAll("%seconds%", String.valueOf(seconds))
                        .replaceAll("%count%", String.valueOf(count)));
                break;
            }

            case "percentage": {
                String percentArg = args[2];
                if (percentArg.endsWith("%")) percentArg = StringUtils.chop(percentArg);

                double percentage;
                try {
                    percentage = Double.parseDouble(percentArg);
                } catch (NumberFormatException e) {
                    sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.Not-Number"));
                    return true;
                }

                if (percentage < 0 || percentage > 100) {
                    sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.Mine.Invalid-Range")
                            .replaceAll("%start%", "0.0")
                            .replaceAll("%end%", "100.0"));
                    return true;
                }

                int count = 0;
                for (CuboidCataMine mine : MineManager.getInstance().getMines()) {
                    mine.setResetPercentage(percentage);
                    mine.save();
                    count++;
                }

                sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Commands.Set-All-Mine-Percentage")
                        .replaceAll("%percentage%", String.valueOf(percentage))
                        .replaceAll("%count%", String.valueOf(count)));
                break;
            }

            default:
                sender.sendMessage(CataMines.PREFIX + "/cm setallmine timer <seconds>");
                sender.sendMessage(CataMines.PREFIX + "/cm setallmine percentage <percentage>");
                break;
        }

        return true;
    }
}
