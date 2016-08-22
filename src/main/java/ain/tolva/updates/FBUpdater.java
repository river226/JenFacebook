package ain.tolva.updates;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

/**
 * @Author jriver
 *
 * Connect and post to facebook
 */
public class FBUpdater {

    FacebookConnectionFactory FBConnect;
    Facebook fb;
    org.springframework.core.env.Environment env;

    public FBUpdater() {
        FBConnect = new FacebookConnectionFactory(env.getProperty("facebook.clientId"),
                env.getProperty("facebook.clientSecret"));
        AccessGrant ag = FBConnect.getOAuthOperations().authenticateClient();
        Connection<Facebook> connection = FBConnect.createConnection(ag);
        fb = connection != null ? connection.getApi() : null;
    }

    public boolean postToFacebook(String post) {
        fb.feedOperations().updateStatus(post);
        return true;
    }
}
