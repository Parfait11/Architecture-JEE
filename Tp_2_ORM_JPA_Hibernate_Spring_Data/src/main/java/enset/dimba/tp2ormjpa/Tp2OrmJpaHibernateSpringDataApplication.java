package enset.dimba.tp2ormjpa;

import enset.dimba.tp2ormjpa.entities.*;
import enset.dimba.tp2ormjpa.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Tp2OrmJpaHibernateSpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp2OrmJpaHibernateSpringDataApplication.class, args);
    }

    // Gestion des Produits
    @Bean
    CommandLineRunner testProducts(ProductRepository productRepository) {
        return args -> {
            log.info("===== GESTION DES PRODUITS =====");

            productRepository.save(Product.builder().name("Farine").price(200).quantity(9).build());
            productRepository.save(Product.builder().name("Huiles").price(130).quantity(3).build());
            productRepository.save(Product.builder().name("Levures").price(18).quantity(13).build());
            productRepository.save(Product.builder().name("Sucres").price(9).quantity(6).build());
            productRepository.save(Product.builder().name("Aromes").price(8).quantity(9).build());

            log.info("--- Liste des produits ---");
            productRepository.findAll().forEach(p -> log.info("{}", p));

            productRepository.findById(1L).ifPresentOrElse(
                    p -> log.info("Produit id=1 trouvé : {}", p),
                    () -> log.warn("Produit id=1 introuvable")
            );

            log.info("--- Recherche de produits contenant 'C' ---");
            productRepository.findByNameContains("C").forEach(p -> log.info("{}", p));

            productRepository.findById(1L).ifPresent(p -> {
                p.setPrice(14000);
                p.setQuantity(15);
                productRepository.save(p);
                log.info("Produit mis à jour : {}", p);
            });

            log.info("--- Suppression du produit id=4 ---");
            productRepository.deleteById(4L);
            log.info("Produits restants : {}", productRepository.count());
        };
    }

    // Gestion de l'hôpital
    @Bean
    CommandLineRunner testHospital(
            PatientRepository patientRepo,
            MedecinRepository medecinRepo,
            RendezVousRepository rdvRepo,
            ConsultationRepository consultationRepo
    ) {
        return args -> {
            log.info("===== HOPITAL =====");

            Patient p1 = patientRepo.save(Patient.builder().nom("Fortune").dateNaissance(new Date()).malade(false).score(56).build());
            patientRepo.save(Patient.builder().nom("Parfait").dateNaissance(new Date()).malade(true).score(23).build());
            patientRepo.save(Patient.builder().nom("Dimba").dateNaissance(new Date()).malade(false).score(78).build());

            Medecin m1 = medecinRepo.save(Medecin.builder().nom("Roy").email("roy@hopi.com").specialite("Pediatre").build());
            medecinRepo.save(Medecin.builder().nom("Veranne").email("vera@hopi.com").specialite("Infirmiere").build());

            log.info("--- Liste des patients ---");
            patientRepo.findAll().forEach(p ->
                    log.info("ID={} | Nom={} | Malade={} | Score={}", p.getId(), p.getNom(), p.isMalade(), p.getScore())
            );

            log.info("--- Patients malades ---");
            patientRepo.findByMalade(true).forEach(p -> log.info("{}", p));

            RendezVous rdv = rdvRepo.save(RendezVous.builder()
                    .date(new Date())
                    .status(StatusRDV.PENDING)
                    .patient(p1)
                    .medecin(m1)
                    .build());

            consultationRepo.save(Consultation.builder()
                    .dateConsultation(new Date())
                    .rapport("Le patient est atteint du palu")
                    .rendezVous(rdv)
                    .build());

            log.info("--- Rendez-vous ---");
            rdvRepo.findAll().forEach(r ->
                    log.info("RDV#{} | Patient={} | Médecin={} | Status={}",
                            r.getId(), r.getPatient().getNom(), r.getMedecin().getNom(), r.getStatus())
            );
        };
    }

    // Gestion des utilisateurs et rôles
    @Bean
    CommandLineRunner testUsersRoles(AppUserRepository userRepo, AppRoleRepository roleRepo) {
        return args -> {
            log.info("===== USERS / ROLES =====");

            AppRole adminRole = roleRepo.save(AppRole.builder().roleName("ADMIN").description("Administrateur").build());
            AppRole userRole = roleRepo.save(AppRole.builder().roleName("USER").description("Utilisateur standard").build());

            userRepo.save(AppUser.builder()
                    .username("admin")
                    .password("admin@123")
                    .email("admin@gmail.com")
                    .roles(List.of(adminRole, userRole))
                    .build());

            userRepo.save(AppUser.builder()
                    .username("adam")
                    .password("adam123")
                    .email("adam@gmail.com")
                    .roles(List.of(userRole))
                    .build());

            log.info("--- Utilisateurs ---");
            userRepo.findAll().forEach(u ->
                    log.info("User={} | Rôles={}", u.getUsername(),
                            u.getRoles().stream().map(AppRole::getRoleName).toList())
            );
        };
    }
}