#### Lambda
람다를 사용하면서 몇가지 기초지식으로 알아야 하는 것이 있다.



#### effectively final
무슨 의미일까? 
final로 선언되지는 않았지만, 실제로 final의 효과를 내는 (혹은 final의 역할을 하는) 변수라는 의미이다.  
또 다른 의미로는 final로 사용되지 않으면 안된다는 의미이기도 하다.

아래의 코드를 살펴보자.
로컬 클래스, 익명 클래스, 람다 전부에서 myNumber를 증가시키려고 하면 아래의 컴파일 오류가 발생한다.

`
Variable 'myNumber' is accessed from within inner class, needs to be final or effectively final
`

myNumber 변수를 final로 써서 변경하지 못하도록 하거나, 
final로 선언을 하지 않을 것이라면 아래에서 해당 변수를 변경하지 못하게 하면서 `effectively final`처럼 사용하라는 의미이다.

~~~java
public class Bar {
    void run() {
        int myNumber = 10;

        // Local class
        class Foo {
            void run() {
//                myNumber++;
                System.out.println(myNumber); // 11
            }
        }

        // anonymous class
        IntConsumer consumer = new IntConsumer() {
            @Override
            public void accept(int value) {
//                myNumber++;
                System.out.println(myNumber); // 12
            }
        };

        // Lambda
        IntConsumer functionByLambda = i -> {
//            myNumber++;
            System.out.println(myNumber);
        };
    }
}
~~~



#### scope
람다의 범위는 어떻게 될까?  
아래의 코드를 살펴보자.  

내부클래스와 익명클래스는 각자의 scope가 있다.  
따라서, 각자의 scope내에서 myNumber를 정의하면 상위 scope에서 정의된 같은 변수를 덮어씌운다.  

하지만, 람다는 고유의 scope를 가지지 않는다. 
아래의 람다코드에서 `int myNumber = 13;`부분을 주석제거하면 컴파일 오류가 발생한다.  
같은 scope 내에서 같은 이름의 변수가 이미 생성되어 있다는 것이다.

아래의 익명클래스와 람다는 완전히 동일한 코드이지만, scope의 차이가 있는 것이다.  
사용상 주의하면 좋을 것 같다.

~~~java
public class Foo {
    void run() {
        int myNumber = 10;

        // Local class
        class Bar {
            void run() {
                int myNumber = 11;
                System.out.println(myNumber); // 11
            }
        }

        // anonymous class
        IntConsumer consumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                int myNumber = 12;
                System.out.println(myNumber); // 12
            }
        };

        // Lambda
        IntConsumer functionByLambda = i -> {
//            int myNumber = 13;
            System.out.println(myNumber);
        };
    }
}
~~~