package com.medium.junewookang.core.singleton;

import org.junit.jupiter.api.Test;

public class StatefulService {
    private int price; // 상태 유지

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제.
        return price; // 이거 추가해주면 됨.
    }

    public int getPrice(){
        return price;
    }
}
