package com.bda.customer.config;

import com.bda.customer.service.CustomerGrpcService;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class GrpcServiceConf {
    @Bean
    public Server grpcServer(CustomerGrpcService customerProtoService) throws Exception {
        Server server = NettyServerBuilder.forAddress(new InetSocketAddress("0.0.0.0", 9091))
                .addService(customerProtoService)
                .build();
        server.start();


        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

        return server;
    }
}
