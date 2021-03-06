import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.client.HttpRequest;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import rx.Single;

import java.util.concurrent.TimeUnit;

/**
 * Created by andre on 18/02/18.
 */
public class RXVerticle extends AbstractVerticle {

    private WebClient client;

    @Override
    public void start() {
        client = WebClient.create(vertx);
        Router router = Router.router(vertx);
        router.get("/single-zip-requests").handler(this::singleZipRequests);
        router.get("/retry-requests").handler(this::retryRequests);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    private void singleZipRequests(RoutingContext rc) {
        HttpRequest<JsonObject> request1 = client.get(8081, "localhost", "/products/prod3568").as(BodyCodec.jsonObject());
        HttpRequest<JsonObject> request2 = client.get(8081, "localhost", "/products/prod7340").as(BodyCodec.jsonObject());
        Single<JsonObject> s1 = request1.rxSend().map(HttpResponse::body);
        Single<JsonObject> s2 = request2.rxSend().map(HttpResponse::body);

        Single.zip(s1, s2, (p1, p2) -> {
            // We have the results of both requests
            return new JsonObject()
                    .put("prod3568", p1.getString("name"))
                    .put("prod7340", p2.getString("name"));
        }).subscribe(result -> rc.response().end(result.encodePrettily()), error -> {
            error.printStackTrace();
            rc.response().setStatusCode(500).end(error.getMessage());
        });
    }

    private void retryRequests(RoutingContext rc) {
        client.get(8081, "localhost", "/products/prod3568").as(BodyCodec.jsonObject())
            .rxSend()
            .subscribeOn(RxHelper.scheduler(vertx))
            .timeout(5, TimeUnit.SECONDS)
            .retry(1)
            .map(HttpResponse::body)
            .onErrorReturn(t -> new JsonObject().put("prod3568", "D'oh! Timeout"))
            .subscribe(json -> json.encode());
    }
}
