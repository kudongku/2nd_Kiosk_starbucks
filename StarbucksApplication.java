import Menu.Drink;
import java.util.ArrayList;

public class StarbucksApplication {
    private static final Service service = new Service();
    public static void main(String[] args) {
        //service 에서 왠만하면 진행이 되고, 어플리케이션에는 대략적인 flow 만 보여준다. + service 와 왔다갔다
        service.on();

        while(true){
            //getDrinkTypeList()는 drinkType 리스트를 리턴해준다.
            int max = service.printCategory();

            int inputNum = service.getNumber();

            if(inputNum==0){
                service.printEnd();
            }
            else if(inputNum<=max){ //음료카테고리를 입력시
                ArrayList<Drink> drinkListByType = service.printDrinks(inputNum);
                int inputNum2 = service.getNumber();

                //drink 번호를 입력했을 경우
                if(inputNum2<=drinkListByType.size()&&inputNum2>0){
                    service.printSelectingSize(drinkListByType, inputNum2);
                }

            }
            else if(inputNum==8){//총 구매내역 조회하기
                service.printTotalPayment();
            }
            else if(inputNum==9){//장바구니 번호를 입력받을 경우
                service.printCartList();

                if(service.getNumber()==1){//결제 번호 입력
                    service.emptyOutCart();
                    service.printEnd();
                }

            }
            else{ // 선택지 이외의 숫자를 입력할 경우
                System.out.println("\n숫자를 정확히 입력하세요!!!");
            }

        }
    }
}