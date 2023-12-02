package View.Panels;

import javax.swing.*;
import java.awt.*;

/**
 * The GradientPanel class represents a JPanel with a gradient background.
 */
public class GradientPanel extends JPanel {
    /**
     * Overrides the paintComponent method to draw a gradient background.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int height = getHeight();
        int width = getWidth();

        Color startColor = Color.BLUE;
        Color endColor = Color.WHITE;

        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);
    }
}
