package test.osgi.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import test.osgi.api.TestOsgiApi;

public class TestOsgiActivator implements BundleActivator, ServiceListener {

	private BundleContext ctx;
	private ServiceReference serviceReference;

	public void start(BundleContext ctx) {
		System.out.println("Start of activator.");
		this.ctx = ctx;
		//try {
			//ctx.addServiceListener(this, "(objectclass=" + TestOsgiApi.class.getName() + ")");
			ctx.addServiceListener(this);
			TestOsgiApi service = (TestOsgiApi) ctx.getServiceReference(TestOsgiApi.class);
			System.out.println("has pcu service available ?");
			if(service != null){
				System.out.println("yes it does");	
			}
		//} catch (InvalidSyntaxException ise) {
			//System.out.println("InvalidSyntaxException.");
			//ise.printStackTrace();
		//}
	}

	public void stop(BundleContext bundleContext) {
		System.out.println("Stop of activator.");
		if (serviceReference != null) {
			ctx.ungetService(serviceReference);
		}
		this.ctx = null;
	}

	public void serviceChanged(ServiceEvent serviceEvent) {
		System.out.println("serviceChanged.");
		int type = serviceEvent.getType();
		switch (type) {
		case (ServiceEvent.REGISTERED):
			System.out.println("Notification of service registered.");
			serviceReference = serviceEvent.getServiceReference();
			//TestOsgiApi service = (TestOsgiApi) (ctx.getService(serviceReference));
			System.out.println("service is there");
			// pcuCollector.execute();
			break;
		case (ServiceEvent.UNREGISTERING):
			System.out.println("Notification of service unregistered.");
			ctx.ungetService(serviceEvent.getServiceReference());
			break;
		default:
			break;
		}

	}

}
