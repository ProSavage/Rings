package me.oribuin.rings.persist;

import java.util.UUID;

public class RingPlayer {

    private UUID uuid;
    private RingItemType activeRing;


    public RingPlayer(UUID uui) {
        this.uuid = uuid;
    }

    public RingItemType getActiveRing() {
        return activeRing;
    }

    public void setActiveRing(RingItemType activeRing) {
        this.activeRing = activeRing;
    }

}
