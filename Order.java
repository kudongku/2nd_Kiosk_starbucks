import java.util.*;

public class Order {
    //order class 는 3개의 필드를 가진다. 1.총 메뉴 목록 2. 장바구니 목록 3. 음료 종류 목록
    private final ArrayList<Drink> drinkList = new ArrayList<>();
    private final ArrayList<ArrayList<String>> cartList = new ArrayList<>();
    // cartList = [[drink_name, size],[drink_name, size],[drink_name, size]]
    private final ArrayList<Drink_type> drink_typeList = new ArrayList<>();

    private int getNum() {
        System.out.print("\n숫자를 입력하세요 : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    private String buildStr(String str){
        StringBuilder strBuild = new StringBuilder(str);
        strBuild.append(" ".repeat(Math.max(0, 10 - strBuild.length())));
        return String.valueOf(strBuild);
    }

    public void on() {
        Drink_type coffeeVar = new Drink_type("커피베리에이션", "커피를 활용한 음료입니다.");
        Drink_type teaVar = new Drink_type("티베리에이션", "티를 활용한 음료입니다.");
        Drink_type blended = new Drink_type("블렌디드", "시원하고 단맛을 자랑하는 음료입니다.");
        drinkList.addAll(Arrays.asList(new Drink("아메리카노", "에스프레소에 물을 탄 커피", 4500, coffeeVar),
                new Drink("허니블랙티", "꿀을 탄 블랙티", 4000, teaVar),
                new Drink("바나나요거트", "바나나를 넣은 요거트", 5000, blended),
                new Drink("카페라테", "에스프레소에 우유를 탄 커피", 4500, coffeeVar),
                new Drink("콜드브류", "저온으로 오랫동안 추출한 커피", 5000, coffeeVar),
                new Drink("유자민트티", "유자청을 첨가한 민트티", 4500, teaVar),
                new Drink("얼그레이티", "에스프레소에 우유를 탄 커피", 3500, teaVar),
                new Drink("자바칩프라푸치노", "자바칩을 넣은 프라푸치노", 6000, blended),
                new Drink("망고스무디", "망고를 갈아넣은 스무디", 5500, blended)));

        for (Drink d : drinkList) {//drinkTypE 또한 리스트에 추가한다.
            if (!drink_typeList.contains(d.drink_type)) {
                drink_typeList.add(d.drink_type);
            }
        }

        System.out.println("-----------------------------------\n스타벅스에 오신걸 환영합니다.");

        //메인 주문표 생성
        printCategory();
    }

    public void printCategory() { //메인 메뉴판
        System.out.println("-----------------------------------\n아래 메뉴판을 보시고 카테고리를 골라 입력해주세요.\n\n[ 음료 카테고리 ]");

        //음료 종류 출력
        for (int i = 0; i < drink_typeList.size(); i++) {
            System.out.println(i+1+". "+buildStr(drink_typeList.get(i).name)+
                    drink_typeList.get(i).explanation);
        }

        //메뉴 옵션 출력 메소드
        printOtherOption(0);

        //선택지 입력받기
        int inputNum = getNum();

        if(inputNum<drink_typeList.size()+1&&inputNum!=0){
            System.out.println("-----------------------------------\n" +
                    "아래 음료메뉴판을 보시고 음료를 골라 입력해주세요.\n\n[ "+
                    drink_typeList.get(inputNum-1).name +" 카테고리 ]");
            ArrayList<Drink> type_drinkList = new ArrayList<>();

            for (Drink drink : drinkList) {

                if (drink.drink_type.equals(drink_typeList.get(inputNum - 1))) {
                    type_drinkList.add(drink);
                    System.out.println(type_drinkList.size() + ". " + buildStr(drink.name) + drink.price + "  | " + drink.explanation);
                }

            }

            printOtherOption(1);
            int num2 = getNum();

            if(num2<8){
                Drink drink_ordered = type_drinkList.get(--num2);
                System.out.println("""
                        -----------------------------------
                        사이즈를 선택해주세요.
                        1.톨
                        2.그란데
                        3.벤티""");
                int num = getNum();
                String size = "";
                if(num==1){
                    size="톨";
                }else if(num==2){
                    size="그란데";
                } else if (num==3) {
                    size="벤티";
                }
                addToCart(drink_ordered.name, size);
            }else{
                runOtherOption(num2);
            }
        }else {
            runOtherOption(inputNum);
        }
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
    private void runOtherOption(int num) {
        switch (num) {
            case 9 -> printCart();
            case 0 -> off();
            case 8 -> printCategory();
            case 7 -> orderCart();
//            default -> 예외처리 하기.
        }
    }

    private void addToCart(String drinkName, String size) {
        System.out.println("-----------------------------------\n"+
                buildStr(drinkName) + "  | " + size + "\n\n위 메뉴를 장바구니에 추가하시겠습니까?" +
                "\n1.확인\n2.취소");
        boolean confirmed = (getNum()==1);
        if(confirmed){
            System.out.println("-----------------------------------\n"+drinkName + size + " 이 장바구니에 담겼습니다.");
            ArrayList<String> tempArr = new ArrayList<>();
            tempArr.add(drinkName);
            tempArr.add(size);
            cartList.add(tempArr);

            printOtherOption(1);
            int num = getNum();
            runOtherOption(num);

        }else {
            printCategory();
        }
    }

    private void printCart() {
        System.out.println("-----------------------------------\n[ 장바구니 목록 ] ");
        Map<String, Integer> map = new HashMap<>();
        for(ArrayList<String> arr : cartList){
            String key = arr.get(0)+" "+arr.get(1);
            if(map.containsKey(key)){
                int tmep = map.get(key);
                map.put(key, ++tmep);
            }else{
                map.put(key, 1);
            }
        }
        for(String str : map.keySet()){
            System.out.println(buildStr(str.split(" ")[0]) + buildStr(str.split(" ")[1]) + map.get(str)+"개");
        }
        printOtherOption(2);
        int num = getNum();
        runOtherOption(num);
    }

    private void orderCart() {
        int total_price = 0;
        System.out.println("-----------------------------------\n고객님, 주문하신");
        Map<String, Integer> map = new HashMap<>();
        for(ArrayList<String> arr : cartList){
            String key = arr.get(0)+" "+arr.get(1);
            if(map.containsKey(key)){
                int tmep = map.get(key);
                map.put(key, ++tmep);
            }else{
                map.put(key, 1);
            }
        }
        for(String str : map.keySet()){
            System.out.print(buildStr("")+buildStr(str.split(" ")[0]) + buildStr(str.split(" ")[1]) +buildStr(map.get(str)+"개") );
            for(Drink drink: drinkList){
                if(drink.name.equals(str.split(" ")[0])){
                    System.out.println(drink.price+"원");
                    total_price += drink.price*map.get(str);
                }
            }
        }
        System.out.println("이 나왔습니다.\n금액은 총 "+total_price+"원 입니다.\n계산하시겠습니까?\n0.계산\n8.더 주문하기");
        runOtherOption(getNum());
    }

    private void off() {
        System.out.println("""
                -----------------------------------
                주문을 종료하시겠습니까?
                1.종료
                2.새로운 주문하기""");
        boolean confirmed = (getNum()==1);
        if(confirmed){
            System.out.println("이용해 주셔서 감사합니다. 땡큐");
            System.exit(0);
        }else{
            cartList.clear();
            printCategory();
        }
    }
}