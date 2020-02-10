package com.justinschaaf.industrialtech.util;

import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Scanner;

public class TextUtil {

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
