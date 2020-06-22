package demo;

import javax.swing.*;
import java.awt.*;

public class Vue extends JFrame {
    private JLabel label;
    private JButton button;
    private Modele modele;
    public Vue(Modele modele){
        this.modele = modele;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.button = new JButton("Inc");
        this.label = new JLabel(this.modele.getI().toString());
        this.add(label);
        this.add(button);
        this.pack();
        this.setVisible(true);

    }

    public JButton getButton() {
        return button;
    }

    public void update(Modele modele){
        this.modele = modele;
        this.label.setText(modele.getI().toString());

    }
}
