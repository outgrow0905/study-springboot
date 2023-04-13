#### Method Reference
람다에서 메서드 참조하는 방법을 살펴보자.

아래의 클래스를 예시로 시작하자.

~~~java
public class Hello {
    String name;

    // default method
    public Hello() {}
    
    // construct with parameter
    public Hello(String name) {
        this.name = name;
    }

    // static method
    public static void hello(String str) {
        System.out.println("hello " + str);
    }
    
    // instance method
    public void hi(String str){
        System.out.println("hi " + str);
    }
}
~~~

#### static method
Hello의 hello 메서드는 String을 받아서 void로 리턴한다.  
이전에 배운 Consumer가 생각난다.  
Consumer를 만들떄에 Hello의 hello 메서드를 참조해서 생성 및 사용할 수 있다.  
아래와 같이 사용한다.

~~~java
@Test
void staticMethod() {
    Consumer<String> consumer = Hello::hello;
    consumer.accept("method");
}
~~~


#### instance method
Hello의 hi 메서드도 String을 받아서 void로 리턴한다.  
역시 Consumer가 되시겠다.  
인스턴스메서드이니 참조하려면 인스턴스를 먼저 생성해야 한다.  
아래와 같이 사용한다.

~~~java
@Test
void instanceMethod() {
    Hello hello = new Hello();
    Consumer<String> consumer = hello::hi;
    consumer.accept("instance");
}
~~~



#### constructor
같은 방법으로 생성자도 참조할 수 있다.  
디폴트 메서드는 아무것도 받지 않고 Hello 클래스를 리턴한다.  
Supplier로 구현될 것 같다.  

파라미터를 받는 생성자는 어떻게 될까?  
String을 받아서 Hello 클래스를 리턴하니, Function으로 가능할 것 같다.  

아래와 같이 가능하다.

~~~java
@Test
void constructor() {
    // default constructor
    Supplier<Hello> supplier = Hello::new;

    // constructor with parameter
    Function<String, Hello> function = Hello::new;
}
~~~

살펴보니 둘다 `Hello::new`이다. 하지만 실제 어떤 생성자를 참조하는지 들어가보면 각각 다르다.  
람다코드만 보고서는 바로 알 수 없으니, 단점이라면 단점일 수 있겠다.


#### instance method without construct
String의 `compareToIgnoreCase`는 static 메서드가 아니다.  
근데 이게 어떻게 가능한가?  

~~~java
@Test
void difficult() {
    String[] names = {"banana", "apple", "mango"};
    Arrays.sort(names, String::compareToIgnoreCase);
}
~~~