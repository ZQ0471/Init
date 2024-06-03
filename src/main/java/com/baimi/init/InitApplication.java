package com.baimi.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.baimi.init.mapper")
@EnableSwagger2
//@EnableCaching
@SpringBootApplication
public class InitApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(InitApplication.class, args);
    }

}
