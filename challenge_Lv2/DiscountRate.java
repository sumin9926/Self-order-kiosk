package challenge_Lv2;

public enum DiscountRate {
    SOLDIER(5), //군인
    STUDENT(3), //학생
    GENERAL(0), //일반인
    VETERAN(10); //국가유공자

    private final int discountRate;//할인율

   DiscountRate(int discountRate){
        this.discountRate=discountRate;
    }

    public int getDiscountRate() { //할인율 반환 메서드
        return discountRate;
    }

    public Double discountPrize(Double totalPrize){ // 총 금액에 할인율을 적용한 결과를 출력하는 메서드
        return totalPrize-totalPrize*discountRate/100;
    }
}
