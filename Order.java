import java.util.*;

public class Order {
    //order class 는 3개의 필드를 가진다. 1.총 메뉴 목록 2. 장바구니 목록 3. 음료 종류 목록
    private final ArrayList<Drink> drinkList = new ArrayList<>();
//    private ArrayList<Drink> cartList = new ArrayList<>();
    private Map<Drink, Integer> cartList = new HashMap<>();
    private final ArrayList<Category> drink_typeList = new ArrayList<>();

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
        System.out.println("아래 메뉴판을 보시고 카테고리를 골라 입력해주세요.\n\n[ 음료 카테고리 ]");

        //음료 종류 출력
        for (int i = 0; i < drink_typeList.size(); i++) {
            StringBuilder temp = new StringBuilder(drink_typeList.get(i).name);

            temp.append(" ".repeat(Math.max(0, 10 - temp.length())));

            System.out.println(i+1+". "+temp+"| "+drink_typeList.get(i).explanation);
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
        for (Drink drink : cartList.keySet()) {
            System.out.println("     " + drink.name);
            total_price+=drink.price * cartList.get(drink);
        }
        System.out.println("이 나왔습니다.\n금액은 총 "+total_price+"원 입니다.\n계산하시겠습니까?\n1.계산\n2.더 주문하기");
        boolean confirmed = (getNum()==1);
        if(confirmed){
            endOrder();
        }else{
            printCategory();
        }

    }

    private void endOrder() {
        printLine();
        System.out.println("주문을 종료하시겠습니까?\n1.종료\n2.새로 주문하기");
        boolean confirmed = (getNum()==1);
        if(confirmed){
             System.out.println("이용해 주셔서 감사합니다. 땡큐");
             System.exit(0);
        }else{
            cartList = new HashMap<>();
            printCategory();
        }
    }

    private void printCart() {
        printLine();
        System.out.println(" [ 장바구니 목록 ] ");
        for (Drink drink : cartList.keySet()) {
            System.out.print(drink.name+ "     ");
            System.out.println(cartList.get(drink));
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
        System.out.println("아래 음료메뉴판을 보시고 음료를 골라 입력해주세요.\n\n[ "+drink_typeList.get(inputNum-1).name +" 카테고리 ]");
        ArrayList<Drink> type_drinkList = new ArrayList<>();

        for (Drink drink : drinkList) {

            if (drink.drink_type.equals(drink_typeList.get(inputNum - 1))) {
                StringBuilder temp = new StringBuilder(drink.name);
                temp.append(" ".repeat(Math.max(0, 10 - temp.length())));

                type_drinkList.add(drink);
                System.out.println(type_drinkList.size() + ". " + temp + "  | " + drink.price + "  | " + drink.explanation);
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
        StringBuilder temp = new StringBuilder(drink.name);
        temp.append(" ".repeat(Math.max(0, 10 - temp.length())));
        printLine();
        System.out.println(temp + "  | " + drink.price + "  | " + drink.explanation+"\n위 메뉴를 장바구니에 추가하시겠습니까?\n1.확인\n2.취소");
        boolean confirmed = (getNum()==1);
        if(confirmed){
            printLine();
            System.out.println(drink.name + " 이 장바구니에 담겼습니다.");

            if(cartList.containsKey(drink)){
                int val = cartList.get(drink);
                cartList.put(drink, val+1);
            }else cartList.put(drink, 1);

            printOtherOption(1);
            int num = getNum();
            if(num == 9){
                printCart();
            }else {
                runOtherOption(num);
            }
        }else {
            printCategory();
        }
    }
}