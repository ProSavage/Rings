package me.oribuin.rings.commands;

import me.oribuin.rings.gui.RingMenu;
import me.oribuin.rings.gui.RingsMenu;
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
            new RingMenu().showGui(player);

//            // Create Menu variable
//            RingsMenu menu = RingsMenu.getInstance();
//            // Add items
//            menu.initializeItems();
//
//            // Open the GUI
//            menu.onInventory(player);
            // If sender isn't a player
        } else {
            // Send message to console
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Rings] You cannot use this command in console.");
        }
        return true;
    }
}
