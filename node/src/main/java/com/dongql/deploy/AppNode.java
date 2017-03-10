package com.dongql.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by dongqilin on 2017/3/10.
 */
@SpringBootApplication
public class AppNode {

    @RequestMapping("/update")
    public String update() {
        return "Hello World";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppNode.class, args);
    }

}
