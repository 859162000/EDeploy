package com.dongql.deploy.bean;

import java.util.List;

/**
 * Created by dongqilin on 2017/3/10.
 */
public class NginxUpStream {

    private String name;
    private boolean ipHash;
    private List<NginxUpStreamServer> servers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIpHash() {
        return ipHash;
    }

    public void setIpHash(boolean ipHash) {
        this.ipHash = ipHash;
    }

    public List<NginxUpStreamServer> getServers() {
        return servers;
    }

    public void setServers(List<NginxUpStreamServer> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        StringBuilder upstream = new StringBuilder();
        String hash = ipHash ? "    ip_hash;\n" : "";
        upstream.append("upstream ").append(name).append(" {\n").append(hash);
        for (NginxUpStreamServer server : servers){
            upstream.append(server.toString());
        }
        upstream.append("}");
        return  upstream.toString();
    }

}
