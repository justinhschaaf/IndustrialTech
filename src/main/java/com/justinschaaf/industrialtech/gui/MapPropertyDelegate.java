package com.justinschaaf.industrialtech.gui;

import net.minecraft.container.PropertyDelegate;

import java.util.HashMap;
import java.util.Map;

public class MapPropertyDelegate implements PropertyDelegate {

    private Map<Integer, Integer> properties = new HashMap<>();

    @Override
    public int get(int key) {
        return this.properties.get(key);
    }

    @Override
    public void set(int key, int value) {
        this.properties.put(key, value);
    }

    @Override
    public int size() {
        return this.properties.size();
    }

}
