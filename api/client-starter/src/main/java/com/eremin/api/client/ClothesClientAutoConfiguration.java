package com.eremin.api.client;

import com.eremin.grpctest.ClothesRoutesGrpc;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(ClothesGrpcClientProperties.class)
public class ClothesClientAutoConfiguration {

    @Bean
    public ClothesRoutesGrpc.ClothesRoutesBlockingStub blockingStub(ClothesGrpcClientProperties properties) {
        var channel = ManagedChannelBuilder.forAddress(properties.getHost(), properties.getPort())
                .usePlaintext()
                .build();

        return ClothesRoutesGrpc.newBlockingStub(channel);
    }

    @Bean
    public ClothesGrpcClient clothesGrpcClient(ClothesRoutesGrpc.ClothesRoutesBlockingStub blockingStub) {
        return new ClothesGrpcClientImpl(blockingStub);
    }

}
