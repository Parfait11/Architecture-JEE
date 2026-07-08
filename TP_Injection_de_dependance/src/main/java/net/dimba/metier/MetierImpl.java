package net.dimba.metier;

import net.dimba.dao.DaoImpl;
import net.dimba.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {


    private IDao dao;

//    public MetierImpl(IDao dao) {
//        this.dao = dao;
//    }

    @Autowired
    public MetierImpl(@Qualifier("d") IDao dao) {  // Specify the bean name "d"
        this.dao = dao;
    }
    public MetierImpl() {

    }

    @Override
    public double calcul(){
        double t = dao.getData();
        double res = t * 36 * Math.PI/2 * Math.cos(t);
        return res;
    }

    //Injection dans l'attribut dao apres instantiation
    public void setDao(IDao dao){
        this.dao = dao;
    }
}
