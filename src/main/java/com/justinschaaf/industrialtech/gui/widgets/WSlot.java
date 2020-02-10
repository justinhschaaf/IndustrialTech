package com.justinschaaf.industrialtech.gui.widgets;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.ValidatedSlot;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class WSlot<T extends ValidatedSlot> extends WItemSlot {

    protected final List<Slot> peers = new ArrayList<Slot>();
    protected Inventory inventory;
    protected int startIndex;
    protected int slotsWide;
    protected int slotsHigh;
    protected boolean big;

    protected Class<T> type;

    public WSlot(Class<T> type, Inventory inventory, int index) {
        this(type, inventory, index, 1, 1, false, false);
    }

    public WSlot(Class<T> type, Inventory inventory, int startIndex, int slotsWide, int slotsHigh) {
        this(type, inventory, startIndex, slotsWide, slotsHigh, false, false);
    }

    public WSlot(Class<T> type, Inventory inventory, int index, boolean big) {
        this(type, inventory, index, 1, 1, big, false);
    }

    public WSlot(Class<T> type, Inventory inventory, int startIndex, int slotsWide, int slotsHigh, boolean big) {
        this(type, inventory, startIndex, slotsWide, slotsHigh, big, false);
    }

    public WSlot(Class<T> type, Inventory inventory, int startIndex, int slotsWide, int slotsHigh, boolean big, boolean ltr) {
        super(inventory, startIndex, slotsWide, slotsHigh, big, ltr);

        this.inventory = inventory;
        this.startIndex = startIndex;
        this.slotsWide = slotsWide;
        this.slotsHigh = slotsHigh;
        this.big = big;

        this.type = type;

    }

    @Override
    public void createPeers(GuiDescription c) {

        /*
         * This is simply the original createPeers() method with the custom slot type T.
         * Original method can be found at https://github.com/CottonMC/LibGui/blob/master/src/main/java/io/github/cottonmc/cotton/gui/widget/WItemSlot.java#L88-L102
         * Originally licensed under the MIT License
         */

        peers.clear();

        int index = startIndex;

        for (int y = 0; y < slotsHigh; y++) for (int x = 0; x < slotsWide; x++) {

            T slot = null;

            try {
                slot = type.getDeclaredConstructor(Inventory.class, int.class, int.class, int.class).newInstance(inventory, index, this.getAbsoluteX() + (x * 18), this.getAbsoluteY() + (y * 18));
            } catch (Exception e) {
                e.printStackTrace();
            }

            peers.add(slot);
            c.addSlotPeer(slot);
            index++;

        }

    }

}
