package laptopService;

import laptop.Laptop;
import laptop.ILaptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LaptopService {
    @Autowired
    private List<ILaptop> laptops = new ArrayList<>();

    public void save(Laptop laptop){
        for(ILaptop ilaptop : this.laptops){
            iLaptop.save(laptop);
        }
    }
}
