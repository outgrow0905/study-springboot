#### Function
함수형프로그래밍을 위해 자바에서 기본으로 제공하는 클래스들이 있다.  
하나씩 알아보자.

#### Function
~~~java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
~~~

T 타입의 객체를 받아서 R 타입의 객체를 리턴한다.  
아래와 같이 상속받아서 사용하면 된다.  

~~~java
public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer number) {
        return number + 10;
    }
}
~~~

사용해보자.

~~~java
@Test
void plus10() {
    Plus10 plus10 = new Plus10();
    assertEquals(11, plus10.apply(1));
}
~~~

익명 클래스를 활용하여 아래와 같이 편하게 사용한다.

~~~java
@Test
void plus10ByAnonymousInnerClass() {
    Function<Integer, Integer> plus10 = integer -> integer + 10;
    assertEquals(11, plus10.apply(1));
}
~~~

##### compose, andThen
Function 인터페이스를 살펴보면 사실 메서드가 몇 개 더 있다.

~~~java
default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
    Objects.requireNonNull(before);
    return (V v) -> apply(before.apply(v));
}

default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return (T t) -> after.apply(apply(t));
}
~~~

compose는 인자로 받은 Function을 먼저 수행하고 자신의 Function을 수행한다.  
andThen은 자신의 Function을 먼저 수행하고 인자로 받은 Function을 수행한다.

~~~java
@Test
void FunctionDefaultMethod() {
    Function<Integer, Integer> plus10 = integer -> integer + 10;
    Function<Integer, Integer> minus5 = integer -> integer - 5;
    Function<Integer, Integer> multiply2 = integer -> integer * 2;

    Function<Integer, Integer> plus10AndMinus5AndMultiply2Function = plus10.andThen(minus5.andThen(multiply2));
    Function<Integer, Integer> multiply2AndMinus5AndPlus10Function = plus10.compose(minus5.compose(multiply2));

    assertEquals(30, plus10AndMinus5AndMultiply2Function.apply(10));
    assertEquals(25, multiply2AndMinus5AndPlus10Function.apply(10));
}
~~~


#### BiFunction
~~~java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
    
    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }
}
~~~
T, U 두 가지 인자를 받아서 R 타입을 리턴하는 함수이다.  
Function과 관련이 깊어보이는데 Function을 상속하지 않은것은 이유가 있어보인다.  
Function에서 default 메서드로 제공하는 andThen, compose를 사용할 수 없기 때문으로 생각된다.  
따라서 andThen만 BiFunction의 반환값을 인자로 받는 Function으로 이어갈 수 있도록 메서드가 구현되어 있다.  
하나만 사용해보고 넘어가자.

~~~java
@Test
void BiFunction() {
    BiFunction<Integer, Integer, Integer> sumAndPlus10 = (a, b) -> a + b + 10;
    assertEquals(20, sumAndPlus10.apply(5, 5));
}
~~~



#### Consumer
~~~java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
~~~
T 인자를 받아서 무언가 수행하고 아무것도 리턴하지 않는다.  
바로 사용해보자.

~~~java
@Test
void consumer() {
    Consumer<String> helloConsumer = str -> System.out.println("hello " + str);
    helloConsumer.accept("lambda");
}
~~~

andThen을 보니 다음 consumer를 바로 수행하도록 연결시킬 수 있는 것 같다.

~~~java
@Test
void consumer() {
    Consumer<String> byeConsumer = str -> System.out.println("bye " + str);
    Consumer<String> helloAndByeConsumer = helloConsumer.andThen(byeConsumer);
    helloAndByeConsumer.accept("lambda");
}
~~~


#### Supplier
~~~java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
~~~

인자를 받지않고 T 를 리턴한다. 바로 사용해보자.

~~~java
@Test
void supplier() {
    Supplier<Integer> return10 = () -> 10;
    assertEquals(10, return10.get());
}
~~~



#### Predicate
~~~
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
~~~

T 를 받아서 boolean을 리턴한다. 바로 사용해보자.

~~~java
@Test
void predicate() {
    Predicate<Integer> isEven = i -> i % 2 == 0;
    assertTrue(isEven.test(2));
    assertFalse(isEven.test(1));
}
~~~

이정도가 기본 Function 패키지이다.  
그런데 실제 Function 패키지를 가면 훨씬 많은 클래스들을 볼 수 있는데, 이는 전부 위의 조합 혹은 확장이라고 볼 수 있다.  
몇 가지만 살펴보자.



#### BinaryOperator
~~~java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}

~~~
BiFunction을 확장하였는데 BiFunction의 두 인자와 리턴값이 모두 같은 타입일때에 사용하는 인터페이스이다.  
추가기능인 minBy, maxBy가 있는데, 이는 들어오는 두 안지가 모두 같은 타입이기 때문에 (굳이 숫자가 아니더라도) 최소, 최대값을 구현할 수 있도록 할 수 있다.  
Comparator는 따로 구현해서 넣어주면 될 것이다.  


#### IntPredicate
이름부터 느낌이 오는가?  
위의 Predicate는 Integer를 인자로받는 Predicate이다. 
같은 예시를 IntPredicate를 사용하여 변경해보자.

~~~java
@Test
void intPredicate() {
    IntPredicate isEven = i -> i % 2 == 0;
    assertTrue(isEven.test(2));
    assertFalse(isEven.test(1));
}
~~~