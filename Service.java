import Data.Cart;
import Data.Order;
import Data.Payed;
import Menu.Cake;
import Menu.Drink;
import Menu.DrinkType;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Service {
    //order 인스턴스 생성
    private final Order order = new Order();
    private final Cart cart = new Cart();
    private final Payed payed = new Payed();

    public void startOrder() {
        System.out.println("""
             -----------------------------------
             스타벅스에 오신걸 환영합니다.""");

        //드링크 타입 인스턴스 생성
        DrinkType coffeeVar = new DrinkType("커피베리에이션", "커피를 활용한 음료입니다.");
        DrinkType teaVar = new DrinkType("티베리에이션", "티를 활용한 음료입니다.");
        DrinkType blended = new DrinkType("블렌디드", "시원하고 단맛을 자랑하는 음료입니다.");

        ArrayList<Drink> drinkList = new ArrayList<>();

        drinkList.add(new Drink("아메리카노", "에스프레소에 물을 탄 커피", 4500, coffeeVar));
        drinkList.add(new Drink("허니블랙티", "꿀을 탄 블랙티", 4000, teaVar));
        drinkList.add(new Drink("바나나요거트", "바나나를 넣은 요거트", 5000, blended));
        drinkList.add(new Drink("카페라테", "에스프레소에 우유를 탄 커피", 4500, coffeeVar));
        drinkList.add(new Drink("콜드브류", "저온으로 오랫동안 추출한 커피", 5000, coffeeVar));
        drinkList.add(new Drink("유자민트티", "유자청을 첨가한 민트티", 4500, teaVar));
        drinkList.add(new Drink("얼그레이티", "에스프레소에 우유를 탄 커피", 3500, teaVar));
        drinkList.add(new Drink("자바칩프라푸치노", "자바칩을 넣은 프라푸치노", 6000, blended));
        drinkList.add(new Drink("망고스무디", "망고를 갈아넣은 스무디", 5500, blended));

        ArrayList<Cake> cakeList = new ArrayList<>();

        cakeList.add(new Cake("생크림케이크", "촉촉한 초콜릿 생크림이 들어간 케이크", 7000));
        cakeList.add(new Cake("고구마케이크", "꿀고구마가 들어간 생크림 케이크", 7500));
        cakeList.add(new Cake("쇼콜라케이크", "라즈베리가 들어간 쇼콜라 케이크", 8000));
        cakeList.add(new Cake("티라미수케이크", "마스카포네 티라미수 케이크", 8000));

        //order.open 은 order 의 drinkList 필드에 drink 인스턴스들을 넣는다. 추가로, drinkList 를 순회하며 drinkType 리스트에도 인스턴스들을 넣는다.
        order.addList(drinkList, cakeList);
    }
    public int printCategory() {
        System.out.println("""
                    -----------------------------------
                    아래 메뉴판을 보시고 카테고리를 골라 입력해주세요.

                    [ 음료 카테고리 ]""");

        ArrayList<DrinkType> getDrinkTypeList = order.getDrinkTypeList();

        for (int i = 0; i < getDrinkTypeList.size(); i++) {
            System.out.println(i+1+". "+(getDrinkTypeList.get(i).getName())+
                    "   "+getDrinkTypeList.get(i).getExplanation());
        }

        System.out.println(getDrinkTypeList.size()+1+". 케이크"+
                "   스타벅스 특제 케이크의 달달한 맛을 느껴보세요");

        System.out.print("""
                
                [ 다른 선택지 ]
                8. 구매내역 조회하기
                9. 장바구니 가기
                0. 주문을 종료하기
                
                숫자를 입력하세요 :""");


        return getNumber();
    }
    public void finishOrder() {
        System.out.print("""
                        -----------------------------------
                        종료를 원하시면 숫자 '1'을
                        더 주문하시기를 원하시면 아무 숫자나 입력하세요

                        숫자를 입력하세요:""");

        if(getNumber()==1){
            System.exit(0);
        }
    }
    public void emptyOutCart(int state) {
        if(state==0){
            System.out.print("""
                           -----------------------------------
                           감사합니다, 결제가 정상적으로 처리되었습니다.
                           """);
            payed.emptyOutCart(cart);
        }else{
            System.out.print("""
                           -----------------------------------
                           장바구니가 초기화되었습니다.
                           """);
            cart.clearCartList();
        }
    }
    public void printTotalPayment() {
        System.out.println("-----------------------------------\n" +
                "[ 총 구매내역 조회 ] ");
        for (Map<String, Integer> map: payed.getPayedList()) {
            System.out.println("\n[ 개별 구매내역 조회 ] ");

            for (String str : map.keySet()) {
                System.out.println(str.split(" ")[0]+"   "+
                        str.split(" ")[1]+"   "+
                        map.get(str) + "개" +
                        "   "+order.getPrice(str)+"원");
            }

        }

        System.out.println("\n총 금액은 " + payed.getTotalBill(order) + " 원 입니다.");
    }
    public int printCart() {
        System.out.println("-----------------------------------\n[ 장바구니 목록 ]");
        //장바구니 목록 출력, 장바구니의 key 값에는 drink 의 이름과 사이즈가 띄어쓰기 로 합쳐져서 split(" ")을 해준다.
        for (String str : cart.getCartList().keySet()) {
            System.out.println(str.split(" ")[0]+"   "+
                    str.split(" ")[1]+"   "+
                    cart.getCartList().get(str) + "개" +
                    "   "+order.getPrice(str)+"원");
        }
        //order.getTheBill() 은 카트 리스트의 Key 값 안에 있는 drink 이름을 drink 리스트에서 찾아서 가격을 사이즈를 고려해서 조회해준다.
        System.out.print("입니다.\n\n금액은 총 " + cart.getTheBill(order) + "원 입니다.\n\n" +
                "계산하시겠습니까?\n\n계산을 원하시면 숫자 '1'을\n" +
                "장바구니를 초기화하고 싶으시면 숫자 '2'를\n" +
                "특정 제품을 삭제하고 싶으시면 숫자 '3'을\n" +
                "더 주문하시기를 원하시면 아무 숫자나 입력하세요\n" +
                "\n숫자를 입력하세요: ");
        return getNumber();
    }
    public int printDrinks(int inputNum) {
        String chosenDrinkTypeName;
        try {
            chosenDrinkTypeName = order.getDrinkTypeList().get(inputNum - 1).getName();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("범위 내의 숫자를 입력하세요!!!");
            return 0;
        }
            //어떤 카테고리를 입력했는지 출력
            System.out.println("-----------------------------------\n" +
                    "아래 음료메뉴판을 보시고 음료를 골라 입력해주세요.\n\n[ " +
                    chosenDrinkTypeName + " 카테고리 ]");
            //선택한 카테고리 안 drink 리스트를 가져와주는 메소드
            ArrayList<Drink> drinkListByType = order.getDrinkList(inputNum);

            //카테고리 안 drink 리스트를 출력
            for (int i = 0; i < drinkListByType.size(); i++) {
                System.out.println(i + 1 + ". " + drinkListByType.get(i).getName() +
                        "   " + drinkListByType.get(i).getPrice() + "   " + drinkListByType.get(i).getExplanation());
            }

            //예외는 while 반복문을 계속 순환하는 것으로 처리
            System.out.print("""
                                    
                    음료 카테고리로 나가고 싶으시면 음료 숫자 외에 다른 숫자를 입력해주세요
                                    
                    숫자입력하기:""");
            return getNumber();
    }
    public void printSize(int selectedDrinkNum, int selectedCategoryNum) {
        ArrayList<Drink> drinkListByType = order.getDrinkList(selectedCategoryNum);

        if(selectedDrinkNum>drinkListByType.size()){
            return;
        }

        System.out.print("-----------------------------------\n"+
                drinkListByType.get(selectedDrinkNum - 1).getName() +
                "\n\n사이즈를 선택해주세요." +
                " \n1.톨 (기본료 그대로)\n2.그란데 (500원 추가)\n3.벤티 (1000원 추가)" +
                "\n\n주문을 취소하시려면 1,2,3 외에 다른 숫자를 입력하세요 (카테고리로 이동합니다.)\n\n숫자를 입력하세요 :");

        //사이즈를 선택하도록
        int selectedSize = getNumber();

        String size = null;
        if(selectedSize==1){
            size="톨";
        }else if(selectedSize==2){
            size="그란데";
        } else if (selectedSize==3) {
            size="벤티";
        }

        if( size != null){
            System.out.println("-----------------------------------\n"+
                    drinkListByType.get(selectedDrinkNum - 1).getName() +" " + size + "  장바구니에 담겼습니다.");
            //카트는 Map 으로, key 에는 drink 이름과 사이즈가 합쳐지고, value 에는 개수가 카운트 된다.
            cart.addToCart(drinkListByType.get(selectedDrinkNum-1).getName(), size);
        }

    }
    public int getNumber() {
        int num;
        try{
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();
        }catch(InputMismatchException i){
            System.out.print("숫자를 입력하세요!!!! :");
            num = getNumber();
        }

        return num;
    }

    public int printCakes() {
        //어떤 카테고리를 입력했는지 출력
        System.out.println("""
                -----------------------------------
                아래 케이크메뉴판을 보시고 케이크를 골라 입력해주세요.

                [ 케이크 카테고리 ]""");

        //카테고리 안 drink 리스트를 출력
        for (int i = 0; i < order.getCakeList().size(); i++) {
            System.out.println(i + 1 + ". " + order.getCakeList().get(i).getName() +
                    "   " + order.getCakeList().get(i).getPrice() + "   " + order.getCakeList().get(i).getExplanation());
        }

        //예외는 while 반복문을 계속 순환하는 것으로 처리
        System.out.print("""
                                    
                    카테고리로 나가고 싶으시면 케이크 숫자 외에 다른 숫자를 입력해주세요
                                    
                    숫자입력하기:""");
        return getNumber();
    }

    public void printSize(int selectedCakeNum) {
        String cakename;
        try {
            cakename = order.getCakeList().get(selectedCakeNum-1).getName();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("범위 내의 숫자를 입력하세요!!!");
            return;
        }
        System.out.print("-----------------------------------\n"+
                cakename +
                "\n\n사이즈를 선택해주세요." +
                " \n1.조각\n2.한판(원래금액의 6배)" +
                "\n\n주문을 취소하시려면 1,2 외에 다른 숫자를 입력하세요 (카테고리로 이동합니다.)\n\n숫자를 입력하세요 :");

        //사이즈를 선택하도록
        int selectedSize = getNumber();

        String size = null;
        if(selectedSize==1){
            size="조각";
        }else if(selectedSize==2){
            size="한판";
        }

        if( size != null){
            System.out.println("-----------------------------------\n"+
                    cakename +" " + size + "  장바구니에 담겼습니다.");
            //카트는 Map 으로, key 에는 drink 이름과 사이즈가 합쳐지고, value 에는 개수가 카운트 된다.
            cart.addToCart(order.getCakeList().get(selectedCakeNum-1).getName(), size);
        }
    }

    public void cancelProductInCart() {
        System.out.println("""
                -----------------------------------
                [ 장바구니 목록 ]
                
                삭제를 원하시는 번호를 입력헤주세요
                삭제를 원하시지 않는다면, 0번을 입력하세요.
                """);
        //장바구니 목록 출력, 장바구니의 key 값에는 drink 의 이름과 사이즈가 띄어쓰기 로 합쳐져서 split(" ")을 해준다.
        int pointer = 1;
        for (String str : cart.getCartList().keySet()) {
            System.out.println(pointer+".  "+str.split(" ")[0]+"   "+
                    str.split(" ")[1]+"   "+
                    cart.getCartList().get(str) + "개" +
                    "   "+order.getPrice(str)+"원");
            pointer++;
        }
        System.out.print("숫자를 입력하세요 :");
        int selectedForDelete = getNumber();
        String keyToDelete = "";
        if (selectedForDelete!=0&&selectedForDelete<pointer){
            int pointer2 = 1;
            for (String str : cart.getCartList().keySet()) {
                if(pointer2==selectedForDelete){
                    keyToDelete = str;
                }
                pointer2++;
            }
            cart.getCartList().remove(keyToDelete);
            System.out.println("""
                -----------------------------------
                해당 상품이 삭제되었습니다.""");
        }
    }
}
