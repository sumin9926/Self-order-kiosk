package kiosk_Lv4;

/**
 * 개별 음식 항목을 관리하는 클래스입니다.
 */
public class MenuItem {
    String foodName; //음식 이름
    Double prize; //음식 가격
    String description; //음식 설명

    public MenuItem(String foodName, Double prize, String description) {
        this.foodName = foodName;
        this.prize = prize;
        this.description = description;
    }

}
