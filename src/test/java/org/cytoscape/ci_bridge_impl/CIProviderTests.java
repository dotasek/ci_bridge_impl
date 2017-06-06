package org.cytoscape.ci_bridge_impl;

import org.junit.Test;

public class CIProviderTests implements CIProviderInterface {
	
	
	
	@Test
	public void testCIWrapping() throws InstantiationException, IllegalAccessException {
		CIAppInfoResponse response = CIProvider.getCIResponseFactory().getCIResponse("Floon", CIAppInfoResponse.class);
		System.out.println(response.data);
	}
}
