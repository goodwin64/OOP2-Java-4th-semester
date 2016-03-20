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
        Airplane ap1 = new Warplane("Il-2", 6160, 89.2, 600);
        Airplane ap2 = new Airliner("Cessna 172", 994, 11.3, 1);
        Airplane ap3 = new Warplane("North American F-86 Sabre / FJ Fury", 6870, 71, 2400);

        ap3.addFlightDistance(112);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src\\KPI_FICT\\OOP2\\Source\\Lab 6-03 - output.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (writer != null) {
            writer.println(ap1);
            writer.println(ap2);
            writer.println(ap3);

            writer.close();
        }
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
            String message = "Incorrect model name, length is >50";
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
        return String.format("%s \"%s\":\n" +
                "\tID: %d\n" +
                "\tMass: %.2f kg\n" +
                "\tFlew %d km\n" +
                "\tFuel consumption is %.2f units\n", className,
                getModel(), getID(), getMass(), getFlightDistance(), getFuelConsumption());
    }
}

/**
 * Civil airplane, carries passengers.
 *
 * seatingCapacity: maximum count of seats excluding crew
 */
class Airliner extends Airplane {
    private int seatingCapacity;

    public Airliner() {
        super();
    }

    public Airliner(int seatingCapacity) {
        super();
        try {
            setSeatingCapacity(seatingCapacity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public Airliner(String model, double mass,
                    double fuelConsumption, int seatingCapacity) throws IllegalArgumentException {
        super(model, mass, fuelConsumption);
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
        // TODO: add a check whether airplane will be able to lift such count of people
        if (seatingCapacity >= 0) {
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

/**
 * Military airplane.
 *
 * bombLoad: maximum mass of bombs carried by a warplane
 */
class Warplane extends Airplane {
    private int bombLoad;

    public Warplane() {
        super();
    }

    public Warplane(int bombLoad) {
        super();
        try {
            setBombLoad(bombLoad);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public Warplane(String model, double mass, double fuelConsumption,
                    int bombLoad) throws IllegalArgumentException {
        super(model, mass, fuelConsumption);
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
        // TODO: add checking with maximum bomb load value
        if (bombLoad >= 0) {
            this.bombLoad = bombLoad;
        }
    }

    @Override
    public String toString() {
        String appendMessage = String.format("\tBomb load: %d kg\n", getBombLoad());
        return super.toString() + appendMessage;
    }
}

/**
 * Civil airplane, carries cargo.
 *
 * cargoCapacity: maximum mass of cargo carried by an air freighter
 */
class Airfreighter extends Airplane {
    public int cargoCapacity;
}
