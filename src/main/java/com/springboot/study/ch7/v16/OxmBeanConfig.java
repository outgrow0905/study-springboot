package com.springboot.study.ch7.v16;


import com.springboot.study.ch7.v15.HashMapSqlRegistry;
import com.springboot.study.ch7.v15.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Bean
    public SqlService myOxmSqlService() {
        OxmSqlService oxmSqlService = new OxmSqlService();
        OxmSqlLoader oxmSqlLoader = new OxmSqlLoader();
        oxmSqlLoader.setUnmarshaller(jaxbUnMarshaller());
        oxmSqlService.setSqlLoader(oxmSqlLoader);
        oxmSqlService.setSqlRegistry(new HashMapSqlRegistry());

        return oxmSqlService;
    }
}
