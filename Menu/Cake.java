package Menu;

public class Cake extends Menu {
    private final int price;

    public Cake(String name, String explanation, int price) {
        super(name, explanation);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
