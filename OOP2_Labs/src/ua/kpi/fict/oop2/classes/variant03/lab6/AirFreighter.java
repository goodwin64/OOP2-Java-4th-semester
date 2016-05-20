package ua.kpi.fict.oop2.classes.variant03.lab6;

/**
 * Civil airplane, carries cargo.
 *
 * MTOW: maximum takeoff weight
 * cargoCapacity: maximum weight of cargo carried by an air freighter (MTOW - emptyWeight)
 */
public class AirFreighter extends Airplane {
    private int MTOW;


    public AirFreighter() {
        super();
    }
    public AirFreighter(Airplane base, int MTOW) {
        super(base);
        try {
            setMTOW(MTOW);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public AirFreighter(String model, int emptyWeight, double fuelConsumption,
                        int cruiseSpeed, int range,
                        int MTOW) throws IllegalArgumentException {
        super(model, emptyWeight, fuelConsumption, cruiseSpeed, range);
        try {
            setMTOW(MTOW);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    public int getCargoCapacity() {
        return MTOW - getWeight();
    }


    public int getMTOW() {
        return MTOW;
    }
    public void setMTOW(int MTOW) throws IllegalArgumentException {
        if (MTOW >= 0) {
            this.MTOW = MTOW;
        } else {
            String message = String.format("Incorrect cargo capacity (%d kg)", MTOW);
            throw new IllegalArgumentException(message);
        }
    }
    public void addWeight(int weight) throws IllegalArgumentException {
        if (getWeight() + weight < getMTOW()) {
            this.weight += weight;
        } else {
            setWeight(getMTOW());
            throw new IllegalArgumentException("Too much cargo added");
        }
    }


    @Override
    public String toString() {
        String appendMessage = String.format(
                "\tCargo capacity: %d kg\n" +
                "\tMaximum takeoff weight: %d kg\n",
                getCargoCapacity(), getMTOW());
        return super.toString() + appendMessage;
    }
}
