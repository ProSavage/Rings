package me.oribuin.main;

import me.oribuin.commands.RingsCommand;
import me.oribuin.gui.RingsMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Rings extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "=============================\n" +
                ChatColor.YELLOW + "Plugin: " + ChatColor.WHITE + "Rings \n" +
                ChatColor.YELLOW + "Version: " + ChatColor.WHITE + "1.0.0 \n" +
                ChatColor.YELLOW + "Author: " + ChatColor.WHITE + "Oribuin \n" +
                ChatColor.WHITE + "=============================");

        getCommand("Rings").setExecutor(new RingsCommand());
        RingsMenu menu = RingsMenu.getInstance();

        Bukkit.getPluginManager().registerEvents(menu, this);

    }

    @Override
    public void onDisable() {

    }
}
