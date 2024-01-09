package Data;

import Data.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Payed {
    private final ArrayList<Map<String, Integer>> payedList = new ArrayList<>();

    public ArrayList<Map<String, Integer>> getPayedList() {
        return payedList;
    }

    public int getTotalBill(Order order) {
        int total_price = 0;

        for(Map<String, Integer> map : getPayedList()){
            for (String str : map.keySet()) {
                total_price += order.getPrice(str) * map.get(str);
            }
        }


        return total_price;
    }

    public void emptyOutCart(Cart cart) {
        Map<String, Integer> temp = new HashMap<>(cart.getCartList());
        this.getPayedList().add(temp);
        cart.clearCartList();
    }
}
