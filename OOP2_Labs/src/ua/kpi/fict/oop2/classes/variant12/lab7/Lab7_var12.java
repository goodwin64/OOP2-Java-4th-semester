package ua.kpi.fict.oop2.classes.variant12.lab7;

import ua.kpi.fict.oop2.classes.variant12.Lab6_var12.Rock;
import ua.kpi.fict.oop2.classes.variant12.Lab6_var12.Disco_80;
import ua.kpi.fict.oop2.classes.variant12.Lab6_var12.Classic;
import ua.kpi.fict.oop2.classes.variant12.Lab6_var12.Music;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 22.05.2016.
 */
public class Lab7_var12 {
    public static void main(String[] args) {
        SuperPuperMegaDuperGyperCyberListTurboPlus3000<Music> listTurboPlus3000 =
                new SuperPuperMegaDuperGyperCyberListTurboPlus3000<>(
                        new Music[]{
                                new Rock("A", "A", 1),
                                new Disco_80("B", "B", 2),
                                new Classic("C", "C", 3)
                        }
                );
        listTurboPlus3000.add(2, new Music("F", "F", 16));
        listTurboPlus3000.add(2, new Music("F", "F", 16));
        listTurboPlus3000.add(2, new Music("F", "F", 16));
        System.out.println(listTurboPlus3000.indexOf(new Music("F", "F", 16)));     // 2
        System.out.println(listTurboPlus3000.lastIndexOf(new Music("F", "F", 16))); // 3
        System.out.println(listTurboPlus3000.set(3, new Disco_80("D", "D", 2)));
        System.out.println(listTurboPlus3000.get(0)); // 1
        System.out.println(listTurboPlus3000.get(-3)); // 1
        System.out.println(listTurboPlus3000.get(4)); // 1
        System.out.println(listTurboPlus3000.subList(2,5));
    }
}
