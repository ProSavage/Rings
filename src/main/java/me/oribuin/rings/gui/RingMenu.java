package me.oribuin.rings.gui;

import com.github.stefvanschie.inventoryframework.GuiItem;
import me.oribuin.rings.persist.Config;
import me.oribuin.rings.persist.RingItemType;
import me.oribuin.rings.persist.RingMenuItem;
import me.oribuin.rings.persist.RingPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RingMenu extends BaseGUI {
    public RingMenu() {
        super(Config.ringMenuTitle, Config.ringMenuBackgroundItem, Config.ringMenuRows);
    }

    @Override
    public void populatePane(RingPlayer ringPlayer) {
        List<GuiItem> guiItems = buildFullBackgroundItemList();
        Config.ringMenuItems.forEach(((ringItemType, ringMenuItem) -> {
                    guiItems.remove(ringMenuItem.getSlot());
                    guiItems.add(ringMenuItem.getSlot(), buildItemBasedOnType(ringItemType, ringMenuItem));
                })
        );
        pane.populateWithGuiItems(guiItems);
    }

    private GuiItem buildItemBasedOnType(RingItemType ringItemType, RingMenuItem ringMenuItem) {
        ItemStack itemStack = ringMenuItem.getItem().buildItem();
        switch (ringItemType) {
            case INFO_BOOK:
                return new GuiItem(itemStack, e -> {
                    e.setCancelled(true);
                    switch (e.getClick()) {
                        case LEFT:
                            e.getWhoClicked().sendMessage("Coming soon");
                            break;
                        case RIGHT:
                            e.getWhoClicked().sendMessage("https://github.com/Oribuin/Rings");
                            break;
                        default:
                            e.getWhoClicked().sendMessage("Please LEFT or RIGHT click it.");
                            break;
                    }
                });
            case FIRE:
            case REGEN:
            case SPEED:
            case WATER:
            case VISION:
            case STRENGTH:
            default:
                return new GuiItem(itemStack, e -> {
                    e.setCancelled(true);
                    e.getWhoClicked().sendMessage("You need to do this part on your own.");
                });
        }
    }

}
