#### Optional
자바 8에서 추가된 Optional에 대해서 알아보자.  
이는 NullPointerException에 관한 것이다.  


##### example1
아래의 객체를 사용해보자.
~~~java
public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;
    
    // getters, setters...
}

public class Progress {
    private Duration duration;
    private boolean finished;
    
    // getters, setters...
}
~~~
아래의 코드는 NPE가 발생한다.  
OnlineClass 생성자에서 Progress를 넣어주지 않았으니 그렇다.
~~~java
@Test
void optional() {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    
    // NPE
    System.out.println("duration:" + springClasses.get(0).getProgress().getDuration());
}
~~~
그래서 우리는 보통 아래와 같이 개발한다.
~~~java
@Test
void optional() {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
        
    // NPE fixed
    Progress progress = springClasses.get(0).getProgress();
    if (null != progress) {
        System.out.println("duration: " + progress.getDuration());
    }
}
~~~
이를 Optional을 사용해보자.


##### ofNullable
먼저, `getter`에서 `Optional`로 리턴하도록 변경하자.
~~~java
public class OnlineClass {
    ...
    public Optional<Progress> getOptionalProgress() {
        return Optional.ofNullable(progress);
    }
}
~~~
`Optional`로 만들 때에는 `of`와 `ofNullable`이 있다.   
`of`의 경우는 `null`이 오면 NPE가 발생한다. 가이드를 잘 읽어보고 사용하자.


#### 주의사항
##### 인스턴스 매개변수로 사용하지 말자 (반환타입으로 사용해라.)
`Optional`은 사용상 문법적으로 아무런 제한이 없지만, 리턴타입으로만 `Optional`을 사용하자.    
`getter`, `setter` 예시에서 `setter`에 사용헀다고 가정해보자.

~~~java
public class OnlineClass {
    ...
    private Progress progress;

    public void setOptionalProgress(Optional<Progress> progress) {
        progress.ifPresent(p -> this.progress = p);
    }
~~~
`setter`부터 `Optional`로 받으니 더 안전하다고 생각되는가?
~~~java
@Test
void optional2() {
    springClasses.get(0).setOptionalProgress(null);
}
~~~
위와 같이 사용자는 얼마든지 `null`을 넣을 수 있다. 정말 괜찮은가?  
위의 `setOptionalProgress()`을 보자. `null`에 `ifPresent`함수를 실행하게 되어 NPE가 발생한다.  
이렇게 쓰지 말자. 


#### 인스턴스변수로 사용하지 말자
그냥 쓰지마라 구리다.



#### Optional을 반환타입으로 사용하더라도 null을 리턴해서는 안된다
~~~java
public class OnlineClass {
    ...
    public Optional<Progress> getOptionalProgress() {
        return Optional.ofNullable(progress);
    }
}
~~~
와 같이 반환타입으로 Optional을 잘 사용했다. 클라이언트는 아래와 같이 사용할 것이다.

~~~java
@Test
void optional2() {
    springClasses.get(0).getOptionalProgress().ifPresent(System.out::println);
}
~~~

그런데 만약 getOptionalProgress()에서 아래와 같이 개발했다고 가정해보자.
~~~java
public class OnlineClass {
    ...
    public Optional<Progress> getOptionalProgress() {
        return progress == null ? null : Optional.ofNullable(progress);
    }
}
~~~
인텔리제이에서는 두 가지 경고를 한다.
![optional1](./img/optional1.png)
첫번째 경고는 `Optional` 반환타입에서 `null`을 리턴하고 있으니 조심하라는 것이다.  
왜 조심하라는 것일까?  
위의 `optional2()` 테스트를 다시 살펴보자. `getOptionalProgress()`에서 `null`을 리턴하니 `ifPresent`에서 NPE가 발생할 것이기 떄문이다.

두번째 경고는 아래와 같다.
![optional2](./img/optional2.png)
`progress`는 무조건 `null`이 아니기 때문에 `ofNullable`이 아닌 `of` 함수를 쓰라는 것이다. 불필요하게 `ofNullable`을 쓰고 있다는 것이다.  

굳이 이렇게 코딩해야겠다면 차라리 아래와 같이 사용하자.

`return progress == null ? Optional.empty() : Optional.of(progress);`

#### Collection, Map, Stream등을 Optional로 감싸지 말자
`Collection, Map, Stream` 등등은 이미 `null`일 수 있음을 충분히 알수있는 함수이다.  
굳이 `Optional`로 감싸서 사용하지 않아도 된다.