package me.nyrethaannounce;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class NyrethaAnnounce extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("NyrethaAnnounce enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("announce")) {

            if (!sender.hasPermission("announce.use")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /announce <message>");
                return true;
            }

            String message = String.join(" ", args);

            getServer().broadcastMessage(
                    ChatColor.DARK_GRAY + "[" +
                    ChatColor.AQUA + "ANNOUNCEMENT" +
                    ChatColor.DARK_GRAY + "] " +
                    ChatColor.WHITE + message
            );

            return true;
        }

        return false;
    }
}
