package com.aksh.titan.db;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkaurelius.titan.core.PropertyKey;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.schema.Parameter;
import com.thinkaurelius.titan.core.schema.TitanGraphIndex;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.core.schema.TitanSchemaType;

@Component
public class GraphSchemaInitializer  {
	public static final String INDEX_BACK_END_NAME = "search";
	@Autowired
	private TitanDBConfigurator titanDBConfigurator;
	TitanGraph graphDb;
	TitanManagement mgmt;
	private static final Logger logger = Logger.getLogger(GraphSchemaInitializer.class);

	@PostConstruct
	public void init() {
		logger.info("Init");
		graphDb = (TitanGraph) titanDBConfigurator.getGraph();
		this.mgmt = graphDb.openManagement();
		createLabels();
		createUniqueIndex();
	}

	private void createLabels() {
//		makeVertexLabel(LABEL_EMAIL);
//		makeVertexLabel(LABEL_PERSON);
//		makeEdgeLabel(RelationshipTypes.EMAILS_TO.name());
//		makeEdgeLabel(RelationshipTypes.REC_EMAIL.name());
//		makeEdgeLabel(RelationshipTypes.SENDS_EMAIL.name());
	}

	private void makeVertexLabel(String label) {
		try {
			mgmt.makeVertexLabel(label).make();
		} catch (Exception e) {
			logger.error("Error while creating the lable-", e);
		}
	}

	private void makeEdgeLabel(String label) {
		try {
			mgmt.makeEdgeLabel(label).make();
		} catch (Exception e) {
			logger.error("Error while creating the lable-", e);
		}
	}

	private void createUniqueIndex() {
		logger.info("Creating unique indexes");
		try {
			// createUniqueCompositeIndexForVertex(LABEL_PERSON,
			// PERSON_EMAILID_PROPERTY, String.class);
			// createCompositeIndexForVertexProperty(LABEL_PERSON,
			// PERSON_SALARY, Double.class);
			// createCompositeIndexForVertexProperty(LABEL_PERSON, PERSON_AGE,
			// Integer.class);
			// createCompositeIndexForVertexProperty(LABEL_PERSON,
			// PERSON_ISINTERNAL_PROPERTY, Boolean.class);
			// createMixedIndexForVertexProperty(LABEL_PERSON,
			// PERSON_NAME_PROPERTY, String.class);
			//
			// createUniqueCompositeIndexForVertex(LABEL_EMAIL,
			// EMAIL_MESSAGE_ID_PROPERTY, String.class);
			//
			// createCompositeIndexForEdgeProperty(RelationshipTypes.SENDS_EMAIL.name(),
			// EMAIL_MESSAGE_ID_PROPERTY,
			// String.class);

			mgmt.commit();

		} catch (Throwable e) {
			mgmt.rollback();
			logger.error("Error -", e);
		}
		logger.info("Indexes Created");

	}

	public Iterable<TitanGraphIndex> getIndexes() {
		return graphDb.openManagement().getGraphIndexes(Vertex.class);
	}

	public PropertyKey makePropertyKey(String propertyKeyName, Class<?> propertyType) {

		PropertyKey propertyKey = mgmt.getPropertyKey(propertyKeyName);
		if (propertyKey == null) {
			propertyKey = mgmt.makePropertyKey(propertyKeyName).dataType(String.class).make();
			logger.info("Property Key>>>>> " + propertyKey + "-" + propertyKey.label());
		}
		return propertyKey;
	}

	private void createUniqueCompositeIndexForVertex(String schemaTypeName, String propertyKeyName,
			Class<?> propertyType) {
		logger.info("Creating Index -" + schemaTypeName + ":" + propertyKeyName + ":" + propertyType);
		TitanSchemaType titanSchemaType = mgmt.getVertexLabel(schemaTypeName);
		PropertyKey propertyKey = makePropertyKey(propertyKeyName, propertyType);
		String indexName = titanSchemaType.name() + "_" + propertyKey;
		TitanGraphIndex graphIndex = mgmt.getGraphIndex(indexName);
		if (graphIndex == null) {
			graphIndex = mgmt.buildIndex(indexName, Vertex.class).addKey(propertyKey)
					.unique()/* indexOnly(titanSchemaType) */
					.buildCompositeIndex();
			logger.info("Index created -" + graphIndex.name() + ":" + graphIndex.getBackingIndex() + ":"
					+ graphIndex.getClass() + ":" + graphIndex.getIndexedElement() + ":"
					+ StringUtils.join(graphIndex.getFieldKeys()));
		}

	}

	private void createCompositeIndexForVertexProperty(String schemaTypeName, String propertyKeyName,
			Class<?> propertyType) {
		logger.info("Creating Index -" + schemaTypeName + ":" + propertyKeyName + ":" + propertyType);
		PropertyKey propertyKey = makePropertyKey(propertyKeyName, propertyType);
		String indexName = schemaTypeName + "_" + propertyKey;
		TitanGraphIndex graphIndex = mgmt.getGraphIndex(indexName);
		if (graphIndex == null) {
			graphIndex = mgmt.buildIndex(indexName, Vertex.class).addKey(propertyKey).buildCompositeIndex();
			logger.info("Index created -" + graphIndex.name() + ":" + graphIndex.getBackingIndex() + ":"
					+ graphIndex.getClass() + ":" + graphIndex.getIndexedElement() + ":"
					+ StringUtils.join(graphIndex.getFieldKeys()));
		}

	}

	private void createCompositeIndexForEdgeProperty(String schemaTypeName, String propertyKeyName,
			Class<?> propertyType) {
		logger.info("Creating Index -" + schemaTypeName + ":" + propertyKeyName + ":" + propertyType);
		PropertyKey propertyKey = makePropertyKey(propertyKeyName, propertyType);
		String indexName = schemaTypeName + "_" + propertyKey;
		TitanGraphIndex graphIndex = mgmt.getGraphIndex(indexName);
		if (graphIndex == null) {
			graphIndex = mgmt.buildIndex(indexName, Edge.class).addKey(propertyKey).buildCompositeIndex();
			logger.info("Index created -" + graphIndex.name() + ":" + graphIndex.getBackingIndex() + ":"
					+ graphIndex.getClass() + ":" + graphIndex.getIndexedElement() + ":"
					+ StringUtils.join(graphIndex.getFieldKeys()));
		}

	}

	private void createMixedIndexForVertexProperty(String schemaTypeName, String propertyKeyName, Class<?> propertyType) {
		logger.info("Creating Mixed Index -" + schemaTypeName + ":" + propertyKeyName + ":" + propertyType);
		PropertyKey propertyKey = makePropertyKey(propertyKeyName, propertyType);
		String indexName = schemaTypeName + "_" + propertyKey;
		TitanGraphIndex graphIndex = mgmt.getGraphIndex(indexName);
		if (graphIndex == null) {
			graphIndex = mgmt.buildIndex(indexName, Vertex.class).addKey(propertyKey,Parameter.of("mapped-name",propertyKey.name())).buildMixedIndex(INDEX_BACK_END_NAME);
			logger.info("Index created -" + graphIndex.name() + ":" + graphIndex.getBackingIndex() + ":"
					+ graphIndex.getClass() + ":" + graphIndex.getIndexedElement() + ":"
					+ StringUtils.join(graphIndex.getFieldKeys()));
		}

	}

}
