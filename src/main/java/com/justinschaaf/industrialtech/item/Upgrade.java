package com.justinschaaf.industrialtech.item;

import net.minecraft.item.Item;

public class Upgrade extends BasicItem {

    int upgradeLevel;
    double multiplier;

    public Upgrade(Settings settings, int upgradeLevel, double multiplier) {
        super(settings);
        this.upgradeLevel = upgradeLevel;
        this.multiplier = multiplier;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public double getMultiplier() {
        return multiplier;
    }

}
