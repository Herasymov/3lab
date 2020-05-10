package config;

import laptop.Laptop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"laptopService", "laptop"})
public class Config {

    @Bean(name = "defaultLaptop")
    public Laptop getDefaultLaptop(){
        return new Laptop();
    }
}
