package com.springboot.study.ch7.v16;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class OxmBeanConfig {

    @Bean
    public Unmarshaller jaxbUnMarshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.springboot.study.ch7.v14.jaxb");
        return jaxb2Marshaller;

    }
}
