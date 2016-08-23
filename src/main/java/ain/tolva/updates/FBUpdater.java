package ain.tolva.updates;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.context.support.StandardServletEnvironment;

/**
 * @author jriver
 *
 * Connect and post to facebook
 */
public class FBUpdater {

    Facebook fb;
    FacebookConnectionFactory FBConnect;
    StandardServletEnvironment env;

    public FBUpdater() {
        env = new StandardServletEnvironment();
        //FBConnect = new FacebookConnectionFactory(env.getProperty("facebook.clientId"),
        //        env.getProperty("facebook.clientSecret"));
        FBConnect = new FacebookConnectionFactory("164499280651375",
                "808158a464819933e17b6b0345719422");
        AccessGrant ag = FBConnect.getOAuthOperations().authenticateClient();
        Connection<Facebook> connection = FBConnect.createConnection(ag);
        fb = connection != null ? connection.getApi() : null;
    }

    public boolean postToFacebook(String post) {
        fb.feedOperations().updateStatus(post);
        return true;
    }
}
