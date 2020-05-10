import laptop.Laptop;
import laptopService.LaptopService;
import config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Laptop laptop = (Laptop)context.getBean("defaultLaptop");
        LaptopService laptopService = context.getBean(laptopService.class);
        laptopService.save(laptop);
    }
}
