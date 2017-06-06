package org.cytoscape.ci_bridge_impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cytoscape.ci.CIErrorFactory;
import org.cytoscape.ci.CIExceptionFactory;
import org.cytoscape.ci.CIResponseFactory;
import org.cytoscape.ci.model.CIError;
import org.cytoscape.ci.model.CIResponse;
import org.cytoscape.ci_bridge_impl.internal.CIErrorFactoryImpl;
import org.cytoscape.ci_bridge_impl.internal.CIExceptionFactoryImpl;
import org.cytoscape.ci_bridge_impl.internal.CIResponseFactoryImpl;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class CIProvider {
	
	public static CIResponseFactory getCIResponseFactory() {
		return new CIResponseFactoryImpl();
	}
	
	public static CIErrorFactory getCIErrorFactory(BundleContext bc) throws IOException {
		return new CIErrorFactoryImpl(getLogLocation(bc));
	}
	
	public static CIExceptionFactory getCIExceptionFactory() {
		return new CIExceptionFactoryImpl();
	}
	
	private static final URI getLogLocation(BundleContext bc) throws IOException {
		final String logLocation;

		// Extract Karaf's log file location
		ConfigurationAdmin configurationAdmin = (ConfigurationAdmin) bc.getService(bc.getServiceReference(ConfigurationAdmin.class.getName()));
		
		if (configurationAdmin != null) {
			Configuration config = configurationAdmin.getConfiguration("org.ops4j.pax.logging");

			Dictionary<?,?> dictionary = config.getProperties();
			Object logObject = dictionary.get("log4j.appender.file.File");
			if (logObject != null && logObject instanceof String) {
				logLocation = (String) logObject;
			}
			else {
				logLocation = null;
			}
		}
		else {
			logLocation = null;
		}
		return (new File(logLocation)).toURI();
	}
}
