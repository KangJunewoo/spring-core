package core.singleton;

public class StatefulService {
    private int price; // 상태유지필드.. 절대 싱글톤이어선 안되지만...

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    /*
    제대로 하려면 아래와 같이 price를 저장할 생각을 하지 말아야 한다.
    public int getPrice(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price
    }
     */

}
