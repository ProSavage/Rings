package me.oribuin.rings;

import me.oribuin.rings.commands.RingsCommand;
import me.oribuin.rings.gui.RingsMenu;
import me.oribuin.rings.persist.Config;
import me.oribuin.rings.persist.Data;
import net.prosavage.baseplugin.SavagePlugin;
import org.bukkit.Bukkit;

public class Rings extends SavagePlugin {

    private static Rings INSTANCE;

    @Override
    public void onEnable() {
        super.onEnable();
        this.loadFiles();
        getCommand("Rings").setExecutor(new RingsCommand());
        RingsMenu menu = RingsMenu.getInstance();
        Bukkit.getPluginManager().registerEvents(menu, this);
        Rings.INSTANCE = this;
    }

    public static Rings getInstance() {
        return INSTANCE;
    }

    private void loadFiles() {
        Config.load();
        Data.load();
    }

    private void saveFiles() {
        Config.save();
        Data.save();
    }

    @Override
    public void onDisable() {
        this.saveFiles();
    }
}
