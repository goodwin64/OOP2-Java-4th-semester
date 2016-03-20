package KPI_FICT.OOP2.Classes.Variant03;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 20.03.2016.
 */
public class Lab6_var03 {
    public static void main(String[] args) {
        Airplane ap1 = new Airplane("Il-2", 6160, 89.2);
        Airplane ap2 = new Airplane();
        Airplane ap3 = new Airplane("North American F-86 Sabre / FJ Fury",
                6870, 71);

        ap3.addFlightDistance(112);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src\\KPI_FICT\\OOP2\\Source\\Lab 6-03 - output.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
            writer.println(ap1);
            writer.println(ap2);
            writer.println(ap3);

        writer.close();
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
    // TODO: add lift power
    // TODO: add Airplane range

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
        // TODO: check whether lift power is enough
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

    @Override
    public String toString() {
        return String.format("Airplane %s:\n" +
                "\tID: %d\n" +
                "\tMass: %.2f kg\n" +
                "\tFlight %d km-s\n" +
                "\tFuel consumption is %.2f units\n",
                getModel(), getID(), getMass(), getFlightDistance(), getFuelConsumption());
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
