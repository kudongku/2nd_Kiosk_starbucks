public class Main {
    public static void main(String[] args) {

        //order 객체 생성
        Order order = new Order();
        Category coffeeVar = new Category("커피베리에이션", "커피를 활용한 음료입니다.");
        Category teaVar = new Category("티베리에이션", "티를 활용한 음료입니다.");
        Category blended = new Category("블렌디드", "시원하고 단맛을 자랑하는 음료입니다.");

        //order 안에 drinkArr 에 drink 들을 담으면서 drink 인스턴스들을 생성함.
        order.addDrinks(new Drink("아메리카노", "에스프레소에 물을 탄 커피", 4500, coffeeVar),
                new Drink("허니블랙티", "꿀을 탄 블랙티", 4000, teaVar),
                new Drink("바나나요거트", "바나나를 넣은 요거트", 5000, blended),
                new Drink("카페라테", "에스프레소에 우유를 탄 커피", 4500, coffeeVar),
                new Drink("콜드브류", "저온으로 오랫동안 추출한 커피", 5000, coffeeVar),
                new Drink("유자민트티", "유자청을 첨가한 민트티", 4500, teaVar),
                new Drink("얼그레이티", "에스프레소에 우유를 탄 커피", 3500, teaVar),
                new Drink("자바칩프라푸치노", "자바칩을 넣은 프라푸치노", 6000, blended),
                new Drink("망고스무디", "망고를 갈아넣은 스무디", 5500, blended));

        order.printLine(); // ---------
        System.out.println("스타벅스에 오신걸 환영합니다.");

        //메인 주문표 생성
        order.printCategory();
    }
}
