package me.oribuin.rings.persist;

import net.prosavage.baseplugin.XMaterial;
import net.prosavage.baseplugin.serializer.Serializer;
import net.prosavage.baseplugin.serializer.commonobjects.SerializableItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Config {
    // Transient means that it wont be serialized, as if we serialize the instance within it, then we are fucked cause we get a stackoverflow.
    private static transient Config instance = new Config();


    public static String ringMenuTitle = "&7Rings Plugin by Oribuin.";
    public static int ringMenuRows = 3;
    public static SerializableItem ringMenuBackgroundItem = new SerializableItem(XMaterial.BLACK_STAINED_GLASS_PANE, "&7 ", Arrays.asList("&7 "), 1);
    public static Map<RingItemType, RingMenuItem> ringMenuItems = new HashMap<>();

    static {
        ringMenuItems.put(RingItemType.INFO_BOOK, new RingMenuItem(new SerializableItem(
                XMaterial.KNOWLEDGE_BOOK,
                "&eInfo Book",
                Arrays.asList("&7Welcomee to the Rings Menu.",
                        "&eLeft Click me for Info!",
                        "&eRight Click me for github link!"),
                1
        ), 4));
    }

    public static void save() {
        new Serializer().save(instance);
    }

    public static void load() {
        new Serializer().load(instance, Config.class, "config");
    }
}
