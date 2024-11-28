#  🍔JAVA-Based Kiosk
## ❔프로젝트 정보
- 클래스 개념 이해와 객체 지향 설계 연습을 위해 진행한 '키오스크 만들기' 과제입니다.
- **개발기간**: 24.11.22~24.11.28

## 🔧사용 기술
**Environment**

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white">

**Development**

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"> 

## 🗂️ 디렉토리별 주요 기능 안내

- 각 상위 단계는 하위 단계의 기능을 포함하고 있습니다.
### Lv1

- 햄버거 메뉴판을 출력합니다
- 사용자로부터 메뉴 번호를 입력받습니다.
- 입력 받은 숫자에 맞는 메뉴를 출력합니다. (0 입력시 종료)

### Lv2

- 개별 음식 항목을 관리하는 MenuItem 클래스를 구현하였습니다.
- List를 선언하여 MenuItem 객체(햄버거 메뉴)를 저장합니다.
- List에 저장된 메뉴를 출력합니다.

### Lv3

- 전체 순서 제어를 관리하는 Kiosk 클래스를 구현하였습니다.
- 사용자 입력과 메뉴판 출력은 모두 Kiosk 클래스가 담당합니다.
- 메뉴판에 없는 번호 입력시 오류 메세지를 출력합니다. <br>
  (정수만 입력만 가능)

### Lv4

- MenuItem 객체들을 관리하는 Menu 클래스를 구현하였습니다.
- Menu 클래스는 '카테고리'의 역할을 합니다.
- Menu 클래스 내부에 메뉴 카테고리 이름을 반환하는 메서드를 구현하였습니다.

### Lv5

- MenuItem, Menu 그리고 Kiosk 클래스의 필드에 캡슐화를 적용하였습니다.
- 각 클래스의 필드 접근을 위해 필요한 최소한의 Getter 메서드를 구현하였습니다.<br>
  (Setter 메서드는 해당 단계에서 필요하지 않아 구현하지 않았음)

### Challenge Lv1

- 장바구니 및 결제 기능이 추가되었습니다.
- 장바구니에 물건이 있는 경우, 'Orders', 'Cancel' 선택지를 포함하고있는 [ ORDER MENU ] 가 추가로 출력됩니다.
- 'Orders' 선택시 장바구니를 확인하고 '주문' 혹은 주문을 '보류'하고 메인 화면으로 돌아갑니다.
- 'Cancel' 선택시 장바구니를 비웁니다.
- [장바구니]<br>
  + 장바구니는 ShoppingCart 클래스를 통해 구현되었습니다.
  + ShoppingCart 클래스에 setQuantity() 메서드를 추가하였습니다.<br>
    수량과 가격 모두 음수는 저장되지 못하도록 제약을 추가하였습니다.
  + 사용자에게 선택한 메뉴를 장바구니에 추가할지 물어보고, 입력값에 따라 장바구니에 추가 혹은 취소처리됩니다.
  + 메뉴 항목을 빠르게 조회하고 수정하도록하기 위해 HashMap을 사용합니다.
  + 장바구니에 중복된 메뉴가 있을 경우 새로운 객체가 추가되지 않고, '수량(quantity 필드)'이 증가되도록 하였습니다.
- [결제]<br>
  + 결제 전, 장바구니에 담긴 모든 메뉴와 총 금액을 출력합니다.<br>
  + '주문하기'를 선택하면 장바구니가 초기화됩니다.

### Challenge Lv2

- '할인'기능과 '장바구니 수정' 기능이 새롭게 추가되었습니다.
- [할인]<br>
  - Enum을 활용해 사용자 유형별 할인율을 관리합니다. (DiscountRate 클래스)
  - 주문 시, 사용자 유형에 맞는 할인율을 적용합니다.
  - 할인율 적용 후 금액은 소수점 1의 자리까지 출력되도록 하였습니다.
- [장바구니 수정]
  + 장바구니에서 특정 메뉴를 뺄 수 있습니다. (stream.filter()를 사용하였습니다)

## ➕부가 설명

- 사용자 입력시 정수만 입력받을 수 있습니다. 실수와 문자열에 대한 예외처리가 되어있지 않습니다.