package ua.kpi.fict.oop2.classes.variant03.lab6;

/**
 * Civil airplane, carries passengers.
 *
 * seatingCapacity: maximum count of seats excluding crew
 */
public class Airliner extends Airplane {
    private int seatingCapacity;


    public Airliner() {
        super();
    }
    public Airliner(Airplane base, int seatingCapacity) {
        super(base);
        try {
            setSeatingCapacity(seatingCapacity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public Airliner(String model, int emptyWeight, double fuelConsumption,
                    int cruiseSpeed, int range,
                    int seatingCapacity) throws IllegalArgumentException {
        super(model, emptyWeight, fuelConsumption, cruiseSpeed, range);
        try {
            setSeatingCapacity(seatingCapacity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    public int getSeatingCapacity() {
        return seatingCapacity;
    }
    public void setSeatingCapacity(int seatingCapacity) throws IllegalArgumentException {
        if (seatingCapacity >= 0 && seatingCapacity < 700) {
            this.seatingCapacity = seatingCapacity;
        } else {
            String message = String.format("Incorrect seating capacity (%d)", seatingCapacity);
            throw new IllegalArgumentException(message);
        }
    }


    @Override
    public String toString() {
        String appendMessage = String.format("\tSeating capacity: %d\n", getSeatingCapacity());
        return super.toString() + appendMessage;
    }
}
