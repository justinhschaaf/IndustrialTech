package com.justinschaaf.industrialtech.util;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Scanner;

public class TextUtil {

    public static void appendItemTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

        // Add description
        TranslatableText itemTooltip = new TranslatableText(stack.getTranslationKey() + ".tooltip");
        if (!itemTooltip.asString().equals(stack.getTranslationKey() + ".tooltip")) {
            if (tooltip.size() > 1) tooltip.add(new LiteralText(" ")); // Add some spacing
            TextUtil.splitTextForTooltip(tooltip, itemTooltip, Formatting.GRAY);
            tooltip.add(new LiteralText(" "));
        }

    }

    public static List<Text> splitTextForTooltip(List<Text> tooltip, Text text, Formatting... formatting) {

        Scanner textScanner = new Scanner(text.asFormattedString());
        StringBuilder nextLine = new StringBuilder();

        while (textScanner.hasNext()) {

            nextLine.append(textScanner.next());
            nextLine.append(" ");

            if (nextLine.length() >= 40 || !textScanner.hasNext()) {
                tooltip.add(new LiteralText(nextLine.toString()).formatted(formatting));
                nextLine = new StringBuilder();
            }

        }

        return tooltip;

    }

}
