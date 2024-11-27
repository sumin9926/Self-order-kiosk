package kiosk_Lv2;

import java.util.List;
import java.util.Scanner;

/**
 * 키오스크 메뉴 관리 및 사용자 입력을 처리하는 클래스
 */
public class Main {
    public static void main(String[] args) {
        //음식 메뉴를 담는 list 선언, 초기화
        List<MenuItem> menuList= List.of(
            new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
            new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
            new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
            new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        );

        Scanner sc=new Scanner(System.in);
        int command=Integer.MAX_VALUE;

        /*메뉴판 출력 및 입력된 숫자에 따른 로직 실행*/
        do {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menuList.size(); i++) { //list에 있는 메뉴 출력
                System.out.println((i+1) + ".  " + menuList.get(i).foodName + "   | W "
                        + menuList.get(i).prize + " | " + menuList.get(i).description);
            }
            System.out.println("0. 종료      | 종료");

            command = sc.nextInt(); //사용자로부터 숫자 입력 받기
            if (command == 0) { // 입력된 숫자가 0이면 프로그램 종료
                System.out.println("키오스크를 종료합니다.");
            }else if(command>0 && command<=menuList.size()){ // 메뉴에 있는 숫자가 입력되면 이름, 가격, 설명 출력
                System.out.println("선택한 메뉴: " + menuList.get(command-1).foodName + "   | W "
                        + menuList.get(command-1).prize + " | " + menuList.get(command-1).description+"\n");
            }else{
                System.out.println("메뉴판에 없는 번호입니다."+"\n");
            }
        }while (command!=0);


    }
}
