package vertx.web.sample.verticles.product;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import vertx.web.sample.verticles.product.handle.ProductHandler;

/**
 * @author s2it_agomes
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 9/5/17 2:08 PM
 */
public class ProductVerticle extends AbstractVerticle {

    @Override
    public void start() {

        ProductHandler.setUpInitialData();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.get("/products/:productID").handler(ProductHandler::getProduct);
        router.put("/products/:productID").handler(ProductHandler::addProduct);
        router.get("/products").handler(ProductHandler::listProducts);

        vertx.createHttpServer().requestHandler(router::accept).listen(8081);
    }

}
