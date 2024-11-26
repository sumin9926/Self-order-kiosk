package challenge_Lv1;

/**
 * 키오스크 프로그램의 시작 지점<br>
 * 음식 카테고리 생성 및 카테고리별 음식 아이템을 저장합니다.
 */
public class Main {
    public static void main(String[] args) {
        /*카테고리별로 Menu 객체 생성하면서 이름도 함께 설정*/
        Menu burgers=new Menu("BURGERS");
        Menu drinks=new Menu("DRINKS");
        Menu desserts=new Menu("DESSERTS");

        /*각 카테고리에 메뉴 아이템 저장*/
        //햄버거 카테고리
        burgers.addItems(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack ", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("CheeseBurger ", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem( "Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        //음료 카테고리
        drinks.addItems(new MenuItem("Fountain Soda", 3.6, "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플"),
                new MenuItem("Lemonade", 5.2, "매장에서 직접 만드는 상큼한 레몬에이드"),
                new MenuItem("Fifty/Fifty", 4.7, "레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 쉐이크쉑의 시그니처 음료"),
                new MenuItem("Abita Root Beer",5.0, "청량감 있는 독특한 미국식 무알콜 탄산음료"));

        //디저트 카테고리
        desserts.addItems(new MenuItem("Fries", 4.9, "바삭하고 담백한 크링클 컷 프라이"),
                new MenuItem("Hot dog", 5.1, "참나무 칩으로 훈연한 비프 소시지와 토종효모 포테이토 번을 사용한 핫 도그"),
                new MenuItem("Sichuan Crisp Fries", 6.5, "플레이버타운의 스파이스 시즈닝과 칠리 크리스프 오일, 고소한 치즈 소스가 토핑된 크링클 컷 프라이"),
                new MenuItem("Shakes", 6.8, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크(바닐라/초콜릿/스트로베리/블랙 & 화이트/솔티드 카라멜/피넛 버터/커피)"));


        Kiosk kiosk=new Kiosk(); //Kiosk 객체 생성
        kiosk.addMenu(burgers, drinks, desserts); //Kiosk에 Menu인스턴스를 넘겨줌

        kiosk.start(); //키오스크 프로그램 실행
    }
}
