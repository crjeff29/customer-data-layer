package com.bda.customer.config;

import com.bda.customer.service.CustomerGrpcService;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

//@Configuration
public class GrpcServiceConf {
//    @Value("${grpc.server.port}")
//    private int grpcPort;
//    @Bean
//    public Server grpcServer(CustomerGrpcService customerProtoService) throws Exception {
//        Server server = NettyServerBuilder.forAddress(new InetSocketAddress("0.0.0.0", grpcPort))
//                .addService(customerProtoService)
//                .build();
//        server.start();
//
//
//        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
//
//        return server;
//    }
}
