import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //order 인스턴스 생성
        Order order = new Order();

        //드링크 타입 인스턴스 생성
        DrinkType coffeeVar = new DrinkType("커피베리에이션", "커피를 활용한 음료입니다.");
        DrinkType teaVar = new DrinkType("티베리에이션", "티를 활용한 음료입니다.");
        DrinkType blended = new DrinkType("블렌디드", "시원하고 단맛을 자랑하는 음료입니다.");

        //order.open 은 order 의 drinkList 필드에 drink 인스턴스들을 넣는다. 추가로, drinkList 를 순회하며 drinkType 리스트에도 인스턴스들을 넣는다.
        order.open(new Drink("아메리카노", "에스프레소에 물을 탄 커피", 4500, coffeeVar),
                new Drink("허니블랙티", "꿀을 탄 블랙티", 4000, teaVar),
                new Drink("바나나요거트", "바나나를 넣은 요거트", 5000, blended),
                new Drink("카페라테", "에스프레소에 우유를 탄 커피", 4500, coffeeVar),
                new Drink("콜드브류", "저온으로 오랫동안 추출한 커피", 5000, coffeeVar),
                new Drink("유자민트티", "유자청을 첨가한 민트티", 4500, teaVar),
                new Drink("얼그레이티", "에스프레소에 우유를 탄 커피", 3500, teaVar),
                new Drink("자바칩프라푸치노", "자바칩을 넣은 프라푸치노", 6000, blended),
                new Drink("망고스무디", "망고를 갈아넣은 스무디", 5500, blended));

        System.out.println("""
             -----------------------------------
             스타벅스에 오신걸 환영합니다.""");

        //hasFinishedOrder 는 사용자의 입력값에 따라 true로 변한다.
        boolean hasFinishedOrder = false;
        while(!hasFinishedOrder){
            System.out.println("""
                    -----------------------------------
                    아래 메뉴판을 보시고 카테고리를 골라 입력해주세요.

                    [ 음료 카테고리 ]""");

            //getDrinkTypeList()는 drinkType 리스트를 리턴해준다.
            for (int i = 0; i < order.getDrinkTypeList().size(); i++) {
                System.out.println(i+1+". "+(order.getDrinkTypeList().get(i).name)+
                        "   "+order.getDrinkTypeList().get(i).explanation);
            }

            System.out.print("""
                
                [ 다른 선택지 ]
                9. 장바구니 가기
                0. 주문을 종료하기
                
                숫자를 입력하세요 :""");

            Scanner sc = new Scanner(System.in);
            int inputNum = sc.nextInt();

            //사용자가 카테고리 숫자를 입력했을 경우, 예외처리는 while 반복문을 계속 순환하는 것으로 처리
            if(inputNum<=order.getDrinkTypeList().size()&&inputNum!=0){
                //어떤 카테고리를 입력했는지 출력
                System.out.println("-----------------------------------\n" +
                        "아래 음료메뉴판을 보시고 음료를 골라 입력해주세요.\n\n[ "+
                        order.getDrinkTypeList().get(inputNum-1).name +" 카테고리 ]");

                //선택한 카테고리 안 drink 리스트를 가져와주는 메소드
                ArrayList<Drink> drinkListByType = order.getDrinkList(inputNum);

                //카테고리 안 drink 리스트를 출력
                for (int i=0; i<drinkListByType.size(); i++) {
                    System.out.println(i+1 + ". " +drinkListByType.get(i).name+
                            "   "+drinkListByType.get(i).price+"   "+drinkListByType.get(i).explanation);
                }

                //예외는 while 반복문을 계속 순환하는 것으로 처리
                System.out.print("""
                
                음료 카테고리로 나가고 싶으시면 음료 숫자 외에 다른 숫자를 입력해주세요
                
                숫자입력하기:""");

                int inputNum2 = sc.nextInt();

                //drink 번호를 입력했을 경우
                if(inputNum2<=order.getDrinkTypeList().size()&&inputNum2!=0){
                    System.out.println("-----------------------------------\n"+
                            drinkListByType.get(inputNum2-1).name +
                            "\n\n사이즈를 선택해주세요." +
                            " \n1.톨 (기본료 그대로)\n2.그란데 (500원 추가)\n3.벤티 (1000원 추가)" +
                            "\n\n주문을 취소하시려면 1,2,3 외에 다른 숫자를 입력하세요 (카테고리로 이동합니다.)\n\n숫자를 입력하세요 :");

                    //사이즈를 선택하도록
                    int inputNum3 = sc.nextInt();

                    String size;
                    if(inputNum3==1){
                        size="톨";
                    }else if(inputNum3==2){
                        size="그란데";
                    } else if (inputNum3==3) {
                        size="벤티";
                    }else{
                        continue;
                    }

                    System.out.println("-----------------------------------\n"+
                            drinkListByType.get(inputNum2-1).name+" " + size + "  장바구니에 담겼습니다.");
                    //카트는 Map 으로, key 에는 drink 이름과 사이즈가 합쳐지고, value 에는 개수가 카운트 된다.
                    order.addToCart(drinkListByType.get(inputNum2-1), size);
                }

            }else if(inputNum==9){//장바구니 번호를 입력받을 경우
                System.out.println("-----------------------------------\n" +
                        "[ 장바구니 목록 ] ");

                //장바구니 목록 출력, 장바구니의 key 값에는 drink 의 이름과 사이즈가 띄어쓰기 로 합쳐져서 split(" ")을 해준다.
                for (String str : order.getCartList().keySet()) {
                    System.out.println(str.split(" ")[0]+"   "+
                            str.split(" ")[1]+"   "+
                            order.getCartList().get(str) + "개" +
                            "   "+order.getPrice(str)+"원");
                }

                System.out.print("""
                        입니다.
                        
                        결제하시려면 숫자 1을, 더 주문하시기를 원하시면 아무거나 입력하세요.
                        
                        숫자를 입력하세요:""");

                if(sc.nextInt()==1){//결제 번호 입력
                    System.out.println("-----------------------------------\n고객님, 주문하신");
                    //장바구니 목록 출력
                    for (String str : order.getCartList().keySet()) {
                        System.out.println(str.split(" ")[0]+"   "+
                                str.split(" ")[1]+"   "+
                                order.getCartList().get(str) + "개" +
                                "   "+order.getPrice(str)+"원");
                    }

                    //order.getTheBill() 은 카트 리스트의 Key 값 안에 있는 drink 이름을 drink 리스트에서 찾아서 가격을 사이즈를 고려해서 조회해준다.
                    System.out.print("이 나왔습니다.\n금액은 총 " + order.getTheBill() + "원 입니다.\n" +
                            "계산하시겠습니까?\n\n계산을 원하시면 숫자 '1'을\n더 주문하시기를 원하시면 아무거나 입력하세요\n" +
                            "\n숫자를 입력하세요: ");

                    //종료를 원할경우 hasFinishedOrder 값을 true 로 바꿔준다.
                    if(sc.nextInt()==1){
                        hasFinishedOrder = true;
                    }

                }
            }else if(inputNum==0){
                System.out.print("""
                        종료를 원하시면 숫자 '1'을
                        더 주문하시기를 원하시면 아무거나 입력하세요

                        숫자를 입력하세요:""");

                if(sc.nextInt()==1){
                    hasFinishedOrder = true;
                }

            }else{ // 선택지 이외의 숫자를 입력할 경우
                System.out.println("\n숫자를 정확히 입력하세요!!!");
            }
        }
        System.out.println("이용해주셔서 감사합니다.");
    }
}