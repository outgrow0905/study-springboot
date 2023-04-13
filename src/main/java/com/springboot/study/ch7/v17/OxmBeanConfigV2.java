package com.springboot.study.ch7.v17;


import com.springboot.study.ch7.v15.HashMapSqlRegistry;
import com.springboot.study.ch7.v15.SqlService;
import com.springboot.study.ch7.v16.OxmSqlLoader;
import com.springboot.study.ch7.v16.OxmSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class OxmBeanConfigV2 {

    @Autowired
    private Unmarshaller jaxbUnMarshaller;

    @Bean
    public SqlService myOxmSqlServiceV2() {
        OxmSqlService oxmSqlService = new OxmSqlService();
        OxmSqlLoaderV2 oxmSqlLoaderV2 = new OxmSqlLoaderV2();
        oxmSqlLoaderV2.setUnmarshaller(jaxbUnMarshaller);
        oxmSqlService.setSqlLoader(oxmSqlLoaderV2);
        oxmSqlService.setSqlRegistry(new HashMapSqlRegistry());

        return oxmSqlService;
    }
}
