package com.medium.junewookang.core.singleton;

public class SingletonService {

    // TODO : 자바기본, static에 대해 공부해보자.
    private static final SingletonService instance = new SingletonService(); // 자기 자신을 내부의 private으로 가져서, 딱 하나만하나만 갖고 있음.

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
