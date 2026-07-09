package net.dimba.pres;

import net.dimba.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXML {
    public static void main(String[] args) {
        ApplicationContext SpringContext = new ClassPathXmlApplicationContext("config.xml");
        IMetier metier = (IMetier) SpringContext.getBean("metier");
        System.out.println("RES = " + metier.calcul());

    }
}
