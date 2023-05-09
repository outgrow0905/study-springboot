package com.springboot.advanced.ch3.proxy;

import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    void withoutProxy() {
        Client client = new Client(new RealSubject());
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxy() {
        Client client = new Client(new CacheProxy(new RealSubject()));
        client.execute();
        client.execute();
        client.execute();
    }
}
