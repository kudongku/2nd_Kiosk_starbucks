package Data;

import Menu.Drink;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<String, Integer> cartList = new HashMap<>();
    // cartList = {음료이름사이즈 : 개수}

    public Map<String, Integer> getCartList() {
        return cartList;
    }

    public void addToCart(Drink orderedDrink, String size) {
        String drinkName = orderedDrink.getName();

        if (getCartList().containsKey(drinkName + " " + size)) {
            int temp = cartList.get(drinkName + " " + size);
            cartList.put(drinkName + " " + size, ++temp);
        } else {
            cartList.put(drinkName + " " + size, 1);
        }

    }

    public int getTheBill(Order order) {
        int total_price = 0;

        for (String str : cartList.keySet()) {
            total_price += order.getPrice(str) * cartList.get(str);
        }

        return total_price;
    }

    public void clearCartList() {
        cartList.clear();
    }
}
