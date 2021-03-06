package info1.ships;
import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;


public class CustomTextField extends JTextField {

    public static void main(final String[] args) {
        final CustomTextField tf = new CustomTextField("");
        tf.setColumns(20);
        tf.setPlaceholder("All your base are belong to us!");
        final Font f = tf.getFont();
        tf.setFont(new Font(f.getName(), f.getStyle(), 30));
        JOptionPane.showMessageDialog(null, tf);
    }

    private String placeholder;

    public CustomTextField() {
    }

    public CustomTextField(
            final Document pDoc,
            final String pText,
            final int pColumns)
    {
        super(pDoc, pText, pColumns);
    }

    public CustomTextField(final int pColumns) {
        super(pColumns);
    }

    public CustomTextField(final String pText) {
        super(pText);
    }

    public CustomTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }
    

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}
