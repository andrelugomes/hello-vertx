package vertx.web.sample.verticles.http.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.BodyHandler;
import vertx.web.sample.verticles.http.client.handle.HttpClientHandler;

/**
 * Created by andre on 16/09/17.
 */
public class HttpClientVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        HttpClientHandler httpClientHandler = new HttpClientHandler(WebClient.create(vertx));

        router.route().handler(BodyHandler.create());
        router.post("/get/").handler(c -> httpClientHandler.get(c));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

}
