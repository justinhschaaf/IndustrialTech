package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.util.TextUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class BasicItem extends Item {

    public BasicItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

        // Add description
        TranslatableText itemTooltip = new TranslatableText(getTranslationKey() + ".tooltip");
        if (!itemTooltip.asString().equals(getTranslationKey() + ".tooltip")) {
            if (tooltip.size() > 1) tooltip.add(new LiteralText(" ")); // Add some spacing
            TextUtil.splitTextForTooltip(tooltip, itemTooltip, Formatting.GRAY);
            tooltip.add(new LiteralText(" "));
        }

    }

}
