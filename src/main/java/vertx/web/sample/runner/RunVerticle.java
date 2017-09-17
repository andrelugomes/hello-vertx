package vertx.web.sample.runner;

import io.vertx.core.VertxOptions;
import vertx.web.sample.verticles.VertxWebMainVerticle;

/**
 * @author s2it_agomes
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 9/15/17 2:58 PM
 */
public class RunVerticle {

    public static void main(String[] args) {
        Runner.run("/src/main/java/", VertxWebMainVerticle.class, new VertxOptions().setClustered(false), null);
    }

}
