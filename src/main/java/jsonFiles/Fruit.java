package jsonFiles;

/** Class Fruit */
public class Fruit {
    private String name;
    private String color;
    private double price;
    private boolean organic;

    /**
     * Class Fruit
     * @param name name of fruit
     * @param color color
     * @param price price
     * @param isOrganic whether organic or not
     */
    public Fruit(String name, String color, double price, boolean isOrganic) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.organic = isOrganic;
    }

    /**
     * toString
     * @return string representation of this fruit
     */
    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", organic=" + organic +
                '}';
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOrganic() {
        return organic;
    }


}
