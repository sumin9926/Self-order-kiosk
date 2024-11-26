package challenge_Lv1;

import java.util.*;

/**
 * 키오스크 실행 및 사용자 입력 처리를 담당하는 클래스<br>
 * menus 정보는 Main으로부터 전달받음
 */
public class Kiosk {
    private Map<String, ShoppingCart> shoppingCarts=new HashMap<>(); //선택한 메뉴를 담을 장바구니 컬렉션
    private List<Menu> menus=new ArrayList<>(); //Menu 객체를 저장하는 List

    void addMenu(Menu...menuArr){ //Menu 객체를 List에 저장하는 메서드
        this.menus.addAll(Arrays.asList(menuArr));
    }

    /*키오스크 프로그램 실행 메서드*/
    void start(){
        int command = Integer.MAX_VALUE; //사용자로부터 입력받은 숫자를 저장하는 변수
        do {
            /*1. 카테고리 선택 단계*/
            System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            showCategory();//카테고리 목록 표시
            if(!shoppingCarts.isEmpty()) showOrderMenu(); //장바구니에 메뉴가 담겨있으면 ORDER MENU도 같이 출력

            command = scanNumber(menus);//사용자로부터 카테고리 번호 입력 받기,(유효하지 않은 숫자 예외 처리)
            if (command == 0) { //입력된 숫자가 0일경우 키오스크 종료
                System.out.println("키오스크를 종료합니다.");
                return;
            } else showMenuItems(command); //입력된 카테고리 번호에 맞는 음식 메뉴 리스트 출력

            /*2. 음식 메뉴 선택 단계*/
            List<MenuItem> menuItemsList=menus.get(command-1).getMenuItems();// 입력된 숫자에 맞는 카테고리의 음식 메뉴 List
            command = scanNumber(menuItemsList); //사용자로부터 선택하려는 메뉴 번호 입력 받기,(유효하지 않은 숫자 예외 처리)
            MenuItem menuItem=menuItemsList.get(command-1); // 입력된 숫자에 맞는 음식 메뉴 하나
            if (command != 0) {//선택된 메뉴 정보 출력
                System.out.println("선택한 메뉴: "+menuItem.getFoodName()+"   | W "
                        +menuItem.getPrize()+" | "+menuItem.getDescription()+"\n");
                /*3. 장바구니에 음식 담기*/
                addToCart(menuItem);
            }
            //음식 메뉴 선택 단계에서 0 입력시 카테고리 선택 단계로 되돌아감
        }while (true);
    }

    /*카테고리 목록을 출력하는 메서드*/
    private void showCategory(){
        System.out.println("\n"+"[ MAIN MENU ]");
        for(int i=0; i< menus.size(); i++){
            System.out.println((i+1)+". "+menus.get(i).getCategory()); //카테고리 이름 출력
        }
        System.out.println("0. 종료      | 종료");
    }

    /*주문 목록을 출력하는 메서드*/
    private void showOrderMenu(){
        System.out.println("\n"+"[ ORDER MENU ]");
        System.out.println((menus.size()+1)+". Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println((menus.size()+2)+". Cancel       | 진행중인 주문을 취소합니다.");
    }

    /*카테고리 번호에 맞는 음식 메뉴 리스트를 출력하는 메서드*/
    private void showMenuItems(int categoryNum){
        List<MenuItem> menuItemsList=menus.get(categoryNum-1).getMenuItems(); // 입력된 숫자에 맞는 음식 메뉴 List를 불러와 저장

        System.out.println("\n"+"[ "+menus.get(categoryNum-1).getCategory()+" MENU ]"); // 선택된 카테고리 이름 출력
        for(int i=0; i<menuItemsList.size(); i++){ //음식 메뉴 리스트 출력
            System.out.println((i+1)+". "+menuItemsList.get(i).getFoodName()+"   | W "+menuItemsList.get(i).getPrize()+" | "+menuItemsList.get(i).getDescription());
        }
        System.out.println("0. 뒤로가기");
    }

    /*사용자로부터 숫자를 입력 받아 유효성을 검증하는 메서드*/
    private <T extends Collection<?>> int scanNumber(T t){ // 카테고리 혹은 음식 메뉴 리스트를 인자로 받음
        Scanner sc=new Scanner(System.in);
        do{
            int num=sc.nextInt(); //숫자 입력
            if((shoppingCarts.isEmpty()&&num>t.size()) || (!shoppingCarts.isEmpty()&&num>t.size()+2)){ //입력받은 숫자가 유효하지 않을경우 예외 메시지 출력
                System.out.println("메뉴판에 없는 번호입니다.");
            } else return num; //유효한 숫자일 경우 반복 종료 및 숫자 반환
        }while(true);
    }

    /*선택된 메뉴를 장바구니에 추가하는 메서드*/
    private void addToCart(MenuItem item){
        System.out.println("\""+item.getFoodName()+"   | W "+item.getPrize()+" | "+item.getDescription()+"\"");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        Scanner sc=new Scanner(System.in);
        int num=Integer.MAX_VALUE; //입력받은 숫자를 저장할 변수
        do{
            num=sc.nextInt(); // 숫자 입력 받기
            if(num==1 || num==2) break; // 입력된 숫자가 유효할 경우 반복문 종료
            else System.out.println("유효하지 않은 요청입니다."); //유효하지 않은 숫자 입력시 메세지 출력
        }while(true);

        if(num==1){ //1 입력 받은 경우 장바구니에 메뉴 저장하기
            if(shoppingCarts.containsKey(item.getFoodName())){ //저장하려는 메뉴가 장바구니에 이미 담겨있는 경우
                ShoppingCart existItem=shoppingCarts.get(item.getFoodName());
                existItem.setQuantity(existItem.getQuantity()+1); // 메뉴 개수 1개 추가
            }else{ //장바구니에 저장하려는 메뉴가 없는 경우
                shoppingCarts.put(item.getFoodName(),new ShoppingCart(item.getFoodName(), item.getPrize(), 1));
            }

            System.out.println(item.getFoodName()+" 이 장바구니에 추가되었습니다."); //저장 완료 메시지 출력
        }
    }
}
