package kiosk_Lv3;

import java.util.List;
import java.util.Scanner;

/**
 * 키오스크 실행 및 사용자 입력 처리를 담당하는 클래스<br>
 * menuItems 정보는 Main으로부터 할당받음
 */
public class Kiosk {
    Scanner sc=new Scanner(System.in);
    private int command=Integer.MAX_VALUE; //사용자가 입력한 숫자를 저장하는 변수
    private List<MenuItem> menuItems; //menuItem을 관리하는 필드

    /*생성자*/
    public Kiosk(List<MenuItem> menuItems){ //Main으로 부터 menuItems를 할당 받는 생성자
        this.menuItems=menuItems;
    }

    /*키오스크 프로그램 실행 메서드*/
    void start(){
        do {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menuItems.size(); i++) { //list에 있는 메뉴 출력
                System.out.println((i+1) + ".  " + menuItems.get(i).foodName + "   | W "
                        + menuItems.get(i).prize + " | " + menuItems.get(i).description);
            }
            System.out.println("0. 종료      | 종료");

            command = sc.nextInt(); //사용자로부터 숫자 입력 받기
            if (command == 0) { // 입력된 숫자가 0이면 프로그램 종료
                System.out.println("키오스크를 종료합니다.");
            }else if(command>0 && command<=menuItems.size()){ // 유효한 숫자가 입력되면 이름, 가격, 설명 출력
                System.out.println("선택한 메뉴: " + menuItems.get(command-1).foodName + "   | W "
                        + menuItems.get(command-1).prize + " | " + menuItems.get(command-1).description+"\n");
            }else{
                System.out.println("메뉴판에 없는 번호입니다.\n"); //유효하지 않은 숫자 입력 시 오류 메시지 출력
            }
        }while (command!=0);
    }

}
