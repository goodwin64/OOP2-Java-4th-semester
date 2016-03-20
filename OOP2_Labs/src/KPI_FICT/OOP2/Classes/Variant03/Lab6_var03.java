package KPI_FICT.OOP2.Classes.Variant03;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 20.03.2016.
 */
public class Lab6_var03 {
    public static void main(String[] args) {
        Airplane ap1 = new Airplane();
        Airplane ap2 = new Airplane();
        ap2.fuelConsumption = 10.5;
        ap2.mass = 992.4;
        ap2.model = "Cessna 172";

        System.out.println(ap2.repairEngine(-5.2)); // false
        System.out.println(ap2.repairEngine(5.2)); // true
        System.out.println(ap2.fuelConsumption); // 5.3
        System.out.println(ap1.ID); // 1
        System.out.println(ap2.ID); // 2
    }
}

/**
 * Parent class for all airplanes
 */
class Airplane {
    public int flightDistance;
    public int ID;
    public static int maxID = 0;
    public double fuelConsumption;
    public double mass; // kg
    public String model;

    public Airplane() {
        this.flightDistance = 0;
        this.ID = maxID + 1;
        this.maxID++;
    }

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
