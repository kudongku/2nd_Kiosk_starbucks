import Menu.Drink;
import Menu.DrinkType;

import java.util.*;

public class Order {
    //order class 는 3개의 필드를 가진다. 1.총 메뉴 목록 2. 장바구니 목록 3. 음료 종류 목록
    private final ArrayList<Drink> drinkList = new ArrayList<>();
    private final Map<String, Integer> cartList = new HashMap<>();
    // cartList = {음료이름사이즈 : 개수}
    private final ArrayList<DrinkType> drinkTypeList = new ArrayList<>();
    private final ArrayList<Map<String, Integer>> payedList = new ArrayList<>();

    public void open(Drink... drinks) {
        drinkList.addAll(Arrays.asList(drinks));
        for (Drink d : drinkList) {//drinkType 또한 리스트에 추가한다.

            if (!drinkTypeList.contains(d.getDrinkType())) {
                drinkTypeList.add(d.getDrinkType());
            }

        }
    }

    public ArrayList<DrinkType> getDrinkTypeList() {
        return drinkTypeList;
    }

    public ArrayList<Drink> getDrinkList(int inputNum) {
        ArrayList<Drink> drinkListByType = new ArrayList<>();

        for (Drink drink : drinkList) {

            if (drink.getDrinkType().equals(drinkTypeList.get(inputNum - 1))) {
                drinkListByType.add(drink);
            }

        }

        return drinkListByType;
    }

    public void addToCart(Drink orderedDrink, String size) {
        String drinkName = orderedDrink.getName();

        if (cartList.containsKey(drinkName + " " + size)) {
            int temp = cartList.get(drinkName + " " + size);
            cartList.put(drinkName + " " + size, ++temp);
        } else {
            cartList.put(drinkName + " " + size, 1);
        }

    }

    public int getTheBill() {
        int total_price = 0;

        for (String str : cartList.keySet()) {
            total_price += getPrice(str) * cartList.get(str);
        }

        return total_price;
    }

    public int getTotalBill() {
        int total_price = 0;

        for(Map<String, Integer> map : payedList){
            for (String str : map.keySet()) {
                total_price += getPrice(str) * map.get(str);
            }
        }


        return total_price;
    }

    public Map<String, Integer> getCartList() {
        return cartList;
    }

    public int getPrice(String str) {
            int size_price = 0;

            if (str.split(" ")[1].equals("그란데")) {
                size_price += 500;
            } else if (str.split(" ")[1].equals("벤티")) {
                size_price += 1000;
            }

            for (Drink drink : drinkList) {

                if (drink.getName().equals(str.split(" ")[0])) {
                    size_price+= drink.getPrice();
                }

            }

        return size_price;
    }

    public void emptyOutCart() {
        Map<String, Integer> temp = new HashMap<>(cartList);
        payedList.add(temp);
        cartList.clear();
    }

    public ArrayList<Map<String, Integer>> getPayedList() {
        return payedList;
    }
}

