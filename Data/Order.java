package Data;

import Menu.Cake;
import Menu.Drink;
import Menu.DrinkType;

import java.util.*;

public class Order {
    //order class 는 3개의 필드를 가진다. 1.음료 메뉴 목록 2.케이크 메뉴 목록 3.음료 종류 목록
    private final ArrayList<Drink> drinkList = new ArrayList<>();
    private final ArrayList<Cake> cakeList = new ArrayList<>();
    private final ArrayList<DrinkType> drinkTypeList = new ArrayList<>();


    public void addList(ArrayList<Drink> listA, ArrayList<Cake> listB) {
        this.drinkList.addAll(listA);
        this.cakeList.addAll(listB);

        for (Drink d : this.drinkList) {//drinkType 또한 리스트에 추가한다.

            if (!drinkTypeList.contains(d.getDrinkType())) {
                drinkTypeList.add(d.getDrinkType());
            }

        }
    }
    public ArrayList<Drink> getDrinkList() {
        return drinkList;
    }

    public ArrayList<Cake> getCakeList() {
        return cakeList;
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

}

