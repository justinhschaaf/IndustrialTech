package com.justinschaaf.industrialtech.gui.widgets;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Supplier;

public class WColoredButton extends WButton implements SuppliedTooltip {

    private int bgColor;
    private Supplier<Text> tooltip;

    public WColoredButton() {
        this(0xFFFFFFFF);
    }

    public WColoredButton(int bgColor) {
        super();
        this.bgColor = bgColor;
    }

    public WColoredButton(int bgColor, Text text) {
        super(text);
        this.bgColor = bgColor;
    }

    @Override
    public void paintBackground(int x, int y, int mouseX, int mouseY) {

        /*
         * This is simply the original paintBackground() method with the custom color implemented (and slightly cleaned to fit my formatting).
         * Original method can be found at https://github.com/CottonMC/LibGui/blob/master/src/main/java/io/github/cottonmc/cotton/gui/widget/WButton.java#L33-L63
         * Originally licensed under the MIT License
         */

        boolean hovered = (mouseX>=0 && mouseY>=0 && mouseX<getWidth() && mouseY<getHeight());
        int state = 1;
        if (!this.isEnabled()) state = 0;
        else if (hovered) state = 2;

        float px = 1 / 256f;
        float buttonLeft = 0 * px;
        float buttonTop = (46 + (state * 20)) * px;
        int halfWidth = this.getWidth() / 2;
        if (halfWidth > 198) halfWidth = 198;
        float buttonWidth = halfWidth * px;
        float buttonHeight = 20 * px;

        float buttonEndLeft = (200 - (this.getWidth() / 2)) * px;

        ScreenDrawing.texturedRect(x, y, this.getWidth() / 2, this.getHeight(), AbstractButtonWidget.WIDGETS_LOCATION, buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonHeight, this.getBgColor());
        ScreenDrawing.texturedRect(x + (this.getWidth() / 2), y, this.getWidth() / 2, this.getHeight(), AbstractButtonWidget.WIDGETS_LOCATION, buttonEndLeft, buttonTop, 200 * px, buttonTop + buttonHeight, this.getBgColor());

        if (this.getLabel() != null) {

            int color = 0xE0E0E0;
            if (!this.isEnabled()) color = 0xA0A0A0;
            else if (hovered) color = 0xFFFFA0;

            ScreenDrawing.drawStringWithShadow(this.getLabel().asFormattedString(), alignment, x, y + ((20 - 8) / 2), width, color);

        }

    }

    @Override
    public void setSize(int x, int y) {
        // Overriden to avoid forced height of 20
        this.width = x;
        this.height = y;
    }

    @Override
    public void addInformation(List<String> information) {

        if (getTooltipCallback() != null) {
            Text tooltipText = getTooltipCallback().get();
            if (tooltipText != null) information.add(tooltipText.asFormattedString());
        }

        super.addInformation(information);

    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public void withTooltip(Supplier<Text> tooltip) {
        this.setTooltipCallback(tooltip);
    }

    @Override
    public void setTooltipCallback(Supplier<Text> tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public Supplier<Text> getTooltipCallback() {
        return this.tooltip;
    }

}
