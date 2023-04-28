package com.toolman.pager.service;

import com.toolman.pager.HelloRequest;
import com.toolman.pager.HelloResponse;
import com.toolman.pager.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;


/**
 * 在 server 端被呼叫
 * 在 client 端被用來當API
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        System.out.println("----------------------------------hello------------------------------------");

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        // server hang --- difference between sync and async
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // return response in an event-driven way
        // callback
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("----------------------------------bye------------------------------------");
    }

}
