package ain.tolva;

import ain.tolva.updates.FBUpdater;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

/**
 * @Author jriver
 *
 * Post Build status of a Jenkins project onto Facebook
 */
public class FBNotifier extends Notifier {

    private final String local;

    public FBNotifier(String local) {
        this.local = local;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {
        String post = getPost(build);
        FBUpdater update = new FBUpdater();
        update.postToFacebook(post);
        return true;
    }

    private String getPost(AbstractBuild<?, ?> build) {
        return build.getProject().getName() +
                ":\n " + build.getResult().toString();
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {
        private String global;

        @Override
        public String getDisplayName() {
            return "FBNotifier";
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException{
            global = formData.getString("global");
            save();
            return super.configure(req, formData);
        }

    }
}