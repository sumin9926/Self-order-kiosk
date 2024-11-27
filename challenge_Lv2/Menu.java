package challenge_Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 카테고리와 MenuItem으로 구성된 List(음식 메뉴 List)를 관리하는 클래스 입니다.
 */
public class Menu {

    private String category; //카테고리 이름 필드
    private List<MenuItem> menuItems;//음식 메뉴를 저장하는 List 필드

    /*생성자*/
    public Menu(String category){ //생성자를 선언과 동시에 카테고리 이름을 지정
        this.category=category;
        this.menuItems=new ArrayList<>(); //리스트 초기화
    }

    void addItems(MenuItem...menuItem){ //음식 메뉴를 List에 저장
        this.menuItems.addAll(Arrays.asList(menuItem));
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
