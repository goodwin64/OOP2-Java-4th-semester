package KPI_FICT.OOP2.Classes.Variant03;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 20.03.2016.
 */
public class Lab6_var03 {
    public static void main(String[] args) {
        String dataPath = "src\\KPI_FICT\\OOP2\\Source\\Variant03\\Lab6 - airplanes data.txt";
        String outputPath = "src\\KPI_FICT\\OOP2\\Source\\Variant03\\Lab6 - output.txt";

        Airplane[] realAirplanes = createAirplanesBasedOnPrototypes();

        airplanesToFile(dataPath, realAirplanes, false);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outputPath, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (writer != null) {
            // TASK: Create an airline.
            Airplane[] airline = createRandomAirplanes();
            airplanesToFile(dataPath, airline, true);

            // TASK: Calculate the total carrying capacity of airline.
            int totalCarryingCapacity = getTotalCarryingCapacity(airline);
            // TODO: 20'000'000 kg -> 20'000 t
            writer.printf("Airline total carrying capacity is %d kg\n", totalCarryingCapacity);

            // TASK: Sort company's airplanes by flight distances.
            Arrays.sort(airline);

            // TASK: Find company's airplanes that correspond to a given range of fuel consumption.
            Scanner scanner = new Scanner(System.in);
            System.out.print("Minimum fuel consumption value: ");
            int minFC = scanner.nextInt();
            System.out.print("Maximum fuel consumption value: ");
            int maxFC = scanner.nextInt();

            writer.printf("Airplanes with fuel consumption between %d and %d sorted by flight distance:\n\n",
                    minFC, maxFC);
            for (Airplane ap : airline) {
                if (ap.getFuelConsumption() > minFC && ap.getFuelConsumption() < maxFC) {
                    writer.println(ap);
                }
            }
            writer.close();
        }

    }

    /**
     * Writes the Airplanes array to a file.
     * @param path          path to file
     * @param airplanes     Airplanes array
     * @param append        boolean if <code>true</code>, then data will be written
     *                      to the end of the file rather than the beginning.
     */
    public static void airplanesToFile(String path, Airplane[] airplanes, boolean append) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(path, append)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (writer != null) {
            for (Airplane ap : airplanes) {
                if (ap != null) {
                    writer.println(ap);
                }
            }
            writer.close();
        }
    }

    /**
     * Creates and fills an Airplanes array based on real prototypes,
     * real aircraft units.
     *
     * Fuel consumption is the most doubtful airplane attribute.
     * Some info: https://en.wikipedia.org/wiki/Fuel_economy_in_aircraft
     */
    public static Airplane[] createAirplanesBasedOnPrototypes() {
        Airplane[] airplanes = new Airplane[15];

        /*
         * Calculated manually, values are rough:
         *      Il-2            fuel capacity: 535 kg, flying duration: 2.5 hours
         *          src:        http://goo.gl/ajfaiT
         *
         *      F-86 Sabre      38 gallons per hour -> 115 kg p h
         *          src:        https://goo.gl/MzfSmP
         *
         *      Bf 109 G-6      50L in 5 minutes of combat -> 480kg p h
         *          src:        http://goo.gl/jwmIHk
         *
         *      Boeing B-17     200 gallons of fuel per hour -> 605.67kg p h
         *          src:        http://goo.gl/A5AlRf
         *
         *      Fw 190 D-9      150..600 l/h -> 120..480 kg/h
         *          src:        http://goo.gl/fYmtdj, page 7
         *
         *      Cessna 172R     80% power
         *          src:        http://goo.gl/NFAyMM
         *
         *      Boeing 747-8I   9.9 kg/km
         *      Airbus A330-200 6.03 kg/km
         *      Airbus A330-300 6.28 kg/km
         *          src:        https://goo.gl/47I82n
         */

        airplanes[0] = new Warplane("Il-2", 4360, 214, 320, 720, 600);
        airplanes[1] = new Warplane("North American F-86 Sabre / FJ Fury", 5046, 115.07, 869, 2454, 2400);
        airplanes[2] = new Warplane("Messerschmitt Bf 109 G-6", 2247, 480, 590, 1000, 250);
        airplanes[3] = new Warplane("Boeing B-17 Flying Fortress", 16391, 605.67, 293, 3219, 2700);
        airplanes[4] = new Warplane("Focke-Wulf Fw 190 D-9", 3490, 300, 650, 835, 500);

        airplanes[5] = new Airliner("Cessna 172R", 767, 28.47, 226, 1289, 4);
        airplanes[6] = new Airliner("Airbus A330-300", 123100, 5700, 870, 11750, 440);
        airplanes[7] = new Airliner("McDonnell Douglas MD-11ER", 132050, 8215, 876, 13410, 410);
        airplanes[8] = new Airliner("McDonnell Douglas DC-10-30", 120742, 9486, 908, 10622, 380);
        airplanes[9] = new Airliner("Boeing 747-8I", 213000, 9078, 917, 15000, 605);

        airplanes[10] = new AirFreighter("Airbus A330-200", 119600, 5593, 871, 13450, 242000);
        airplanes[11] = new AirFreighter("An-225", 250000, 15900, 850, 15400, 640000);
        airplanes[12] = new AirFreighter("McDonnell Douglas MD-11ER", 132050, 8215, 876, 13410, 286000);
        airplanes[13] = new AirFreighter("McDonnell Douglas DC-10-30", 120742, 9486, 908, 10622, 259459);
        airplanes[14] = new AirFreighter("Boeing 747-8I", 213000, 9078, 917, 15000, 448000);

        return airplanes;
    }

    /**
     * Creates and fills an Airplanes array of random airplanes.
     */
    public static Airplane[] createRandomAirplanes() {
        Airplane[] airplanes = new Airplane[50];

        for (int i = 0; i < 50; i++) {
            int selector = ((int) (Math.random() * 3) + 1);                 // 1..3

            int emptyWeight = ((int) (Math.random() * 240000)) + 500;       // 500kg..250t;
            double fuelDelta = Math.random() * 19;                          // 0..19
            double fuelDeltaSign = Math.signum(Math.random() - 0.5);        // -1, 0, 1
            double fuelConsumption = emptyWeight / (24.28 + (fuelDelta * fuelDeltaSign));
            int cruiseSpeed = ((int) (Math.random() * 1000)) + 200;         // 200..1200km p h
            int range = ((int) (Math.random() * 14800)) + 200;              // 200..15000km

            airplanes[i] = new Airplane("", emptyWeight, fuelConsumption, cruiseSpeed, range);

            switch (selector) {
                case 1:
                    int bombLoad = (int) (Math.random() * emptyWeight);   // direct dependence on the emptyWeight
                    airplanes[i] = new Warplane(airplanes[i], bombLoad);
                    airplanes[i].setModel("random warplane");
                    break;

                case 2:
                    int seatingCapacity = (int) (Math.random() * 500);
                    airplanes[i] = new Airliner(airplanes[i], seatingCapacity);
                    airplanes[i].setModel("random airliner");
                    break;

                case 3:
                    /* TODO: find the bug, air freighters are twice less than other airplanes */
                    int MTOW = (int) ((Math.random() + 1) * emptyWeight);   // g.t. or equal to emptyWeight
                    airplanes[i] = new AirFreighter(airplanes[i], MTOW);
                    airplanes[i].setModel("random air freighter");
                    int cargoWeight = (int) (Math.random() * airplanes[i].getWeight() / 2);
                    try {
                        ((AirFreighter) airplanes[i]).addWeight(cargoWeight);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            airplanes[i].addFlightDistance((int) (Math.random() * 10000));
        }

        return airplanes;
    }

    /**
     * Calculates the total carrying capacity of the airline.
     *
     * @param airline       airplanes array
     */
    public static int getTotalCarryingCapacity(Airplane[] airline) {
        int totalCarryingCapacity = 0;
        for (Airplane ap : airline) {
            if (ap instanceof Airliner) {
                totalCarryingCapacity += ((Airliner) ap).getSeatingCapacity() * 80; // average man mass
            } else if (ap instanceof AirFreighter) {
                totalCarryingCapacity += ((AirFreighter) ap).getCargoCapacity();
            } else if (ap instanceof Warplane) {
                totalCarryingCapacity += ((Warplane) ap).getBombLoad();
            }
        }
        return totalCarryingCapacity;
    }
}

/**
 * Parent class for all airplanes
 *
 * flightDistance       distance which airplane has covered (km)
 * cruiseSpeed          average speed (km per hour)
 * range                maximum possible flight distance per flight (km)
 * ID                   airplane ID (number)
 * fuelConsumption      fuel consumption (kg per hour)
 * averageFCEfficiency  average fuel consumption efficiency
 * emptyWeight          weight of airplane without cargo (kg)
 * weight               current airplane weight (kg)
 * model                title of airplane model
 */
class Airplane implements Comparable<Airplane> {
    private int flightDistance;
    private int cruiseSpeed;
    private int range;
    private int ID;
    private static int maxID = 0;
    private double fuelConsumption;
    public static final double averageFCEfficiency = 20.4;
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
                getFuelConsumptionEfficiency(), getFuelConsumptionEfficiency() - averageFCEfficiency,
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

/**
 * Military airplane.
 *
 * bombLoad: maximum weight of bombs carried by a warplane
 */
class Warplane extends Airplane {
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

/**
 * Civil airplane, carries cargo.
 *
 * MTOW: maximum takeoff weight
 * cargoCapacity: maximum weight of cargo carried by an air freighter (MTOW - emptyWeight)
 */
class AirFreighter extends Airplane {
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
