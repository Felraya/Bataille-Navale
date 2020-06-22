import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Des cas de tests pour la classe Game
 */

public class GameTest {

    Game game;


    /*          --------Tests Id Game--------          */
    @Test
    public void test_IdGame1(){ //Id negatif
        try {
            game = new Game(-10);
            fail("exception pas levée");
        } catch (BadIdException e) {
            System.out.println("Exception bien levée");
            e.printStackTrace();
        }
    }

    @Test
    public void test_IdGame2(){ //Id positif
        try {
            game = new Game(10);
        } catch (BadIdException e) {
            fail("exception non attendu");
            e.printStackTrace();
        }
    }

    @Test
    public void test_IdGame3(){ //Id null
        try {
            game = new Game(0);
            fail("exception pas levée");
        } catch (BadIdException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_IdGame4(){ //Id limite
        try {
            game = new Game(Integer.MAX_VALUE);
        } catch (BadIdException e) {
            fail("exception non attendu");
            e.printStackTrace();
        }
    }

    @Test
    public void test_IdGame5(){ //Id limite+1
        try {
            game = new Game(Integer.MAX_VALUE+1);
            fail("exception pas levée");
        } catch (BadIdException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_IdGame6(){ //Id -limite
        try {
            game = new Game(Integer.MIN_VALUE);
            fail("exception pas levée");
        } catch (BadIdException e) {
            System.out.println("Exception bien levée");
            e.printStackTrace();
        }
    }


    /*while(point>0){
        int tmp = new random(1,5);
        while(tmp>point){
            tmp = new random(1,5);
        }
        point = point-tmp;
    }
    */

    /*          --------Tests Initiator--------          */
    @Test
    public void test_Initiator1(){
        try {
            game = new Game(10);
            assertEquals(game.getInitiator(),null,"initiator doit etre null");

        } catch (BadIdException e) {
            fail("pas d'exception");
            e.printStackTrace();
        }
    }

    @Test
    public void test_Initiator2(){
        try {
            game = new Game(10);
            Player res = new Player("erwan");
            game.initiate(res);
            assertEquals(game.getInitiator(),res,"initiator doit etre erwan");

        } catch (BadIdException e) {
            fail("pas d'exception");
            e.printStackTrace();
        }
    }


    /*          --------Tests Guest--------          */
    @Test
    public void test_Gest1(){
        try {
            game = new Game(10);
            assertEquals(game.getGuest(),null,"guest doit etre null");

        } catch (BadIdException e) {
            fail("pas d'exception");
            e.printStackTrace();
        }
    }

    @Test
    public void test_Gest2(){
        try {
            game = new Game(10);
            Player res = new Player("erwan");
            game.join(res);
            assertEquals(game.getGuest(),res,"guest doit etre erwan");

        } catch (BadIdException e) {
            fail("pas d'exception");
            e.printStackTrace();
        }
    }

    /*          --------Tests Equals--------          */
    @Test
    public void test_equals1(){ //Deux games identiques
        try {
            Player j = new Player("erwan");
            Player g = new Player("theo");
            game = new Game(10);
            game.initiate(j);
            game.join(g);

            Player j2 = new Player("erwan");
            Player g2 = new Player("theo");
            Game game2 = new Game(10);
            game2.initiate(j2);
            game2.join(g2);

            assertTrue(game.equals(game2),"test equals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals2(){ //Deux games identiques
        try {
            Player j = null;
            Player g = new Player("theo");
            game = new Game(10);
            game.initiate(j);
            game.join(g);

            Player j2 = null;
            Player g2 = new Player("theo");
            Game game2 = new Game(10);
            game2.initiate(j2);
            game2.join(g2);

            assertTrue(game.equals(game2),"test equals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals3(){ //Deux games identiques
        try {
            Player j = new Player("erwan");
            Player g = null;
            game = new Game(10);
            game.initiate(j);
            game.join(g);

            Player j2 = new Player("erwan");
            Player g2 = null;
            Game game2 = new Game(10);
            game2.initiate(j2);
            game2.join(g2);

            assertTrue(game.equals(game2),"test equals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals4(){ //Deux games identiques
        try {
            Player j = null;
            Player g = null;
            game = new Game(10);
            game.initiate(j);
            game.join(g);

            Player j2 = null;
            Player g2 = null;
            Game game2 = new Game(10);
            game2.initiate(j2);
            game2.join(g2);

            assertTrue(game.equals(game2),"test equals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals5(){//Pas le meme initiateur
        try {
            Player j = new Player("erwan");
            Player g = new Player("theo");
            game = new Game(10);
            game.initiate(j);
            game.join(g);

            Player j2 = new Player("Pas erwan");
            Player g2 = new Player("theo");
            Game game2 = new Game(10);
            game2.initiate(j2);
            game2.join(g2);

            assertFalse(game.equals(game2),"test pas egals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals6(){//Pas le meme guest
        try {
            Player j = new Player("erwan");
            Player g = new Player("theo");
            game = new Game(10);
            game.initiate(j);
            game.join(g);

            Player j2 = new Player("erwan");
            Player g2 = new Player("Pas theo");
            Game game2 = new Game(10);
            game2.initiate(j2);
            game2.join(g2);

            assertFalse(game.equals(game2),"test pas egals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals7(){//Pas le meme ID
        try {
            Player j = new Player("erwan");
            Player g = new Player("theo");
            game = new Game(5);
            game.initiate(j);
            game.join(g);

            Player j2 = new Player("erwan");
            Player g2 = new Player("Pas theo");
            Game game2 = new Game(10);
            game2.initiate(j2);
            game2.join(g2);

            assertFalse(game.equals(game2),"test pas egals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals8(){//Game null
        try {
            game = null;

            Game game2 = null;

            assertTrue(game.equals(game2),"test egals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_equals9(){//Game null
        try {
            game = null;

            Game game2 = null;

            assertTrue(game.equals(game2),"test egals");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*          --------Tests toString--------          */

    @Test
    public void test_toString1(){ //Game avec initiateur et guest
        try {
            Player j = new Player("theo");
            Player g = new Player("guest");
            game = new Game(10);
            game.initiate(j);
            game.join(g);

            assertEquals(game.toString(),"[10 : theo vs guest]","test toString");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_toString2(){ //Game sans guest
        try {
            Player j = new Player("theo");
            game = new Game(10);
            game.initiate(j);

            assertEquals(game.toString(),"[10 : theo]","test toString");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_toString3(){ //Game sans initialeur
        try {
            Player g = new Player("erwan");
            game = new Game(10);
            game.join(g);

            assertEquals(game.toString(),"[10 vs erwan]","test toString");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_toString4(){ //Game avec personne
        try {
            game = new Game(10);

            assertEquals(game.toString(),"[10]","test toString");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*                                                                                   //BUG DANS LA CLASSE GAME, ON NE PEUT PAS FAIRE UN TOSTRING SUR UNE GAME NULL
    @Test
    public void test_toString5(){ //Game null
        try {
            game = null;

            assertEquals(game.toString(),"[]","test toString");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
