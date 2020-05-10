package laptop;

import accessor.Accessor;
import org.springframework.stereotype.Component;

@Component
public class LaptopDatabase implements ILaptop{
    @Override
    public void save(Laptop laptop) {
        try {
            Accessor ac = Accessor.getInstance();
            ac.selectDatabase("laptops");
            ac.selectTable();
            ac.insertLaptop(laptop);
            ac.closeConnection();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
