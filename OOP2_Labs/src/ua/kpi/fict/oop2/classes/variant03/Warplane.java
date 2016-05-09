package ua.kpi.fict.oop2.classes.variant03;

/**
 * Military airplane.
 *
 * bombLoad: maximum weight of bombs carried by a warplane
 */
public class Warplane extends Airplane {
    private int bombLoad;


    public Warplane() {
        super();
    }
    public Warplane(Airplane base, int bombLoad) {
        super(base);
        try {
            setBombLoad(bombLoad);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public Warplane(String model, int emptyWeight, double fuelConsumption,
                    int cruiseSpeed, int range,
                    int bombLoad) throws IllegalArgumentException {
        super(model, emptyWeight, fuelConsumption, cruiseSpeed, range);
        try {
            setBombLoad(bombLoad);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    public int getBombLoad() {
        return bombLoad;
    }
    public void setBombLoad(int bombLoad) throws IllegalArgumentException {
        if (bombLoad >= 0) {
            this.bombLoad = bombLoad;
        }
    }
    /**
     * Returns count of bombs warplane is able to lift.
     */
    public int getBombsCount(int bombMass) {
        return getBombLoad() / bombMass;
    }


    @Override
    public String toString() {
        String appendMessage = String.format("\tBomb load: %d kg\n", getBombLoad());
        return super.toString() + appendMessage;
    }
}
