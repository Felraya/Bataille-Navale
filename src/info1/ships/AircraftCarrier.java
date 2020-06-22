package info1.ships;

import java.awt.*;

/**
 * Classe définissant une navire de catégorie "Porte-Avion" (taille 5)
 */

public class AircraftCarrier extends Ship {
    private Color c;

    public AircraftCarrier(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
        c = Color.RED;
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.AIRCRAFT_CARRIER;
    }

}
