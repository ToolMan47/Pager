package com.toolman.pager.grpc.unary;

import com.google.common.util.concurrent.ListenableFuture;
import com.toolman.pager.HelloRequest;
import com.toolman.pager.HelloResponse;
import com.toolman.pager.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ExecutionException;

public class GrpcBlockClient {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext() // without any encryption
                .build();

        // In gRPC, a Stub is a client-side proxy that handles the low-level communication details between the client and the remote server.
        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        // make a sync call
        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("ToolMan")
                .setLastName("gRPC")
                .build());

        System.out.println("Response received from server:\n" + helloResponse);

        channel.shutdown();
    }
}
