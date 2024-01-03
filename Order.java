import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Order {
    //order class 는 3개의 필드를 가진다. 1.총 메뉴 목록 2. 장바구니 목록 3. 음료 종류 목록
    private final ArrayList<Drink> drinkList = new ArrayList<>();
    private final ArrayList<Drink> cartList = new ArrayList<>();
    private final ArrayList<String> drink_typeList = new ArrayList<>();

    //메소드는 public 에서 private 으로 나열
    public void printLine() {
        System.out.println("-----------------------------------");
    }
    public void addDrinks(Drink...drink){ //drinkList에 drink들을 추가한다.
        drinkList.addAll(Arrays.asList(drink));

        for (Drink d : drink) {//drinkTypE 또한 리스트에 추가한다.
            if (!drink_typeList.contains(d.drink_type)) {
                drink_typeList.add(d.drink_type);
            }
        }

    }
    public void printCategory() { //메인 메뉴판
        printLine();
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n[ 음료 카테고리 ]");

        //음료 종류 출력
        for (int i = 0; i < drink_typeList.size(); i++) {
            System.out.println(i + 1 + ". " + drink_typeList.get(i));
        }

        //메뉴 옵션 출력 메소드
        printOtherOption(0);

        //선택지 입력받기
        int num = getNum();

        if(num<drink_typeList.size()+1&&num!=0){
            printTypeDrink(num);
        }else {
            runOtherOption(num);
        }
    }
    private void runOtherOption(int num) {
        switch (num) {
            case 9 -> printCart();
            case 0 -> endOrder();
            case 8 -> printCategory();
            case 7 -> orderCart();
//            default -> 예외처리 하기.
        }
    }

    private void orderCart() {
        int total_price = 0;
        printLine();
        System.out.println("고객님, 주문하신");
        for (Drink drink : cartList) {
            System.out.println("     " + drink.name);
            total_price+=drink.price;
        }
        System.out.println("이 나왔습니다.\n금액은 총 "+total_price+"원 입니다.");
        endOrder();
    }

    private void endOrder() {
        printLine();
        System.out.println("이용해 주셔서 감사합니다. 땡큐");
    }

    private void printCart() {
        printLine();
        System.out.println(" [ 장바구니 목록 ] ");
        for (Drink drink : cartList) {
            System.out.println(drink.name);
        }
        printOtherOption(2);
        int num = getNum();
        runOtherOption(num);
    }

    private void printOtherOption(int state) {
        if(state==0){ //카테고리에서
            System.out.println("""

                [ 다른 선택지 ]
                9. 장바구니 가기
                0. 주문을 종료하기""");
        } else if (state==1) { //장바구니를 추가한 다음
            System.out.println("""

                [ 다른 선택지 ]
                8. 카테고리로 가기
                9. 장바구니 가기
                0. 주문을 종료하기""");
        } else if (state==2) { // 장바구니에서
            System.out.println("""

                [ 다른 선택지 ]
                7. 계산하기
                8. 카테고리 가기
                0. 주문을 종료하기""");
        }
    }



    private int getNum() {
        System.out.print("\n숫자를 입력하세요 : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }



    private void printTypeDrink(int inputNum) {
        printLine();
        System.out.println("[ "+drink_typeList.get(inputNum-1) +" 카테고리 ]");
        ArrayList<Drink> type_drinkList = new ArrayList<>();

        for (Drink drink : drinkList) {

            if (drink.drink_type.equals(drink_typeList.get(inputNum - 1))) {
                type_drinkList.add(drink);
                System.out.println(type_drinkList.size() + ". " + drink.name + "  " + drink.price + "  " + drink.explanation);
            }

        }

        printOtherOption(1);
        int num2 = getNum();

        if(num2<8){
            addToCart(type_drinkList.get(num2-1));
        }else{
            runOtherOption(num2);
        }
    }

    private void addToCart(Drink drink) {
        printLine();
        System.out.println(drink.name + " 이 장바구니에 담겼습니다.");
        cartList.add(drink);
        printOtherOption(1);
        int num = getNum();
        if(num == 9){
            printCart();
        }else {
            runOtherOption(num);
        }
    }




}