#### Functional Interface
함수형 인터페이스를 알아보자.  
일단 인터페이스이다. 하나 만들어보자.  

~~~java
public interface DoSomething {
}
~~~

메서드도 하나 추가한다.

~~~java
public interface DoSomething {
    int doIt(int a);
}
~~~

여기서 메서드를 하나 더 추가하면?  
함수형 인터페이스가 아니다.  
함수형 인터페이스는 하나의 메서드를 가지는 인터페이스이다.  

그런데 자바8버전부터는 인터페이스에 static 메서드 혹은 default 메서드도 가능하게 되었다.  

~~~java
public interface DoSomething {
    int doIt(int a);
    
    static void doStatic() {
        System.out.println("do static");
    }
    
    default void doDefault() {
        System.out.println("do default");    
    }
}
~~~

이래도 함수형 인터페이스인가?  
그렇다.

이러한 함수형 인터페이스는 특별관리를 위해 @FunctionalInterface 어노테이션을 붙일 수 있다.  
특볋한 기능은 없다. 다만, 어노테이션을 통해 함수형 인터페이스의 정의를 위반하게 된다면 컴파일 오류가 발생할 것이다.

~~~java
@FunctionalInterface
public interface DoSomething {
    int doIt(int a);
}
~~~

사용해보자.

~~~java
@Test
void anonymousInnerClass() {
    DoSomething doSomething = new DoSomething() {
        @Override
        public int doIt(int a) {
            return a + 1;
        }
    };
    
    assertEquals(2, doSomething.doIt(1));
    assertEquals(2, doSomething.doIt(1));
    assertEquals(2, doSomething.doIt(1));
}

@Test
void anonymousInnerClassByLambda() {
    DoSomething doSomething = a -> a + 1;

    assertEquals(2, doSomething.doIt(1));
    assertEquals(2, doSomething.doIt(1));
    assertEquals(2, doSomething.doIt(1));
}
~~~

위의 코드는 동일한 코드이다. 아래의 테스트코드는 람다표현식으로 변경한것이다.  
만약 함수형인터페이스에 동일한 input, output을 가지는 (int 하나를 받아서 int로 리턴하는) 함수가 있었다면 당연히 이렇게 사용은 못했을 것이다.  

#### Functional Programming
위의 테스트코드에서 하나 더 배우고 넘어갈 부분은 doSomething 함수형 인터페이스를 같은값을 입력하여 여러번 수행해도 같은 결과값이 나왔다는 부분이다.  
함수형 프로그래밍은 같은 값을 입력하면 같은 값을 리턴하는것을 보장해주기를 권고하고 있다.  

아래와 같이 외부의 변수를 참고하거나 한다면, 함수형 프로그래밍의 권고사항과 어긋나기 때문에 (틀린것은 아니다.) 충분히 고려하고 사용하면 된다.

~~~java
class Hello {
    int a;

    int plus(int number) {
        a = a + number;
        return a;
    }
}
@Test
void violateFunctionalProgramming() {
    Hello hello = new Hello();

    DoSomething doSomething = hello::plus;

    assertEquals(1, doSomething.doIt(1));
    assertEquals(2, doSomething.doIt(1));
    assertEquals(3, doSomething.doIt(1));
}
~~~

위와 같은 예시에서는 같은 1을 넣어도 리턴값이 계속 바뀌는데, 함수형 프로그래밍 지향점과 상반되는 예시로 볼 수 있다.