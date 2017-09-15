package runner;

import io.vertx.core.VertxOptions;
import verticles.VertXWeb;

/**
 * @author s2it_agomes
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 9/15/17 2:58 PM
 */
public class RunVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.run("/src/main/java/", VertXWeb.class, new VertxOptions().setClustered(false), null);
    }

}
