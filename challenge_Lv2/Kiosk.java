package challenge_Lv2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 키오스크 실행 및 사용자 입력 처리를 담당하는 클래스<br>
 * menus 정보는 Main으로부터 전달받음
 */
public class Kiosk {
    Scanner sc=new Scanner(System.in);
    private Map<String, ShoppingCart> shoppingCarts=new HashMap<>(); //선택한 메뉴를 담을 장바구니 컬렉션(Key=음식 이름)
    private static List<Menu> menus=new ArrayList<>(); //Menu 객체를 저장하는 List

    void addMenu(Menu...menuArr){ //Menu 객체를 List에 저장하는 메서드
        this.menus.addAll(Arrays.asList(menuArr));
    }

    /*키오스크 프로그램 실행 메서드*/
    void start(){
        do {
            int command = Integer.MAX_VALUE; //사용자로부터 입력받은 숫자를 저장하는 변수
            /*1. 카테고리 선택 단계*/
            System.out.println("=======================================");
            System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            showCategory();//카테고리 목록 표시

            if(!shoppingCarts.isEmpty()) showOrderMenu(); //장바구니에 메뉴가 담겨있으면 ORDER MENU도 같이 출력
            command = scanNumber(menus);//사용자로부터 카테고리 번호 입력 받기,(유효하지 않은 숫자 예외 처리)
            if (command == 0) { //입력된 숫자가 0일경우 키오스크 종료
                System.out.println("키오스크를 종료합니다.");
                return;
            }
            else if(command<=menus.size()) showMenuItems(command); //입력된 카테고리 번호에 맞는 음식 메뉴 리스트 출력
            else if(command==menus.size()+1) {
                orderItems(); //장바구니 확인 및 주문 진행
                continue; //메뉴판으로 돌아감
            }
            else{
                shoppingCarts.clear(); //진행중인 주문 취소
                System.out.println("주문 취소. 장바구니를 초기화합니다.\n");
                continue;
            }

            /*2. 음식 메뉴 선택 단계*/
            List<MenuItem> menuItemsList=menus.get(command-1).getMenuItems();// 입력된 숫자에 맞는 카테고리의 음식 메뉴 List
            do {
                command = sc.nextInt();//사용자로부터 선택하려는 메뉴 번호 입력 받기
                if(command>0 && command<=menuItemsList.size()) break; // 유효한 숫자 입력시 반복문 종료
                else System.out.println("메뉴판에 없는 번호입니다."); // 유효하지 않은 숫자 입력시 예외 메시지 출력
            }while(true);

            if (command != 0) {//선택된 메뉴 정보 출력
                MenuItem menuItem=menuItemsList.get(command-1); // 입력된 숫자에 맞는 음식 메뉴 하나
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
    private int scanNumber(List<Menu> t){ // 카테고리 리스트를 인자로 받음
        do{
            int num=sc.nextInt(); //숫자 입력
            if(num<0||(shoppingCarts.isEmpty()&&num>t.size()) || (!shoppingCarts.isEmpty()&&num>t.size()+2)){ //입력받은 숫자가 유효하지 않을경우 예외 메시지 출력
                System.out.println("메뉴판에 없는 번호입니다.");
            } else return num; //유효한 숫자일 경우 반복 종료 및 숫자 반환
        }while(true);
    }

    /*선택된 메뉴를 장바구니에 추가하는 메서드*/
    private void addToCart(MenuItem item){
        System.out.println("\""+item.getFoodName()+"   | W "+item.getPrize()+" | "+item.getDescription()+"\"");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
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

    /*장바구니 메뉴 주문을 진행하는 메서드*/
    private void orderItems(){
        Double totalPrize=0.0;
        /*1. 장바구니에 담긴 메뉴 출력*/
        System.out.println("[ Orders ]");
        for(ShoppingCart cartItem: shoppingCarts.values()){ //장바구니에 담기 모든 메뉴 출력
            System.out.println(cartItem.getItemName()+" | W "
                    +cartItem.getPrize()+" | 총 수량: "+cartItem.getQuantity());
            totalPrize+=cartItem.getPrize()*cartItem.getQuantity(); //모든 메뉴의 가격 합 계산
        }
        /*2. 장바구니 메뉴 가격 합계 출력*/
        System.out.println("\n[ Total ]");
        System.out.println("W "+String.format("%.1f",totalPrize));

        /*3. 주문 혹은 보류 단계*/
        System.out.println("\n1. 주문      2. 메뉴판      3. 메뉴 수정");
        int num; //입력받은 숫자를 저장할 변수
        do {
            num = sc.nextInt();//사용자로부터 번호 입력 받기
            if(num==1 || num==2 || num==3) break; // 유효한 숫자 입력시 반복문 종료(1. 주문, 2. 메뉴판)
            else System.out.println("메뉴판에 없는 번호입니다."); // 유효하지 않은 숫자 입력시 예외 메시지 출력
        }while(true);

        if(num==1) { //1 입력시 주문 진행
            /*4. 할인 유형 선택 및 할인율 적용*/
            System.out.println("할인 정보를 입력해주세요.");
            DiscountRate[] discountRatesArr=DiscountRate.values(); //할인 대상 배열
            for(int i=0; i< discountRatesArr.length; i++){ //할인정보 출력
                System.out.println((i+1)+". "+discountRatesArr[i].name()+" : "+discountRatesArr[i].getDiscountRate()+"%");
            }
            do {
                num = sc.nextInt();//사용자로부터 할인 유형 번호 입력 받기
                if(num>0 && num<=discountRatesArr.length) break; // 유효한 숫자 입력시 반복문 종료
                else System.out.println("유효하지 않은 번호입니다."); // 유효하지 않은 숫자 입력시 예외 메시지 출력
            }while(true);
            totalPrize=discountRatesArr[num-1].discountPrize(totalPrize); //총 가격에 선택된 할인 유형에 맞는 할인율 적용
            System.out.println("주문이 완료되었습니다. 금액은 W "+String.format("%.1f",totalPrize)+" 입니다.\n"); //소수점 1번째 자리까지 출력
            shoppingCarts.clear(); //장바구니 초기화
        }
        else if(num==3) { //장바구니에서 선택한 메뉴 삭제 진행
            System.out.println("삭제할 메뉴의 이름을 입력해주세요.");
            String foodName; //삭제할 메뉴 이름 저장
            sc.nextLine(); //버퍼 비우기
            do {
                foodName = sc.nextLine(); //삭제할 메뉴 이름 입력받기
                if (shoppingCarts.containsKey(foodName)) break; //입력한 메뉴명이 장바구니에 존재할 경우 반복문 탈출
                else System.out.println("존재하지 않는 메뉴명입니다."); // 유효하지 않은 메뉴명 입력시 예외 메시지 출력
            } while (true);

            final String finalFoodName = foodName; //람다식 사용시 외부 변수는 'final' 혹은 '사실상 final'이어야한다.(변수캡쳐)
            Map<String, ShoppingCart> filteredCart = shoppingCarts.entrySet().stream()
                    .filter(stringShoppingCartEntry -> !stringShoppingCartEntry.getKey().equals(finalFoodName))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            shoppingCarts.clear();//기존 맵 비우기
            shoppingCarts.putAll(filteredCart);//필터링된 값으로 장바구니 새로 채우기
        }
    }
}

