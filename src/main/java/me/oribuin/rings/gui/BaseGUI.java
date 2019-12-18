package me.oribuin.rings.gui;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import me.oribuin.rings.Rings;
import me.oribuin.rings.persist.Data;
import me.oribuin.rings.persist.RingPlayer;
import net.prosavage.baseplugin.serializer.commonobjects.SerializableItem;
import net.prosavage.baseplugin.strings.StringProcessor;
import org.bukkit.entity.HumanEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGUI {

    private String title;
    private SerializableItem backgroundItem;
    private int guiRows;

    protected PaginatedPane pane;
    protected Gui gui;

    public BaseGUI(String title, SerializableItem serializableItem, int guiRows) {
        this.title = title;
        this.backgroundItem = serializableItem;
        this.guiRows = guiRows;
        gui = new Gui(Rings.getInstance(), guiRows, StringProcessor.color(title));
        pane = new PaginatedPane(0, 0, 9, guiRows);
    }

    public abstract void populatePane(RingPlayer ringPlayer);

    public List<GuiItem> buildFullBackgroundItemList() {
        List<GuiItem> guiItems = new ArrayList<>();
        for (int i = 0; i < guiRows * 9; i++) {
            guiItems.add(new GuiItem(backgroundItem.buildItem(), e -> {e.setCancelled(true);} ));
        }
        return guiItems;
    }

    public void showGui(HumanEntity humanEntity) {
        this.populatePane(Data.ringMap.getOrDefault(humanEntity.getUniqueId(), null));
        gui.addPane(this.pane);
        gui.update();
        gui.show(humanEntity);
    }





}
