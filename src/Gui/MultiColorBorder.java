package Gui;

import java.awt.*;
import javax.swing.border.Border;

public class MultiColorBorder implements Border {
    private int top, left, bottom, right;
    private Color topColor, leftColor, bottomColor, rightColor;

    public MultiColorBorder(int top, int left, int bottom, int right,
            Color topColor, Color leftColor, Color bottomColor, Color rightColor) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.topColor = topColor;
        this.leftColor = leftColor;
        this.bottomColor = bottomColor;
        this.rightColor = rightColor;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(top, left, bottom, right);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(topColor);
        g.fillRect(x, y, width, top);

        g.setColor(leftColor);
        g.fillRect(x, y, left, height);

        g.setColor(bottomColor);
        g.fillRect(x, y + height - bottom, width, bottom);

        g.setColor(rightColor);
        g.fillRect(x + width - right, y, right, height);
    }
}
