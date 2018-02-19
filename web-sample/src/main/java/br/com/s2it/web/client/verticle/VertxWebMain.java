package br.com.s2it.web.client.verticle;

import br.com.s2it.web.client.verticle.product.ProductVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * @author s2it_agomes
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 9/5/17 2:08 PM
 */
public class VertxWebMain extends AbstractVerticle {

    @Override
    public void start(Future<Void> done) {
        vertx.deployVerticle(new ProductVerticle());
    }
}
