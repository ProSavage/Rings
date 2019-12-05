package me.oribuin.commands;

import me.oribuin.gui.RingsMenu;
import me.oribuin.main.Rings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RingsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // If the command sender is a Player
        if (sender instanceof Player) {
            // Get the player
            Player player = (Player) sender;

            // Create Menu variable
            RingsMenu menu = new RingsMenu();
            // Add items
            menu.initializeItems();

            // Open the GUI
            menu.onInventory(player);
            // If sender isn't a player
        } else {
            // Send message to console
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Rings] You cannot use this command in console.");
        }
        return true;
    }
}
