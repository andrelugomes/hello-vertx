package vertx.web.sample.verticles.http.client.model;

/**
 * Created by andre on 16/09/17.
 */
public class RequestModel {

    private String url;
    private int port;
    private String uri;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
