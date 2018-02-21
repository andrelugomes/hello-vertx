package vertx.web.sample.verticles.http.client;

import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.circuitbreaker.CircuitBreaker;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.client.HttpRequest;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import rx.Single;

/**
 * Created by andre on 18/02/18.
 */
public class RXCircuitBreakVerticle extends AbstractVerticle {

    private WebClient client;

    @Override
    public void start() {
        client = WebClient.create(vertx);
        Router router = Router.router(vertx);
        router.get("/break").handler(this::singleZipRequests);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    private void singleZipRequests(RoutingContext rc) {

        CircuitBreaker circuit = CircuitBreaker.create("my-circuit", vertx, new CircuitBreakerOptions()
                .setFallbackOnFailure(true) // Call the fallback // on failures
                .setTimeout(2000) // Set the operation timeout
                .setMaxFailures(5) // Number of failures before // switching to // the 'open' state
                .setResetTimeout(5000) // Time before attempting // to reset // the circuit breaker
        );
        circuit.rxExecuteCommandWithFallback(future -> {
                    client.get(8081, "localhost", "/products/XXX")
                            .rxSend()
                            .map(HttpResponse::bodyAsJsonObject)
                            .subscribe(future::complete, future::fail);
                },
                t -> new JsonObject().put("message", "D'oh! Fallback")).
                subscribe(json -> { // Get the actual json or the fallback value
                    System.out.println(json.encode());
                });


    }
}
