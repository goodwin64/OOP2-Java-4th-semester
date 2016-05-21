package ua.kpi.fict.oop2.classes.variant03.lab6;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 20.03.2016.
 */
public class Lab6_var03 {
    public static void main(String[] args) {
        String dataPathPrefix = "src\\ua\\kpi\\fict\\oop2\\Resources\\Variant03\\";
        String outputPathPrefix = dataPathPrefix;

        Airplane[] realAirplanes = createAirplanesBasedOnPrototypes();

        airplanesToFile(dataPathPrefix + "Lab6 - airplanes data.txt", realAirplanes, false);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outputPathPrefix + "Lab6 - output.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (writer != null) {
            // TASK: Create an airline.
            Airplane[] airline = createRandomAirplanes();
            airplanesToFile(dataPathPrefix + "Lab6 - airplanes data.txt", airline, true);

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

            // Additional TASK: sort random airplanes by speed
            Arrays.sort(airline, new SortByCruiseSpeed());
            airplanesToFile(dataPathPrefix + "Lab6 - rand by speed.txt", airline, false);

            // Additional TASK: sort random airplanes by FC efficiency
            Arrays.sort(airline, new SortByFuelConsumptionEfficiency());
            airplanesToFile(dataPathPrefix + "Lab6 - rand by FC efficiency.txt", airline, false);
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
                    int cargoWeight = (int) (Math.random() *
                            (((AirFreighter) airplanes[i]).getMTOW() - airplanes[i].getWeight()));
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

class SortByFuelConsumptionEfficiency implements Comparator<Airplane> {

    @Override
    public int compare(Airplane o1, Airplane o2) {
        double comparing = o2.getFuelConsumptionEfficiency() - o1.getFuelConsumptionEfficiency();
        return ((int) Math.signum(comparing));
    }
}

class SortByCruiseSpeed implements Comparator<Airplane> {

    @Override
    public int compare(Airplane o1, Airplane o2) {
        return o2.getCruiseSpeed() - o1.getCruiseSpeed();
    }
}
