package com.aksh.titan.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.thinkaurelius.titan.graphdb.tinkerpop.TitanBlueprintsGraph;

@Component("createRelationShip")
public class GraphRelationShipCreattionService implements  RelationShipCreator {
	private static final Logger LOGGER=Logger.getLogger(GraphRelationShipCreattionService.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss Z");
	@Autowired
	@Qualifier("titanGraph")
	private Graph graphDb;
	

	@PostConstruct
	public void init() {
		registerShutdownHook();
//		graphDb=graphDBConfigurator.getGraph();
	}

	
	public void creatRelationShip(Map<String, String> map2) {
		try {
			createRelationShip_(map2);
			graphDb.tx().commit();
		} catch (Exception e) {
			graphDb.tx().rollback();
			LOGGER.error("Error while creating relationship -" + map2, e);
		}
	}
	

	private void createRelationShip_(Map<String, String> map) {


	}

	private void createSendsEmailRelationship(Map<String, String> map, Vertex fromPerson, Vertex email) {
	}

	private void createRecEmailRelationship(Map<String, String> map, Vertex recPerson, Vertex email, String ccBcc) {
	}

	private Edge createEmailsToRelationShip(Map<String, String> map, Vertex fromPerson, Vertex toPerson, Vertex email,
			String ccBcc) {
		return null;
	}



	


	private void shutDown() throws Exception {
		graphDb.close();
	}

	private void registerShutdownHook() {
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running application).
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					shutDown();
				} catch (Exception e) {
					LOGGER.error("Error while shutdown", e);
				}
			}
		});
	}

	private List<String> getNameListFromCCNames(String ccNames) {
		List<String> nameListRes = new ArrayList<String>();
		ccNames = ccNames != null ? ccNames : "";
		String[] str = ccNames.split("<*>");
		List<String> nameList = Arrays.asList(str);
		for (String name : nameList) {
			if (name.length() > 0 && name.contains("<")) {
				nameListRes.add(name.substring(0, name.indexOf("<")).replaceAll(",", ""));
			} else if (name.length() > 0) {
				nameListRes.add(name.replaceAll(",", ""));
			}

		}
		return nameListRes;
	}

	private List<String> getCCEmailList(String ccNames) {
		ccNames = ccNames != null ? ccNames : "";
		return Arrays.asList(ccNames.split(",")).stream().filter(str->!StringUtils.isEmpty(str)).collect(Collectors.toList());
	}

	public void setGraphDb(TitanBlueprintsGraph graphDb) {
		this.graphDb = graphDb;
	}

}
