package dk.sdu.mmmi.bomberman.bomb;


import dk.sdu.mmmi.bomberman.common.services.IEntityProcessingService;
import dk.sdu.mmmi.bomberman.commonbomb.BombSPI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        BombSystem bs = new BombSystem();
        context.registerService(IEntityProcessingService.class, bs, null);
        context.registerService(BombSPI.class, bs, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
