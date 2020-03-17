package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.util.TextUtil;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class ITBlockItem extends BlockItem {

    public ITBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        TextUtil.appendItemTooltip(stack, world, tooltip, context);
    }

}
