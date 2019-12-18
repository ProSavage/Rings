package me.oribuin.rings.persist;

import net.prosavage.baseplugin.serializer.commonobjects.SerializableItem;

public class RingMenuItem {

    private SerializableItem item;
    private int slot;

    public RingMenuItem(SerializableItem item, int slot) {

        this.item = item;
        this.slot = slot;
    }

    public SerializableItem getItem() {
        return item;
    }

    public void setItem(SerializableItem item) {
        this.item = item;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
