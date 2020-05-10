package laptop;

import org.springframework.stereotype.Component;

import java.io.FileWriter;

@Component
public class LaptopTXT implements ILaptop {

    @Override
    public void save(Laptop laptop) {
        String result = laptop.toString() + "\n";
        try(FileWriter wr = new FileWriter("laptops.txt")){
            wr.write(result);
            wr.flush();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
