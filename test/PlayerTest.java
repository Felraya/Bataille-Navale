import info1.network.Player;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * des cas de tests pour la classe Player
 */


public class PlayerTest {

    Player p;


    /*               -------Test Nom Player----------          */

    @Test
    public void test_Nom1() {
        p = new Player("theo");
        assertEquals(p.getName(),"theo");
    }


    /*           -----------Test equals-----------       */
    @Test
    public void test_equals1()   {
        p = new Player("theo");
        Player p2 = new Player("theo");
        assertTrue(p.equals(p2));
    }

    @Test
    public void test_equals2()   {
        p = new Player("theo");
        Player p2 = new Player("theo2");
        assertFalse(p.equals(p2));
    }

    /*         --------Test JSON----------               */



    @Test
    public void test_JSON1() throws Exception {
        p = new Player("theo");
        JSONObject js = p.asJSON();
        assertEquals(js.get("nom"),"theo","valuer du JSONObject");
    }



    /*             ----------Test toString------------*/

    @Test
    public void test5()   {
        p = new Player("theo");
        assertEquals(p.toString(),"Theo","bonne methode toString");
    }
}