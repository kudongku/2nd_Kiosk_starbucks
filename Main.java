import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        DrinkType coffeeVar = new DrinkType("커피베리에이션", "커피를 활용한 음료입니다.");
        DrinkType teaVar = new DrinkType("티베리에이션", "티를 활용한 음료입니다.");
        DrinkType blended = new DrinkType("블렌디드", "시원하고 단맛을 자랑하는 음료입니다.");

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

        boolean hasFinishedOrder = false;
        while(!hasFinishedOrder){
            System.out.println("""
                    -----------------------------------
                    아래 메뉴판을 보시고 카테고리를 골라 입력해주세요.

                    [ 음료 카테고리 ]""");

            for (int i = 0; i < order.getDrinkTypeList().size(); i++) {
                System.out.println(i+1+". "+(order.getDrinkTypeList().get(i).name)+
                        "   "+order.getDrinkTypeList().get(i).explanation);
            }

            System.out.print("""
                
                [ 다른 선택지 ]
                7. 계산하기
                8. 카테고리로 가기
                9. 장바구니 가기
                0. 주문을 종료하기
                
                숫자를 입력하세요 :""");

            Scanner sc = new Scanner(System.in);
            int inputNum = sc.nextInt();

            if(inputNum<=order.getDrinkTypeList().size()&&inputNum!=0){
                System.out.println("-----------------------------------\n" +
                        "아래 음료메뉴판을 보시고 음료를 골라 입력해주세요.\n\n[ "+
                        order.getDrinkTypeList().get(inputNum-1).name +" 카테고리 ]");

                ArrayList<Drink> drinkListByType = order.getDrinkList(inputNum);

                for (int i=0; i<drinkListByType.size(); i++) {
                    System.out.println(i+1 + ". " +drinkListByType.get(i).name+
                            "   "+drinkListByType.get(i).price+"   "+drinkListByType.get(i).explanation);
                }

                System.out.print("""
                
                음료 카테고리로 나가고 싶으시면 음료 숫자 외에 다른 숫자를 입력해주세요
                
                숫자입력하기:""");

                Scanner sc2 = new Scanner(System.in);
                int inputNum2 = sc2.nextInt();

                if(inputNum2<=order.getDrinkTypeList().size()&&inputNum2!=0){
                    System.out.println("-----------------------------------\n"+
                            drinkListByType.get(inputNum2-1).name +
                            "\n\n사이즈를 선택해주세요." +
                            " \n1.톨 (기본료 그대로)\n2.그란데 (500원 추가)\n3.벤티 (1000원 추가)" +
                            "\n\n주문을 취소하시려면 1,2,3 외에 다른 숫자를 입력하세요 (카테고리로 이동합니다.)\n\n숫자를 입력하세요 :");

                    Scanner sc3 = new Scanner(System.in);
                    int inputNum3 = sc3.nextInt();

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
                    order.addToCart(drinkListByType.get(inputNum2-1), size);
                }

            }else if(inputNum==7){
                System.out.println("-----------------------------------\n고객님, 주문하신");
                //장바구니 목록 출력
                for (String str : order.getCartList().keySet()) {
                    System.out.println(str.split(" ")[0]+"   "+
                            str.split(" ")[1]+"   "+
                            order.getCartList().get(str) + "개" +
                            "   "+order.getPrice(str)+"원");
                }
                System.out.print("이 나왔습니다.\n금액은 총 " + order.getTheBill() + "원 입니다.\n" +
                        "계산하시겠습니까?\n\n계산을 원하시면 숫자 '1'을\n더 주문하시기를 원하시면 아무거나 입력하세요\n" +
                        "\n숫자를 입력하세요:");

                Scanner sc5 = new Scanner(System.in);
                if(sc5.nextInt()==1){
                    hasFinishedOrder = true;
                }
            }else if(inputNum==8){
                continue;
            }else if(inputNum==9){
                System.out.println("-----------------------------------\n" +
                        "[ 장바구니 목록 ] ");

                for (String str : order.getCartList().keySet()) {
                    System.out.println(str.split(" ")[0]+"   "+
                            str.split(" ")[1]+"   "+
                            order.getCartList().get(str) + "개" +
                            "   "+order.getPrice(str)+"원");
                }
                System.out.print("""
                        입니다.
                        
                        결제하시려면 숫자 1을, 더 주문하시기를 원하시면 아무거나 입력하세요.
                        
                        숫자를 입력하세요""");
                Scanner sc6 = new Scanner(System.in);
                if(sc6.nextInt()==1){
                    System.out.println("-----------------------------------\n고객님, 주문하신");
                    //장바구니 목록 출력
                    for (String str : order.getCartList().keySet()) {
                        System.out.println(str.split(" ")[0]+"   "+
                                str.split(" ")[1]+"   "+
                                order.getCartList().get(str) + "개" +
                                "   "+order.getPrice(str)+"원");
                    }
                    System.out.print("이 나왔습니다.\n금액은 총 " + order.getTheBill() + "원 입니다.\n" +
                            "계산하시겠습니까?\n\n계산을 원하시면 숫자 '1'을\n더 주문하시기를 원하시면 아무거나 입력하세요\n" +
                            "\n숫자를 입력하세요:");

                    Scanner sc7 = new Scanner(System.in);
                    if(sc7.nextInt()==1){
                        hasFinishedOrder = true;
                    }
                }
            }else if(inputNum==0){
                System.out.print("""
                        종료를 원하시면 숫자 '1'을
                        더 주문하시기를 원하시면 아무거나 입력하세요

                        숫자를 입력하세요:""");

                Scanner sc8 = new Scanner(System.in);
                if(sc8.nextInt()==1){
                    hasFinishedOrder = true;
                }
            }else{
//                throw new
            }
        }
        System.out.println("이용해주셔서 감사합니다.");
    }
}