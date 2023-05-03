#### Runnable, Callable
둘에 대해서 알아보자.



#### Runnable
Runnable은 많이 사용해본것처럼 병렬처리가 필요할떄에 사용했다.  
보통 아래와 같이 어렵지 않게 사용했다.

~~~java
@Test
void runnable() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    System.out.println("start");
    executorService.submit(showMessageByRunnable("hello"));
    executorService.submit(showMessageByRunnable("world"));
    executorService.submit(showMessageByRunnable("my"));
    executorService.submit(showMessageByRunnable("name"));
    executorService.submit(showMessageByRunnable("is"));
    executorService.shutdown();
    System.out.println("end");
}

private Runnable showMessageByRunnable(String message) {
    return () -> {
    System.out.println(message + " Thread: " + Thread.currentThread().getName());
    };
}
~~~

결과는 코드순서대로 수행되지 않고 병렬처리되기 때문에 아래와 같이 출력될 때도 있다.

~~~
start
end
world Thread: pool-1-thread-2
hello Thread: pool-1-thread-1
my Thread: pool-1-thread-1
name Thread: pool-1-thread-2
is Thread: pool-1-thread-1
~~~



#### Callable
`Callable`은 `Runnable`과 비슷하다.  
하지만 `void`인 `Runnable`과 달리 `Callable`은 반환파라미터가 있다는 것이다.  
일단 반환파라미터의 사용없이 `Runnable`처럼 사용해보자.

~~~java
@Test
void callableWithoutFuture() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    System.out.println("start");
    Future<String> hello = executorService.submit(showMessageByCallable("hello"));
    Future<String> world = executorService.submit(showMessageByCallable("world"));
    Future<String> my = executorService.submit(showMessageByCallable("my"));
    Future<String> name = executorService.submit(showMessageByCallable("name"));
    Future<String> is = executorService.submit(showMessageByCallable("is"));
    System.out.println("end");
}

private Callable<String> showMessageByCallable(String message) {
    return () -> {
        System.out.println(message + " Thread: " + Thread.currentThread().getName());
        return "hello " + message;
    };
}
~~~

결과를 보면 `Runnable`과 똑같이 동작한다는 것을 알 수 있다.
~~~
start
end
world Thread: pool-1-thread-2
hello Thread: pool-1-thread-1
my Thread: pool-1-thread-2
name Thread: pool-1-thread-1
~~~



##### get
Future를 받아올 수 있으니 이를 사용해보자.

아래의 결과는 어떻게 될까?
~~~java
@Test
void callableUsingFuture() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    System.out.println("start");
    Future<String> hello = executorService.submit(showMessageByCallable("hello"));
    hello.get();
    Future<String> world = executorService.submit(showMessageByCallable("world"));
    world.get();
    Future<String> my = executorService.submit(showMessageByCallable("my"));
    my.get();
    Future<String> name = executorService.submit(showMessageByCallable("name"));
    name.get();
    Future<String> is = executorService.submit(showMessageByCallable("is"));
    is.get();
    System.out.println("end");
}
~~~

결과는 아래와 같다.

~~~
start
hello Thread: pool-1-thread-1
world Thread: pool-1-thread-2 
my Thread: pool-1-thread-1
name Thread: pool-1-thread-2
is Thread: pool-1-thread-1
end
~~~

병렬처리를 하기위해 Executor를 사용했지만, 마치 하나의 쓰레드에서 동작한 것처럼 병렬처리의 이점을 전혀 가져가지 못했다.



##### invokeAll
`invokeAll` 메서드는 여러개의 `Callable`을 수행할때에 유용할 수 있다.  
~~~java
@Test
void invokeAll() throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    
    Callable<String> callable1 = () -> {
        Thread.sleep(1000);
        return "callable1";
    };
    Callable<String> callable2 = () -> {
         Thread.sleep(2000);
         return "callable2";
    };
    Callable<String> callable3 = () -> {
         Thread.sleep(3000);
         return "callable3";
    };
    
    List<Future<String>> futures = executorService.invokeAll(Arrays.asList(callable1, callable2, callable3));
    for (Future<String> future : futures) {
        System.out.println(future.get());
    }
}
~~~
위의 결과는 1초, 2초, 3초마다 메시지가 찍히지 않고, 3초뒤에 3개의 메시지가 같이 찍히게 된다.  
어떨 때 사용할 수 있을까?  
여러개의 은행에 예금한 금액의 총합을 보여주는 기능에 사용할 수 있을 것이다.  



##### invokeAny
상황에 따라 `invokeAny` 메서드가 유용할 수 있다.

~~~java
@Test
void invokeAny() throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    Callable<String> callable1 = () -> {
        Thread.sleep(3000);
        return "callable1";
    };
    Callable<String> callable2 = () -> {
        Thread.sleep(2000);
        return "callable2";
    };
    Callable<String> callable3 = () -> {
        Thread.sleep(1000);
        return "callable3";
    };

    String any = executorService.invokeAny(Arrays.asList(callable1, callable2, callable3));
    System.out.println(any);
}
~~~
`invokeAny`는 여러개의 `Callable`을 수행하고 가장 빨리 결과값이 반환된 것을 사용하게 된다.  
그리고, 이는 `blocking`으로 작동한다. `invokeAny`의 반환값이 `Future`가 아니라 `String`임을 주의하자.  
위의 예시에서는 `callable3`가 찍힌다.  

어떨 때 사용할 수 있을까?  
3개의 서버에 같은 파일을 분산백업 해놓고, 가장 빨리 응답이 오는 서버의 데이터를 사용할 수 있겠다.  

위의 예시에서 주의사항으로 쓰레드 개수를 3개보다 적게 하면 `callable2`가 리턴된다.  
이유는 스스로 생각해보자.