public class Drink extends Menu{
    private final int price;
    private final DrinkType drinkType;
    public Drink(String name, String explanation, int price, DrinkType drink_type) {
        super(name, explanation);
        this.price = price;
        this.drinkType = drink_type;
    }

    public int getPrice() {
        return price;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }
}
