package info1.ships;

import java.awt.*;

/**
 * Classe définissant un navire de catégorie "sous-marin" (taille 1)
 * @author lanoix-a
 */

public class Submarine extends Ship {
    private Color c;

    public Submarine(String name, String xy)
            throws BadCoordException, CoordsBadShipException {
        super(name, xy, xy);
        c = Color.RED;
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.SUBMARINE;
    }

}
