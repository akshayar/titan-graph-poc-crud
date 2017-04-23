package com.aksh.titan.db;

import org.apache.tinkerpop.gremlin.structure.Graph;

public interface GraphDBConfigurator {
	
	void init() throws Exception;

	Graph getGraph();
	
}
