package challenge_Lv1;

/**
 * 장바구니 관련 클래스 입니다.
 */
public class ShoppingCart {
    private String itemName; //음식 이름
    private int quantity; //장바구니에 담긴 음식의 수량
    private Double prize; //음식 가격

    /*생성자*/
    public ShoppingCart(String itemName, Double prize, int quantity){
        this.itemName=itemName;

        if(prize>=0.0) this.prize=prize;
        else this.prize=0.0; //prize에는 양수만 저장할 수 있음

        if(quantity>=0) this.quantity=quantity;
        else this.quantity=0; //quantity에 음수는 저장할 수 없음. 최솟값은 0.
    }

    /*메서드*/
    public void setQuantity(int quantity) {
        if(quantity>=0) this.quantity = quantity;
        else this.quantity=0; //quantity에 음수는 저장할 수 없음. 최솟값은 0.
    }

    public int getQuantity() { //음식 수량 반환
        return quantity;
    }

    public String getItemName() { //음식 이름 반환
        return itemName;
    }

    public Double getPrize() { //음식 가격 반환
        return prize;
    }
}
