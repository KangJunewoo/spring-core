package core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
컴포넌트 스캔 : 쫙 읽어서 쫙 끌어올리는거?

중요하진 않지만 앞에서 선언했던 컨피규레이션 깡그리 무시하기 위해서 excludeFilters를 넣었다.
@Component를 붙여서 스프링 빈으로 등록할거라는 선언을 할 수 있음.
그러면 의존관계 주입 어떻게 할거야? (Service에서 Repository 어떻게 꽂아줄거야)
여기서 나오는게 @Autowired임.

이전 AppConfig에선 @Bean으로 직접 설정정보를 작성했고, 의존관계를 직접 명시했음.
이를 오토와이어드로 알아서 주입해주는 걸로 바꿈.

@CompomentScan 붙이는 순간
@Component가 붙은 모든 클래스를 스프링 빈으로 등록함.
스프링 빈 기본 이름은 클래스명을 사용하되, 맨 앞글자만 소문자를 사용함.
그리고 생성자에 @Autowired를 붙이면, 스프링 컨테이너가 알아서 타입 같은 빈을 찾아 주입해줌.

basePackages, basePackageClasses 옵션으로 컴포넌트 스캔의 대상을 고를 수 있지만,
그냥 설정정보 클래스를 최상단에 두면 해결됨.

사실 애노테이션엔 상속관계가 없음.
@Component를 상속하는 @Service, @Repository 등이 있음. 이제 부가기능에 대해 알아볼까.
@Controller : 스프링 MVC 컨트롤러로 인식
@Repository : 스프링 데이터접근계층으로 인식, 데이터계층의 예외를 스프링 예외로 변환해줌.
@Configuration : 스프링 설정정보로 인식. 싱글톤 유지.
@Service : 추가처리 없음 ㅋ. 다만 비즈니스 계층인 것을 개발자들한테 나타내는 의의.
 */
@Configuration
@ComponentScan(
        basePackages = "core.member", // 컴포넌트 스캔의 대상
        basePackageClasses = AutoAppConfig.class, // 어디서 찾을건지
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
