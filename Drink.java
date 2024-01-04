public class Drink extends Menu{
    int price;
    Drink_type drink_type;
    public Drink(String name, String explanation, int price, Drink_type drink_type) {
        super(name, explanation);
        this.price = price;
        this.drink_type = drink_type;
    }

}
