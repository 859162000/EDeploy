package com.dongql.deploy.bean;

/**
 * Created by dongqilin on 2017/3/10.
 */
public class NginxUpStreamServer {

    private String host;
    private int port;
    private int weight;
    private int maxFails;
    private int failTimeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxFails() {
        return maxFails;
    }

    public void setMaxFails(int maxFails) {
        this.maxFails = maxFails;
    }

    public int getFailTimeout() {
        return failTimeout;
    }

    public void setFailTimeout(int failTimeout) {
        this.failTimeout = failTimeout;
    }

    @Override
    public String toString() {
        String w = weight > 0 ? " weight=" + weight : "";
        String fails = maxFails > 0 ? " max_fails=" + maxFails : "";
        String timeout = failTimeout > 0 ? " fail_timeout=" + failTimeout + "s" : "";
        return "    server " + host + ":" + port + w + fails + timeout + ";\n";
    }

}
