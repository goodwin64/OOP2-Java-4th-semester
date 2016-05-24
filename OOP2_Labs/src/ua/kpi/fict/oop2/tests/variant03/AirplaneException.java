package ua.kpi.fict.oop2.tests.variant03;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 23.05.2016.
 */
public class AirplaneException extends Exception {
    public AirplaneException() {
        super();
    }

    public AirplaneException(String message) {
        super(message);
    }

    public AirplaneException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirplaneException(Throwable cause) {
        super(cause);
    }
}
