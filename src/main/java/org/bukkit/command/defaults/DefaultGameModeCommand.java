package org.bukkit.command.defaults;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DefaultGameModeCommand extends VanillaCommand {
    private static final List<String> GAMEMODE_NAMES = ImmutableList.of("adventure", "creative", "survival");

    public DefaultGameModeCommand() {
        super("defaultgamemode");
        this.description = "Set the default gamemode";
        this.usageMessage = "/defaultgamemode <mode>";
        this.setPermission("bukkit.command.defaultgamemode");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length == 0) {
            sender.sendMessage("Usage: " + usageMessage);
            return false;
        }

        String modeArg = args[0];
        int value = -1;

        try {
            value = Integer.parseInt(modeArg);
        } catch (NumberFormatException ex) {}

        GameMode mode = GameMode.getByValue(value);

        if (mode == null) {
            switch (modeArg.toLowerCase()) {
                case "creative":
                case "c":
                    mode = GameMode.CREATIVE;
                    break;
                case "adventure":
                case "a":
                    mode = GameMode.ADVENTURE;
                    break;
                default:
                    mode = GameMode.SURVIVAL;
            }
        }

        Bukkit.getServer().setDefaultGameMode(mode);
        Command.broadcastCommandMessage(sender, "Default game mode set to " + mode.toString().toLowerCase());

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], GAMEMODE_NAMES, new ArrayList<String>(GAMEMODE_NAMES.size()));
        }

        return ImmutableList.of();
    }
}
