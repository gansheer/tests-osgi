package test.osgi.service;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import test.osgi.api.TestOsgiApi;

public class TestOsgiService implements TestOsgiApi, BundleActivator {

	private ServiceReference<TestOsgiApi> reference;
	private ServiceRegistration<TestOsgiApi> registration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Registering service.");
		registration = context.registerService(TestOsgiApi.class, new TestOsgiService(),
				new Hashtable<String, String>());
		reference = registration.getReference();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Unregistering service.");
		registration.unregister();
	}

	public void execute() {
		System.out.println("execute service (yolo)");

	}

}
