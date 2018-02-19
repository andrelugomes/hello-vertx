package br.com.s2it.web.client.runner;

import io.vertx.core.VertxOptions;
import br.com.s2it.web.client.verticle.VertxWebMain;

/**
 * @author s2it_agomes
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 9/15/17 2:58 PM
 */
public class RunVerticle {

    public static void main(String[] args) {
        Runner.run("/src/main/java/", VertxWebMain.class, new VertxOptions().setClustered(false), null);
    }

}
