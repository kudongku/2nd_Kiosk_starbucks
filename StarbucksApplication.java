public class StarbucksApplication {

    //서비스 인스턴스 생성
    private static final Service service = new Service();
    public static void main(String[] args) {
        //service 에서 왠만하면 진행이 되고, 어플리케이션에는 대략적인 flow 만 보여준다. + service 와 왔다갔다
        service.startOrder();

        while(true){
            //getDrinkTypeList()는 drinkType 리스트를 리턴해준다.
            int selectedCategoryNum = service.printCategory();

            if(selectedCategoryNum==0){
                service.finishOrder();
            }
            else if(selectedCategoryNum==9){//장바구니 번호를 입력받을 경우

                if(service.printCart()==1){//결제 번호 입력
                    service.emptyOutCart();
                    service.finishOrder();
                }

            }
            else if(selectedCategoryNum==8){//총 구매내역 조회하기
                service.printTotalPayment();
            }
            else{ //음료카테고리를 입력시
                int selectedDrinkNum = service.printDrinks(selectedCategoryNum);

                //drink 번호를 입력했을 경우
                if(selectedDrinkNum!=0){
                    service.printSize(selectedDrinkNum, selectedCategoryNum);
                }

            }

        }
    }
}