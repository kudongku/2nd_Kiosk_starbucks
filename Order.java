import java.util.*;

public class Order {
    //order class 는 3개의 필드를 가진다. 1.총 메뉴 목록 2. 장바구니 목록 3. 음료 종류 목록
    private final ArrayList<Drink> drinkList = new ArrayList<>();
    private final Map<String, Integer> cartList = new HashMap<>();
    // cartList = {음료이름사이즈 : 개수}
    private final ArrayList<DrinkType> drinkTypeList = new ArrayList<>();

    public void open(Drink... drinks) {
        drinkList.addAll(Arrays.asList(drinks));
        for (Drink d : drinkList) {//drinkType 또한 리스트에 추가한다.

            if (!drinkTypeList.contains(d.drinkType)) {
                drinkTypeList.add(d.drinkType);
            }

        }
    }

    public ArrayList<DrinkType> getDrinkTypeList() {
        return drinkTypeList;
    }

    public ArrayList<Drink> getDrinkList(int inputNum) {
        ArrayList<Drink> drinkListByType = new ArrayList<>();

        for (Drink drink : drinkList) {

            if (drink.drinkType.equals(drinkTypeList.get(inputNum - 1))) {
                drinkListByType.add(drink);
            }

        }

        return drinkListByType;
    }

    public void addToCart(Drink orderedDrink, String size) {
        String drinkName = orderedDrink.name;

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
            int size_price = 0;

            if (str.split(" ")[1].equals("그란데")) {
                size_price += 500;
            } else if (str.split(" ")[1].equals("벤티")) {
                size_price += 1000;
            }

            for (Drink drink : drinkList) {

                if (drink.name.equals(str.split(" ")[0])) {
                    total_price += (size_price+drink.price) * cartList.get(str);
                }

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

                if (drink.name.equals(str.split(" ")[0])) {
                    size_price+=drink.price;
                }

            }

        return size_price;
    }
}

