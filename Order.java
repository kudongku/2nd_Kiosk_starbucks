import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Order {
    //order class 는 3개의 필드를 가진다. 1.총 메뉴 목록 2. 장바구니 목록 3. 음료 종류 목록
    private final ArrayList<Drink> drinkArr = new ArrayList<>();
    private final ArrayList<Drink> cartArr = new ArrayList<>();
    private final ArrayList<String> drink_typeList = new ArrayList<>();

    //메소드는 public 에서 private 으로 나열
    public void printLine() {
        System.out.println("-----------------------------------");
    }
    public void addDrinks(Drink...drink){
        drinkArr.addAll(Arrays.asList(drink));

        for (Drink d : drink) {
            if (!drink_typeList.contains(d.drink_type)) {
                drink_typeList.add(d.drink_type);
            }
        }

    }
    public void printCategory() { //메인 메뉴판
        printLine();
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n[ 음료 카테고리 ]");

        for (int i = 0; i < drink_typeList.size(); i++) {
            System.out.println(i + 1 + ". " + drink_typeList.get(i));
        }

        //메뉴 옵션 출력 메소드
        getOtherOption(0);
        int num = getNum();

        if(num<9&&num!=0){
            getTypeDrink(num, drink_typeList);
        }else {
            runOtherOption(num);
        }
    }

    private void runOtherOption(int num) {
        if(num == 9){
            printCart();
        }else if(num==0){
            endOrder();
        }else if(num==8){
            printCategory();
        } else if(num==7){
            orderCart();
        }
    }

    private void orderCart() {
        printLine();
        System.out.println("고객님, 주문하신");
        for (Drink drink : cartArr) {
            System.out.println("     " + drink.name);
        }
        System.out.println("이 나왔습니다.");
    }

    private void endOrder() {
        printLine();
        System.out.println("이용해 주셔서 감사합니다. 땡큐");
    }

    private void printCart() {
        printLine();
        System.out.println(" [ 장바구니 목록 ] ");
        for(int i=0; i< cartArr.size(); i++){
            System.out.println(i+1+", "+ cartArr.get(i).name);
        }
        getOtherOption(2);
        int num = getNum();
        runOtherOption(num);
    }

    private void getOtherOption(int num) {
        if(num==0){ //카테고리에서
            System.out.println("""

                [ 다른 선택지 ]
                9. 장바구니 가기
                0. 주문을 종료하기""");
        } else if (num==1) { //장바구니를 추가한 다음
            System.out.println("""

                [ 다른 선택지 ]
                8. 카테고리로 가기
                9. 장바구니 가기
                0. 주문을 종료하기""");
        } else if (num==2) { // 장바구니에서
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



    private void getTypeDrink(int inputNum, ArrayList<String> drink_typeList) {
        printLine();
        System.out.println("[ "+drink_typeList.get(inputNum-1) +" 카테고리 ]");
        ArrayList<Drink> type_drinkList = new ArrayList<>();

        for (Drink drink : drinkArr) {

            if (drink.drink_type.equals(drink_typeList.get(inputNum - 1))) {
                type_drinkList.add(drink);
                System.out.println(type_drinkList.size() + ". " + drink.name + "  " + drink.price + "  " + drink.explanation);
            }

        }

        getOtherOption(1);
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
        cartArr.add(drink);
        getOtherOption(1);
        int num = getNum();
        if(num == 9){
            printCart();
        }else {
            runOtherOption(num);
        }
    }




}