package Application;

import Listener.*;
import info1.network.*;
import info1.ships.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Principale extends JFrame {
    private ArrayList<Integer> flotte;

    private JLabel choixPseudo;
    private CustomTextField jNickname; //la zone de texte où rentrer son nom

    private JLabel labelChoixCompo;
    private JComboBox<String> choixCompo ;
    private JButton addFlotte;

    private JButton lancer;

    private Player joueur; // le nom récuperé



    public Principale() throws HeadlessException {
        super("Bataille Navale");

        this.flotte = new ArrayList<>();
        this.choixPseudo = new JLabel("Votre pseudo");
        this.jNickname = new CustomTextField(20);
        jNickname.setPlaceholder("Pseudo");
        this.labelChoixCompo = new JLabel("Choississez votre composition de flotte");
        String[] listeFlotte = new String[]{"Flotte Française","Flotte Belge"};
        this.choixCompo = new JComboBox<>(listeFlotte);
        this.addFlotte = new JButton("Créer Flotte Personnalisée");
        this.lancer = new JButton("valider");



        JPanel PanelCentral = new JPanel();
        PanelCentral.setLayout(new GridLayout(3,1));

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        choixPseudo.setHorizontalAlignment(JLabel.CENTER);
        panelTop.add(choixPseudo,BorderLayout.CENTER);
        panelTop.add(jNickname,BorderLayout.SOUTH);
        panelTop.setBorder(BorderFactory.createEmptyBorder(0, 70, 20, 70));

        JPanel panelMiddle = new JPanel();
        panelMiddle.setLayout(new FlowLayout());
        panelMiddle.add(labelChoixCompo);
        panelMiddle.add(choixCompo);

        panelMiddle.add(addFlotte);
        panelMiddle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));


        JPanel panelBot = new JPanel();
        panelBot.setLayout(new FlowLayout());
        panelBot.add(lancer);
        panelBot.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));


        PanelCentral.add(panelTop);
        PanelCentral.add(panelMiddle);
        PanelCentral.add(panelBot);



        //listener
        AddFlotte af = new AddFlotte(this);
        addFlotte.addActionListener(af);

        Valider val = new Valider(this);
        lancer.addActionListener(val);






        this.getContentPane().add(PanelCentral);
        this.setPreferredSize(new Dimension(330, 300));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }


    public String getNickname(){
        joueur = new Player(jNickname.getText());
        return jNickname.getText();
    }

    public String getComboBox(){
        return (String)choixCompo.getSelectedItem();
    }


    public void setFlotte(ArrayList<Integer> liste) {
        this.flotte = liste;
    }


    public ArrayList<Integer> getFlotte() {
        return flotte;
    }

    public void change_button(){ this.addFlotte.setText("Refaire sa flotte personnalisée"); }


    public void addFlottePerso(){
        if(choixCompo.getItemCount()==2) {
            choixCompo.addItem("Personnalisée");
            choixCompo.setSelectedIndex(2);

        }
    }
    public Player getPlayer(){
        return joueur;
    }
}

