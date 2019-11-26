package me.oribuin.main;

import me.oribuin.commands.MainCommand;
import me.oribuin.gui.RingsMenu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Rings extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("Rings").setExecutor(new MainCommand());

        RingsMenu menu = new RingsMenu();

        Bukkit.getPluginManager().registerEvents(menu, this);
        menu.initializeItems();
    }

    @Override
    public void onDisable() {

    }
}
