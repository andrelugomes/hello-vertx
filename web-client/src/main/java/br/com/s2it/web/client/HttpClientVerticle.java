package br.com.s2it.web.client;

import br.com.s2it.web.client.handle.HttpClientHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Created by andre on 16/09/17.
 */
public class HttpClientVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        HttpClientHandler httpClientHandler = new HttpClientHandler(WebClient.create(vertx));

        router.route().handler(BodyHandler.create());
        router.post("/post/").handler(c -> httpClientHandler.post(c));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

}
