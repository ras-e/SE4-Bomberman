package dk.sdu.mmmi.bomberman.OSGiBomb.internal;

import dk.sdu.mmmi.bomberman.OSGiBomb.BombPlugin;
import dk.sdu.mmmi.bomberman.common.services.IGamePluginService;
import org.osgi.framework.*;

public class BombActivator implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("STARTING BUNDLE dk.sdu.mmmi.bomberman.OSGiBomb");

        bc.registerService(IEntityProcessingService.class.getName(), new BombProcessingSystem(), null);
        bc.registerService(IGamePluginService.class.getName(), new BombPlugin(), null);
    }

    @Override
    public void stop(BundleContext bc) throws Exception {
        System.out.println("STOPPING BUNDLE dk.sdu.mmmi.bomberman.OSGiBomb");

        for (Bundle bundle : bc.getBundles()) {
            String bundleCat = (String) bundle.getHeaders().get("Bundle-Category");

            if (bundleCat != null && bundleCat.equals("bomb") && bundle.getState() == Bundle.ACTIVE) {
                bundle.stop();
            }
        }
    }
}
