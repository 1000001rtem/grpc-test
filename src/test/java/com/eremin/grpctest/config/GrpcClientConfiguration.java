package com.eremin.grpctest.config;

import com.eremin.grpctest.ClothesRoutesGrpc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.client.GrpcChannelFactory;

@TestConfiguration
public class GrpcClientConfiguration {
    @Bean
    public ClothesRoutesGrpc.ClothesRoutesBlockingStub grpcClient(GrpcChannelFactory channels) {
        return ClothesRoutesGrpc.newBlockingStub(channels.createChannel("clothes"));
    }
}
