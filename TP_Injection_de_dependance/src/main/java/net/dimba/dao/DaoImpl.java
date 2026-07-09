package net.dimba.dao;

import org.springframework.stereotype.Component;

@Component("d")
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("version de la DB");
         double t = 34;
        return t;
    }
}
