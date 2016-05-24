package ua.kpi.fict.oop2.tests.variant12;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 23.05.2016.
 */
public class MusicException extends Exception {
    public MusicException() {
        super();
    }

    public MusicException(String message) {
        super(message);
    }

    public MusicException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicException(Throwable cause) {
        super(cause);
    }
}
