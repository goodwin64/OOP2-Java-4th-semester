package ua.kpi.fict.oop2.tests.variant12;

import org.junit.Test;
import ua.kpi.fict.oop2.classes.variant12.lab5.Text;

import static org.junit.Assert.*;
import static ua.kpi.fict.oop2.classes.variant12.Lab5_var12.parseTextToObjects;
import static ua.kpi.fict.oop2.classes.variant12.Lab5_var12.replaceSomeWords;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 20.05.2016.
 */
public class Lab5_var12Test {
    @Test
    public void parseTextToObjectsTest() {
        String beforeReplacing = "one two three four FIVE";
        String afterReplacing = "one two three exampleWord exampleWord";
        String afterSecondReplacing = "exampleWord exampleWord three exampleWord exampleWord";

        // 1st replacing
        Text text = parseTextToObjects(beforeReplacing);
        replaceSomeWords(4, "exampleWord", text);
        assertEquals(afterReplacing, text.toString());

        // 2nd replacing
        replaceSomeWords(3, "exampleWord", text);
        assertEquals(afterSecondReplacing, text.toString());
    }
}
// Прекрасно слышу, но НАташа приехала приболевшей и с 22-00 лежит, спит......  не хочу ее будть, пусть отдахнет, замерзла наверное,у стала. Потому я тихо говорю. Молчу...
