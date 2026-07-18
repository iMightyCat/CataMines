package de.c4t4lysm.catamines.commands.cmcommands;

import de.c4t4lysm.catamines.CataMines;
import de.c4t4lysm.catamines.commands.CommandInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ToggleAutoInvCommand implements CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if (!sender.hasPermission("catamines.toggleautoinv")) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Error-Messages.No-Permission"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(CataMines.PREFIX + "/cm toggleautoinv");
            return true;
        }

        boolean current = CataMines.getInstance().isAutoInvEnabled();
        CataMines.getInstance().setAutoInvEnabled(!current);

        if (CataMines.getInstance().isAutoInvEnabled()) {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Commands.Toggle-Auto-Inv.Enabled"));
        } else {
            sender.sendMessage(CataMines.PREFIX + CataMines.getInstance().getLangString("Commands.Toggle-Auto-Inv.Disabled"));
        }

        return true;
    }
}
