package me.nyrethaannounce;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnnounceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("announce.use")) {
            sender.sendMessage("§cNo permission!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /announce <message>");
            return true;
        }

        String raw = String.join(" ", args);
        String message = ChatColor.translateAlternateColorCodes('&', raw);
        String title = ChatColor.translateAlternateColorCodes('&', 
            NyrethaAnnounce.getInstance().getConfig().getString("title", "&b&lNYRETHA"));

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendTitle(title, message, 10, 70, 20);
            p.playSound(p.getLocation(), Sound.valueOf(
                NyrethaAnnounce.getInstance().getConfig().getString("sound", "ENTITY_ENDER_DRAGON_GROWL")), 1f, 1f);
        });

        Bukkit.broadcastMessage("§8[§b§lANNOUNCE§8] §r" + message);

        return true;
    }
    }
