package com.dongql.deploy.web.console;

import com.dongql.deploy.bean.NginxUpStreamServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by dongqilin on 2017/3/12.
 */
@Controller
@SpringBootApplication
public class AppConsole {

    @RequestMapping("/")
    public String index(Model model) {
        NginxUpStreamServer upstream = new NginxUpStreamServer();
        upstream.setHost("1.1.1.1");
        model.addAttribute("upstream", upstream);
        return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConsole.class, args);
    }

}