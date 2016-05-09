package ua.kpi.fict.oop2.classes.variant03;

import java.util.Locale;

/**
 * Parent class for all airplanes
 *
 * flightDistance       distance which airplane has covered (km)
 * cruiseSpeed          average speed (km per hour)
 * range                maximum possible flight distance per flight (km)
 * ID                   airplane ID (number)
 * fuelConsumption      fuel consumption (kg per hour)
 * AVERAGE_FC_EFFICIENCY  average fuel consumption efficiency
 * emptyWeight          weight of airplane without cargo (kg)
 * weight               current airplane weight (kg)
 * model                title of airplane model
 */
public class Airplane implements Comparable<Airplane> {
    private int flightDistance;
    private int cruiseSpeed;
    private int range;
    private int ID;
    private static int maxID = 0;
    private double fuelConsumption;
    public static final double AVERAGE_FC_EFFICIENCY = 20.4;
    private int emptyWeight;
    protected int weight; //TODO: private, add MTOW to all planes
    private String model;


    public Airplane() {
        this.flightDistance = 0;
        this.ID = maxID + 1;
        maxID++;
    }
    public Airplane(Airplane other) {
        addFlightDistance(other.getFlightDistance());
        setCruiseSpeed(other.getCruiseSpeed());
        setRange(other.getRange());
        this.ID = other.getID();
        setWeight(other.getWeight());
        this.emptyWeight = other.getWeight();
        setFuelConsumption(other.getFuelConsumption());
        setModel(other.getModel());
    }
    public Airplane(String model, int emptyWeight, double fuelConsumption,
                    int cruiseSpeed, int range) throws IllegalArgumentException {
        this.flightDistance = 0;
        this.ID = maxID + 1;
        maxID++;
        this.emptyWeight = emptyWeight;

        try {
            setWeight(emptyWeight);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            setFuelConsumption(fuelConsumption);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            setModel(model);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            setCruiseSpeed(cruiseSpeed);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            setRange(range);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public double getFuelConsumption() {
        return fuelConsumption;
    }
    /**
     * Sets fuel consumption based on the airplane weight:
     * most low ratio between the weight and the FC:    4.6 (Bf 109 G-6)
     * most high:                                      43.8 (F-86 Sabre)
     */
    public void setFuelConsumption(double fuelConsumption) throws IllegalArgumentException {
        double minFuelConsumption = 4.6;
        double maxFuelConsumption = 43.9;

        double ratio = getWeight() / fuelConsumption;

        if (ratio > minFuelConsumption && ratio < maxFuelConsumption) {
            this.fuelConsumption = fuelConsumption;
        } else {
            String message = "Incorrect fuel consumption value";
            if (ratio < minFuelConsumption) {
                message += String.format("(%.2f < %.2f)", ratio, minFuelConsumption);
            } else if (ratio > maxFuelConsumption) {
                message += String.format("(%.2f > %.2f)\nNeed repair", ratio, maxFuelConsumption);
            }
            throw new IllegalArgumentException(message);
        }
    }
    /**
     * Calculates the Energy conversion efficiency (η) of fuel consumption
     * based on the airplane weight. Returns the value in percents (0..100)
     *
     * Input is heat (burning fuel)
     * Useful output is mechanical work (lifting the airplane)
     *
     * Kerosene: q = 40.8 MJ/kg
     */
    public double getFuelConsumptionEfficiency() {
        double input = getFuelConsumption() * 40.8 * Math.pow(10, 6);
        double output = (getWeight() + 200000) * 9.8 * getCruiseSpeed();
        return output / input * 100;
    }


    public int getEmptyWeight() {
        return emptyWeight;
    }
    public int getWeight() {
        return weight;
    }
    public int getCargoWeight() {
        return getWeight() - getEmptyWeight();
    }
    public void setWeight(int emptyWeight) throws IllegalArgumentException {
        if (emptyWeight > 70.0) {
            this.weight = emptyWeight;
        } else {
            String message = String.format(Locale.ENGLISH, "Incorrect emptyWeight (%d kg)", getWeight());
            throw new IllegalArgumentException(message);
        }
    }


    public int getFlightDistance() {
        return flightDistance;
    }
    /**
     * There is no need in public method setFlightDistance.
     * Method addFlightDistance provided instead.
     *
     * @param flightDistance    positive or negative integer number
     *                          (distance in km-s that airplane covered)
     */
    public void addFlightDistance(int flightDistance) {
        this.flightDistance += flightDistance;
    }


    public int getID() {
        return ID;
    }
    public static int getMaxID() {
        return maxID;
    }


    public String getModel() {
        return model;
    }
    public void setModel(String model) throws IllegalArgumentException {
        if (model.length() <= 50) {
            this.model = model;
        } else {
            String message = "Incorrect model name, length is >50";
            throw new IllegalArgumentException(message);
        }
    }


    public int getCruiseSpeed() {
        return cruiseSpeed;
    }
    public void setCruiseSpeed(int cruiseSpeed) throws IllegalArgumentException {
        if (cruiseSpeed > 50 && cruiseSpeed < 3200) {
            this.cruiseSpeed = cruiseSpeed;
        } else {
            String message = "Incorrect cruise speed";
            throw new IllegalArgumentException(message);
        }
    }


    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        if (range > 0 && range < 17395) {
            this.range = range;
        } else {
            String message = "Incorrect range";
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * Engine repair will reduce fuel consumption.
     *
     * @return true     if repair was successful
     */
    public boolean repairEngine(double fuelConsumptionReduction) {
        if (fuelConsumptionReduction > 0
                && this.fuelConsumption > fuelConsumptionReduction) {
            this.fuelConsumption -= fuelConsumptionReduction;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        String className = this.getClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1);
        return String.format(Locale.ENGLISH,
                "%s \"%s\":\n" +
                "\tID: %d\n" +
                "\tFlew %d km, range: %d km\n" +
                "\tCruise speed: %d km/h\n" +
                "\tFuel consumption is %.2f kg/h\n" +
                "\tEnergy conversion efficiency: %.0f%% (deviation: %+.0f%%)\n" +
                "\tWeight: %d kg, Cargo weight: %d kg\n",
                className, getModel(),
                getID(),
                getFlightDistance(), getRange(),
                getCruiseSpeed(),
                getFuelConsumption(),
                getFuelConsumptionEfficiency(), getFuelConsumptionEfficiency() - AVERAGE_FC_EFFICIENCY,
                getWeight(), getCargoWeight());
    }

    @Override
    public int compareTo(Airplane other) {
        int compareFlightDistance = this.getFlightDistance() - other.getFlightDistance();
        if (compareFlightDistance != 0) {
            return compareFlightDistance;
        } else {
            return ((int) Math.signum(this.getFuelConsumption() - other.getFuelConsumption()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airplane airplane = (Airplane) o;

        return (getModel() != null ? getModel().equals(airplane.getModel()) : airplane.getModel() == null)
                && getEmptyWeight() == airplane.getEmptyWeight()
                && getWeight() == airplane.getWeight()
                && getRange() == airplane.getRange()
                && getCruiseSpeed() == airplane.getCruiseSpeed()
                && getFlightDistance() == airplane.getFlightDistance()
                && Double.compare(airplane.getFuelConsumption(), getFuelConsumption()) == 0;
    }
}
