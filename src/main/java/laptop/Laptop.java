package laptop;



public class Laptop {
    private String producer;
    private String model;
    private int year;

    public Laptop() {
        producer = "Acer";
        model = "Aspire5";
        year = 2019;
    }

    public String getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +'}';
    }
}
