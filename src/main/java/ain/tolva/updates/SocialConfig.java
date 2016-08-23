package ain.tolva.updates;


import org.springframework.context.annotation.Configuration;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

/**
 * @author jriver
 */

@Configuration
public class SocialConfig implements SocialConfigurer {

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, org.springframework.core.env.Environment env) {
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("facebook.clientId"),
                env.getProperty("facebook.clientSecret")));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return null;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}