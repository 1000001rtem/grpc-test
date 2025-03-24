package com.eremin.api.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "grpc.client")
public class ClothesGrpcClientProperties {
    private String host = "localhost";
    private int port = 6565;
    private boolean usePlaintext = true;

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

    public boolean isUsePlaintext() {
        return usePlaintext;
    }

    public void setUsePlaintext(boolean usePlaintext) {
        this.usePlaintext = usePlaintext;
    }
}
