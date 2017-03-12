package com.dongql.deploy.bean;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqilin on 2017/3/10.
 */
public class BeanTest {

    @Test
    public void upstream(){
        NginxUpStream upStream = getNginxUpStream();
        System.out.println(upStream.toString());
    }

    private NginxUpStream getNginxUpStream() {
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
        server.setMaxFails(5);
        server.setFailTimeout(60);
        servers.add(server);

        NginxUpStream upStream = new NginxUpStream();
        upStream.setName("upstream_erp");
        upStream.setIpHash(true);
        upStream.setServers(servers);
        return upStream;
    }

}
