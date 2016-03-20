package KPI_FICT.OOP2.Classes.Variant03;

import java.util.Locale;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 20.03.2016.
 */
public class Lab6_var03 {
    public static void main(String[] args) {
        Airplane ap1 = new Airplane("Il-2", 6160, 89.2);
        Airplane ap2 = new Airplane();
        Airplane ap3 = new Airplane("North American F-86 Sabre / FJ Fury",
                6870, 115.5); // Incorrect fuel consumption
        ap3.setFuelConsumption(71); // Correct fuel consumption

        try {
            ap2.setMass(-120); // Exception
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        ap2.setMass(992.4);
        ap2.addMass(7.6);
        ap2.setFuelConsumption(10.5);

        ap2.setModel("Cessna 172");

        System.out.println(ap2.repairEngine(-5.2)); // false
        System.out.println(ap2.repairEngine(5.2)); // true

        System.out.println(ap1.getFuelConsumption()); // 89.2
        System.out.println(ap2.getFuelConsumption()); // 5.3
        System.out.println(ap3.getFuelConsumption()); // 71.0

        System.out.println(ap1.getMass()); // 6160
        System.out.println(ap2.getMass()); // 1000
        System.out.println(ap3.getMass()); // 6870.0

        System.out.println(ap1.getID()); // 1
        System.out.println(ap2.getID()); // 2
        System.out.println(ap3.getID()); // 3
    }
}

/**
 * Parent class for all airplanes
 */
class Airplane {
    private int flightDistance;
    private int ID;
    private static int maxID = 0;
    private double fuelConsumption;
    private double mass; // kg
    private String model;

    public Airplane() {
        this.flightDistance = 0;
        this.ID = maxID + 1;
        maxID++;
    }

    public Airplane(String model, double mass,
                    double fuelConsumption) throws IllegalArgumentException {
        this.flightDistance = 0;
        this.ID = maxID + 1;
        maxID++;

        try {
            setMass(mass);
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
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * Sets fuel consumption based on the airplane weight:
     * correct fuel consumption is within 25% of the average fuel consumption value.
     */
    public void setFuelConsumption(double fuelConsumption) throws IllegalArgumentException {
        double minFuelConsumption = calcFuelConsumption() * 0.75;
        double maxFuelConsumption = calcFuelConsumption() * 1.25;

        if (fuelConsumption > minFuelConsumption && fuelConsumption < maxFuelConsumption) {
            this.fuelConsumption = fuelConsumption;
        } else {
            String message = "Incorrect fuel consumption value";
            if (fuelConsumption < minFuelConsumption) {
                message += String.format("(%.2f < %.2f)", fuelConsumption, minFuelConsumption);
            } else if (fuelConsumption > maxFuelConsumption) {
                // need repair
                message += String.format("(%.2f > %.2f)", fuelConsumption, maxFuelConsumption);
            }
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Calculates the approximate fuel consumption based on the airplane weight.
     *
     * For now, this formula does not assume any physical meaning.
     */
    public double calcFuelConsumption() {
        // TODO: add physical meaning
        return this.mass / 80;
    }


    public double getMass() {
        return mass;
    }

    public void setMass(double mass) throws IllegalArgumentException {
        if (mass > 70.0) {
            this.mass = mass;
        } else {
            String message = String.format(Locale.ENGLISH, "Incorrect mass (%.2f kg)", mass);
            throw new IllegalArgumentException(message);
        }
    }

    public void addMass(double mass) {
        if (mass > 0) {
            this.mass += mass;
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
            String message = String.format("Incorrect model name, length is >50");
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * Engine repair will reduce fuel consumption.
     *
     * @return true     if repair was successful
     * @return false    otherwise
     */
    public boolean repairEngine(double fuelConsumptionReduction) {
        if (fuelConsumptionReduction > 0
                && this.fuelConsumption > fuelConsumptionReduction) {
            this.fuelConsumption -= fuelConsumptionReduction;
            return true;
        }
        return false;
    }
}

/**
 * Civil airplane
 *
 * seatingCapacity: maximum count of seats
 */
class Airliner extends Airplane {
    public int seatingCapacity;
}

/**
 * Military airplane
 *
 * bombLoad: maximum mass of bombs carried by a warplane
 */
class Warplane extends Airplane {
    public int bombLoad;
}

/**
 * Cargo airplane
 *
 * cargoCapacity: maximum mass of cargo carried by an air freighter
 */
class Airfreighter extends Airplane {
    public int cargoCapacity;
}
