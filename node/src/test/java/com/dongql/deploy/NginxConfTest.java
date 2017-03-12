package com.dongql.deploy;

import com.dongql.deploy.bean.NginxUpStream;
import com.dongql.deploy.bean.NginxUpStreamServer;
import com.dongql.deploy.util.NginxUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dongqilin on 2017/3/12.
 */
public class NginxConfTest {

    @Test
    public void refine() {
        List<NginxUpStream> upStreams = new ArrayList<>();
        upStreams.add(getNginxUpStream("erp"));
        upStreams.add(getNginxUpStream("wechat"));
        boolean success = NginxUtil.refineNginxConf("D:\\nginx-1.8.0\\conf\\nginx.conf", upStreams);
        assertTrue(success);
    }

    @Test
    public void exec() {
        String config = "D:\\nginx-1.8.0\\conf\\nginx.conf";
        try {
            String command = NginxUtil.nginx(config);
            Process exec = Runtime.getRuntime().exec(command);
            int exitValue = exec.waitFor();
            assertEquals("程序异常退出 exit:", exitValue, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static NginxUpStream getNginxUpStream(String name) {
        List<NginxUpStreamServer> servers = new ArrayList<>();
        NginxUpStreamServer server = new NginxUpStreamServer();
        server.setHost("127.0.0.1");
        server.setPort(8080);
        server.setWeight(1);
        servers.add(server);
        server = new NginxUpStreamServer();
        server.setHost("10.0.11.1");
        server.setPort(8088);
        server.setWeight(5);
        server.setMaxFails(3);
        server.setFailTimeout(30);
        servers.add(server);

        NginxUpStream upStream = new NginxUpStream();
        upStream.setName("upstream_" + name);
        upStream.setIpHash(true);
        upStream.setServers(servers);
        return upStream;
    }

}
