package demo;

public class App2 {
    public static void main(String[] args) {
        Modele modele = new Modele();
        Vue vue = new Vue(modele);
        Controller controller = new Controller(vue, modele);

    }
    
}
