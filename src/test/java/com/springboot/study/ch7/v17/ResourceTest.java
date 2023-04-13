package com.springboot.study.ch7.v17;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

@Slf4j
public class ResourceTest {
    @Test
    void resource_from_file() throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("ch7/user.xml");
        log.info("exists: {}", resource.exists());

        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            String result =  FileCopyUtils.copyToString(reader);
            log.info("result: {}", result);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Test
    void resource_from_url() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("https://jsonplaceholder.typicode.com/posts/1");
        log.info("exists: {}", resource.exists());

        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            String result =  FileCopyUtils.copyToString(reader);
            log.info("result: {}", result);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
