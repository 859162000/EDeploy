package com.dongql.deploy;

import com.dongql.deploy.bean.NginxUpStream;
import com.dongql.deploy.common.Result;
import com.dongql.deploy.util.NginxUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

/**
 * Created by dongqilin on 2017/3/10.
 */
@RestController
@SpringBootApplication
public class AppNode {

    private static String nginxConf;

    /**
     * 根据配置数据生成新的nginx.conf，然后优雅重启NGINX
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object update(@RequestBody List<NginxUpStream> data) {
        boolean success = NginxUtil.refineNginxConf(nginxConf, data);
        if (success) {
            try {
                String command = NginxUtil.nginx(nginxConf);
                Process exec = Runtime.getRuntime().exec(command);
                int exitValue = exec.waitFor();
                if(exitValue != 0){
                    return Result.error("程序异常退出 exit:" + exitValue);
                }
            } catch (Exception e) {
                return Result.error(e.getMessage());
            }
            return Result.success();
        }
        return Result.error("处理失败");
    }

    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 1) {
            System.out.println("Usage: java -jar node-xxx.jar path-to-nginx.conf notify-url-base");
            System.exit(1);
        }
        nginxConf = args[0];
        File configFile = new File(nginxConf);
        if (!configFile.exists()) {
            System.out.println("file not exists: " + nginxConf);
            System.exit(2);
        }
        if (!configFile.canWrite()) {
            System.out.println("cannot modify file : " + nginxConf + ", check your permission...");
            System.exit(3);
        }
        System.out.println(nginxConf + " is OK!");
        SpringApplication.run(AppNode.class, args);
    }

}
