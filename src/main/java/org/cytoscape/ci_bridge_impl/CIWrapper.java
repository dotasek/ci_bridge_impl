package org.cytoscape.ci_bridge_impl;

import java.util.ArrayList;

import org.cytoscape.ci.model.CIError;
import org.cytoscape.ci.model.CIResponse;

public class CIWrapper {
	public static <K> CIResponse<K> wrapInCI(K object) {
		CIResponse<K> ciResponse = new CIResponse<K>();
		ciResponse.data = object;
		ciResponse.errors = new ArrayList<CIError>();
		return ciResponse;
	}
}
