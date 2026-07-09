package enset.dimba.tp_3_spring_mvc;

import enset.dimba.tp_3_spring_mvc.entities.Product;
import enset.dimba.tp_3_spring_mvc.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp3SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp3SpringMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(ProductRepository repo) {
        return args -> {
            repo.save(new Product(null, "Doliprane 1000mg (boîte de 8)", 14.90, 90));
            repo.save(new Product(null, "Spasfon 80mg (boîte de 30)", 16.30, 60));
            repo.save(new Product(null, "Efferalgan 500mg (boîte de 16)", 12.20, 85));
            repo.save(new Product(null, "Smecta 3g (boîte de 20)", 19.80, 45));
            repo.save(new Product(null, "Immodium 2mg (boîte de 12)", 22.40, 55));
            repo.save(new Product(null, "Ventoline 100µg (inhalateur)", 38.50, 30));
        };
    }
}
