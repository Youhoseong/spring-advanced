# 스프링 고급 주제

# 템플릿 메소드 패턴
- 전체적인 코드 실행 흐름은 부모 클래스에 정의하고, 흐름 중 세부 구현은 부모 클래스를 상속한 자식 클래스에서 @Override하여 유연하게 변경한다.

- 자식 클래스에서는 부모 클래스의 기능을 사용하지 않는데 부모 클래스의 기능을 아는 것(상속)은 낭비이다.


# 전략 패턴 
- 전체적인 코드 실행 흐름을 한 클래스에 정의하고, 흐름 중 세부 구현은 전략 인터페이스를 사용한다. 전략 인터페이스를 구현하여 코드 실행 클래스에 주입함으로써 템플릿 메소드 패턴의 단점을 보완한 디자인 패턴

   ## 전략을 필드에 주입
   ## 전략을 콜백 형태로 사용
    
# 프록시 패턴 
- 클라이언트가 target 객체에 접근하기 직전, 부가 기능을 더하거나 접근 제어를 위해 사용한다.
- 부가 기능을 더하는 것은 프록시 패턴 중에서도 데코레이터, 접근 제어는 프록시라고 칭한다.
  
    ## 정적 프록시
     - 1은 일일히 클래스마다 프록시 객체를 만들어줘야 하므로 번거롭다.


   ## JDK 동적 프록시
   - 인터페이스-구체클래스 구조에서 사용할 수 있는 프록시
  - 2, 3은 프록시 객체를 일일히 만들지 않아도 되지만, 인터페이스-구체 / 구체 구조에 따라서 동일한 기능을 하는 Advice를 JDK, CGLIB에 각각 맞는 형태로 정의를 해줘야 하는 번거로움.



   ## CGLIB 동적 프록시
   - 구체클래스만 있을 때 사용할 수 있는 프록시

   ## 프록시 팩토리
   -  target 클래스가 인터페이스-구체클래스 구조일 때는 JDK  동적 프록시, 구체클래스만 있을 땐 CGLIB 프록시 사용(물론 기본 값이 이렇다는 것 - 변경 가능)
     

    - 4는 클래스 구조에 따라 Advice를 공통적으로 사용할 순 있으나, Configuration을 통해 프록시를 Bean으로 등록해줘야하는데, 이 과정이 만만치 않게 귀찮다.
        
   ## 빈 후 처리기
   - 스프링 빈에 등록되기 직전에 수행하는 일종의 interceptor와 비슷한 역할(interceptor는 아니다.)
   - 객체 바꿔치기도 가능함. 즉, 조건을 충족하면 target 대신 프록시를 빈으로 등록하는 로직 수행 가능.
   - 스프링 빈으로 등록된 모든 Advisor를 통해 PointCut에 기반한 프록시 객체를 빈 등록한다. (모든 클래스, 모든 메소드를 포인트 컷에 해당하는 지 검사하고, 메소드 하나라도 해당되면 프록시 객체 생성)
   - PointCut은, [프록시 등록 여부 판단 시 & 어드바이스 수행 여부 판단 시] 이렇게 두 번 사용된다.


  - 스프링은 자동 빈 후 처리기를 제공하므로, postprocessor를 임의로 구현하여 빈 등록해놓을 필요는 없다. 
