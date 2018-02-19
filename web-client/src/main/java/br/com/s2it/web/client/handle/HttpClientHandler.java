package br.com.s2it.web.client.handle;

import br.com.s2it.web.client.model.RequestModel;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

/**
 * Created by andre on 16/09/17.
 */
public class HttpClientHandler {

    private static WebClient http;

    public HttpClientHandler(WebClient client) {
        this.http = client;
    }

    public static void post(RoutingContext routingContext) {
        final RequestModel model = Json.decodeValue(routingContext.getBodyAsString(), RequestModel.class);
        HttpServerResponse response = routingContext.response();
        System.out.println("Thread for this request= " + Thread.currentThread().getName());
        http.get(model.getPort(), model.getUrl(), model.getUri()).send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> res = ar.result();
                Buffer body = res.bodyAsBuffer();

                System.out.println("Received response with status code " + res.statusCode());
                System.out.println("Received response with body " + res.body());

                response.putHeader("content-type", "application/json").end(body);
            } else {
                ar.cause().printStackTrace();
            }
        });
    }
}
