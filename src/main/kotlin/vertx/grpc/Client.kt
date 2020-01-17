package vertx.grpc

import io.vertx.core.Vertx
import io.vertx.example.grpc.EmptyPingPongServiceGrpc
import io.vertx.example.grpc.EmptyProtos
import io.vertx.grpc.VertxChannelBuilder

fun main() {
    val vertx = Vertx.vertx()
    val channel = VertxChannelBuilder
            .forAddress(vertx, "localhost", 8080)
            .usePlaintext(true)
            .build()
    val stub = EmptyPingPongServiceGrpc.newVertxStub(channel)

    val request = EmptyProtos.Empty.newBuilder().build()

    stub.emptyCall(request) {
        ar ->
        if (ar.succeeded()) {
            println("Got the server response.")
        } else {
            println("Could not reach server " + ar.cause().message)
        }
    }
}