package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.util.TextUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class ITItem extends Item {

    public ITItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        TextUtil.appendItemTooltip(stack, world, tooltip, context);
    }

}
