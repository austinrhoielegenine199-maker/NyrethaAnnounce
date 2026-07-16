package me.nyrethaannounce;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NyrethaAnnounce extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("NyrethaAnnounce enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("announce")) {
            return false;
        }

        if (!sender.hasPermission("announce.use")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        // /announce reload
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();

            sender.sendMessage(
                    ChatColor.GREEN + "NyrethaAnnounce configuration reloaded!"
            );

            return true;
        }

        // /announce <message>
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /announce <message>");
            sender.sendMessage(ChatColor.RED + "Usage: /announce reload");
            return true;
        }

        String message = String.join(" ", args);

        String title = ChatColor.translateAlternateColorCodes(
                '&',
                getConfig().getString("title", "&b&lANNOUNCEMENT")
        );

        String subtitle = ChatColor.translateAlternateColorCodes(
                '&',
                getConfig().getString("subtitle", "&f%message%")
                        .replace("%message%", message)
        );

        for (Player player : getServer().getOnlinePlayers()) {

            player.sendTitle(
                    title,
                    subtitle,
                    10,
                    70,
                    20
            );

            if (getConfig().getBoolean("sound.enabled", true)) {

                try {
                    Sound sound = Sound.valueOf(
                            getConfig()
                                    .getString(
                                            "sound.name",
                                            "BLOCK_NOTE_BLOCK_PLING"
                                    )
                                    .toUpperCase()
                    );

                    float volume = (float) getConfig()
                            .getDouble("sound.volume", 1.0);

                    float pitch = (float) getConfig()
                            .getDouble("sound.pitch", 1.0);

                    player.play
