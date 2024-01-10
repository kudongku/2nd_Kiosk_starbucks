package Data;

import Menu.Cake;
import Menu.Drink;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<String, Integer> cartList = new HashMap<>();
    // cartList = {음료이름사이즈 : 개수}

    public Map<String, Integer> getCartList() {
        return cartList;
    }

    public void addToCart(String productName, String size) {

        if (getCartList().containsKey(productName + " " + size)) {
            int temp = cartList.get(productName + " " + size);
            cartList.put(productName + " " + size, ++temp);
        } else {
            cartList.put(productName + " " + size, 1);
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
