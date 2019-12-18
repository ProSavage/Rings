package me.oribuin.rings.persist;

import net.prosavage.baseplugin.serializer.Serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Data {

    // Transient means that it wont be serialized, as if we serialize the instance within it, then we are fucked cause we get a stackoverflow.
    private static transient Data instance = new Data();


    public static Map<UUID, RingPlayer> ringMap = new HashMap<>();


    public static void save() {
        new Serializer().save(instance);
    }

    public static void load() {
        new Serializer().load(instance, Data.class, "data");
    }


}
