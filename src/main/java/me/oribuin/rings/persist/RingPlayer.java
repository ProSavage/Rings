package me.oribuin.rings.persist;

import java.util.UUID;

public class RingPlayer {

    private UUID uuid;
    private boolean speedRing, healingRing, bruteRing, aquaRing, fireRing, strengthRing, visionRing;


    public RingPlayer(UUID uui) {
        this.uuid = uuid;
    }

    public boolean isSpeedRing() {
        return speedRing;
    }

    public void setSpeedRing(boolean speedRing) {
        this.speedRing = speedRing;
    }

    public boolean isHealingRing() {
        return healingRing;
    }

    public void setHealingRing(boolean healingRing) {
        this.healingRing = healingRing;
    }

    public boolean isBruteRing() {
        return bruteRing;
    }

    public void setBruteRing(boolean bruteRing) {
        this.bruteRing = bruteRing;
    }

    public boolean isAquaRing() {
        return aquaRing;
    }

    public void setAquaRing(boolean aquaRing) {
        this.aquaRing = aquaRing;
    }

    public boolean isFireRing() {
        return fireRing;
    }

    public void setFireRing(boolean fireRing) {
        this.fireRing = fireRing;
    }

    public boolean isStrengthRing() {
        return strengthRing;
    }

    public void setStrengthRing(boolean strengthRing) {
        this.strengthRing = strengthRing;
    }

    public boolean isVisionRing() {
        return visionRing;
    }

    public void setVisionRing(boolean visionRing) {
        this.visionRing = visionRing;
    }
}
