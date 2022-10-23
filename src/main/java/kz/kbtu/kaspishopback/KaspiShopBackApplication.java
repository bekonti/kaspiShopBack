package kz.kbtu.kaspishopback;

import kz.kbtu.kaspishopback.domain.KsProduct;
import kz.kbtu.kaspishopback.domain.KsRole;
import kz.kbtu.kaspishopback.domain.KsUser;
import kz.kbtu.kaspishopback.service.ProductService;
import kz.kbtu.kaspishopback.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class KaspiShopBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaspiShopBackApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, ProductService productService) {
        return args -> {
            userService.saveRole(new KsRole(null, "ROLE_USER"));
            userService.saveRole(new KsRole(null, "ROLE_MANAGER"));
            userService.saveRole(new KsRole(null, "ROLE_ADMIN"));
            userService.saveRole(new KsRole(null, "ROLE_SUPER_ADMIN"));
            KsUser john = new KsUser(null, "John Wick", "john", "1234", new ArrayList<>());
            KsUser will = new KsUser(null, "Will Wick", "will", "1234", new ArrayList<>());
            userService.saveUser(john);
            userService.saveUser(will);
            userService.saveUser(new KsUser(null, "Tom Holland", "tom", "1234", new ArrayList<>()));
            userService.saveUser(new KsUser(null, "Andrew Garfield", "andrew", "1234", new ArrayList<>()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_MANAGER");
            userService.addRoleToUser("will", "ROLE_ADMIN");
            userService.addRoleToUser("tom", "ROLE_MANAGER");
            userService.addRoleToUser("andrew", "ROLE_ADMIN");
            userService.addRoleToUser("andrew", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("andrew", "ROLE_USER");

            productService.save(new KsProduct(null, "APPLE", "iPhone 14 Pro", "прям новый не бита не крашена 66", 350_000, john));
            productService.save(new KsProduct(null, "APPLE", "iPad Pro 11", "мощьный iPad", 550_000, will));
            productService.save(new KsProduct(null, "APPLE", "MacBook Pro 14 M1 Pro", "Для Профи Нотебуки", 1_250_000, john));
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}
