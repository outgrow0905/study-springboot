#### Stream
자바8에서 가장 많은 관심을 받은 스트림을 알아보자.  
먼저 [공식문서](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)를 통해 스트림의 특성을 알아보자.

##### operation
스트림 오퍼레이션은 크게 두 가지로 나뉜다.  
중개 오퍼레이션(intermediate operation)과 종결 오퍼레이션(terminate operation)이다.  
중개 오퍼레이션은 스트림을 다른 스트림으로 변화시킨다. 주의할 점은 다른 스트림으로 변화시킨다고 했지, 작업을 수행한다고 하지 않은 점이다.
예를 들어, `filter(Predicate)`와 같은 중개 오퍼레이션은 `Predicate` 조건에 맞는 객체만 필터링해주는 역할을 하지만, 종결 오퍼레이션이 호출되지 않으면 아무런 일도 하지 않는다.  

그렇다. 스트림은 위에 말한대로 종결 오퍼레이션이 호출되어야 일을 한다. 
이러한 스트림의 성질을 `lazy` 하다고 한다. 수 많은 중개 오퍼레이션을 연계해두어도 종결 오퍼레이션이 호출될때까지는 아무런 일도 시작하지 않기 때문이다.  
그렇다면 종결 오퍼레이션은 무엇인가?  
중개 오퍼레이션은 스트림을 다른 스트림으로 변화시킨다고 하였으니, 이 종결 오퍼레이션은 스트림이 아닌 다른 형태로 변화시킬 것이다.  
예를 들어, `sum(), count(), collect()` 등이 있을 수 있겠다.
그리고 무엇보다 종결 오퍼레이션이 호출되기 전까지 `lazy` 하던 스트림이 작업을 시작하도록 할 것이다.  

코드를 만들어 확인해보자.  

~~~java
@Test
void streamOperation() {
    List<String> fruits = List.of("apple", "banana", "mango", "blueberry");

    // intermediate operation
    // lazy
    Stream<String> lazyStream = fruits.stream().filter(fruit -> fruit.startsWith("b"));
    System.out.println(fruits); // [apple, banana, mango, blueberry]

    // terminate operation
    long count = lazyStream.count();
    System.out.println(count); // 2
}
~~~