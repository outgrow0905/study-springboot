package com.springboot.advanced.ch2.v6;

import com.springboot.advanced.ch1.trace.LogTrace;
<<<<<<< HEAD
import com.springboot.advanced.ch2.v6.template.AbstractTemplate;
=======
import com.springboot.advanced.ch1.trace.TraceStatus;
import com.springboot.advanced.ch2.template.AbstractTemplate;
>>>>>>> 438818ac4bcd8ad08140edf8a4d7967c357e60f7
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV6 {
    private final OrderRepositoryV6 orderRepository;
    private final LogTrace logTraceV5;

    public void orderItem(String itemId) {
        new AbstractTemplate<Void>(logTraceV5) {
            @Override
            protected Void action() {
                orderRepository.save(itemId);
                return null;
            }
        }.execute("OrderService orderItem()");
    }
}
