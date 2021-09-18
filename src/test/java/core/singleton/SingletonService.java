package core.singleton;

/*
테케 아님.
싱글톤 패턴을 구현하는 방법은 정말 많은데,
여기선 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 택함.

BUT 싱글톤은 수많은 문제점을 가지고 있음.
1. static부터 시작하는 아래 코드르 다 넣어야함.
2. DIP, OCP 위반함 (구체클래스에 의존함.)
3. 유연한 테스트가 어려움
4. 자식클래스를 만들기 어려움 (private 생성자)
5. 뭐.. 이러한 이유들로 결국엔 유연성이 떨어짐. 안티패턴으로 불려.

갓갓 스프링이 이 문제점을 모두 해결해줌.
스프링 빈이 알아서 싱글톤으로 관리해주거든.

하지만 아직 주의점은 남아있음.
여러군데서 같은 객체 인스턴스를 공유하기 때문에, 무상태로 해야 함.
상태값 적용했다가 진짜 큰일나는 경우가 있음..
 */
public class SingletonService {
    // 자바기본 : 이렇게 하면 스태틱 영역에 인스턴스가 딱 하나만 올라가줌.
    private static final SingletonService instance = new SingletonService();

    // SingletonService는 딱 이걸로만 접근할 수 있음.
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 프라이빗으로 선언해주는 전무후무한 경우 -> 오히려 싱글톤에 적합. (외부에서 new키워드가 막힘)
    private SingletonService() {
    }


}
