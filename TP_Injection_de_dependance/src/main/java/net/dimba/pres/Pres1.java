package net.dimba.pres;

import net.dimba.metier.MetierImpl;
import net.dimba.ext.DaoImplV2;

public class Pres1 {
    public static void main(String[] args) {
       // DaoImpl d = new DaoImpl();
        DaoImplV2 d = new DaoImplV2();
        MetierImpl metier = new MetierImpl(d);
        metier.setDao(d);
        System.out.println("RES " +metier.calcul());
    }
}
