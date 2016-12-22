package core;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import java.util.Base64;

public class RestHelpers {

    public static Invocation.Builder requestTo(String uri) {
        return ClientBuilder.newClient().target(uri).request();
    }

    public static Invocation.Builder authorized(Invocation.Builder requestBuilder, String login, String password) {
        return requestBuilder.header("Authorization", "Basic " + Base64.getEncoder().encodeToString((login + ":" + password).getBytes()));
    }

    public static Invocation.Builder authorizedRequest(String uri) {
        return authorized(requestTo(uri), "miguel", "python");
    }

}
