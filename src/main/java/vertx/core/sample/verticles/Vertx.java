package vertx.core.sample.verticles;

import io.vertx.core.AbstractVerticle;

/**
 * @author s2it_agomes
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 9/5/17 2:08 PM
 */
public class Vertx extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        //CORE SAMPLE
        vertx.createHttpServer()
                .requestHandler(request -> request.response().end("Hello Java world !"))
                .listen(8081);
    }
}