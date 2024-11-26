package kiosk_Lv1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int command=Integer.MAX_VALUE; //명령어
        String[] menuBoard={"1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거",
                "2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
                "3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",
                "4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거"};

        /*메뉴 리스트 출력 및 입력 숫자에 맞는 동작 실행*/
        do {
            /*매뉴 리스트 출력*/
            System.out.println("[ SHAKESHACK MENU ]");
            for(String str:menuBoard){
                System.out.println(str);
            }
            System.out.println("0. 종료      | 종료");

            command=sc.nextInt(); //명령어 숫자 입력
            switch (command){ //입력된 숫자에 맞는 동작 실행(메뉴 출력, 종료, 안내)
                case 1 -> System.out.println(menuBoard[0]+"\n");
                case 2 -> System.out.println(menuBoard[1]+"\n");
                case 3 -> System.out.println(menuBoard[2]+"\n");
                case 4 -> System.out.println(menuBoard[3]+"\n");
                case 0 -> System.out.println("프로그램을 종료합니다."); // 0 입력시 키오스크 종료
                default -> System.out.println("메뉴판에 없는 번호입니다."+"\n"); //메뉴에 없는 번호 입력시 안내 멘트 출력
            }
        }while (command!=0);
    }
}
