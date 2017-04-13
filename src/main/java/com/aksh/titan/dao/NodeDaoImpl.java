package com.aksh.titan.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aksh.titan.dto.Node;
import com.google.common.base.Preconditions;

public class NodeDaoImpl implements NodeDao{

	private static Logger logger =Logger.getLogger(NodeDaoImpl.class);
	
	@Autowired
	@Qualifier("titanGraph")
	private Graph graph;
	
	/*public void init(){
		logger.info("Init");
		graph=graphDBConfigurator.getGraph();
		logger.info("Initialization done.");
	}*/
	
	public void shutdown() throws Exception{
		logger.info("Shutdown");
		graph.close();
		logger.info("Shutdown done");
	}
	
	
	
	@Override
	public Node create(Node baseEntity, boolean embedded) {
		return null;
		
		/*Vertex vertex=graph.addVertex(baseEntity.getLabel());
		String 
		return baseEntity;*/
	}
	@Override
	public void delete(String uri) {
		Vertex vertex = getEntityByURI(uri);
		graph.traversal().V(123).remove();
		if(vertex!=null){
			vertex.property("isSoftDeleted", true);
		}
	}
	
	@Override
	public List<Node> query(Map<String, Object> properties) {
		GraphTraversal<Vertex, Vertex> graphTraversal = graph.traversal().V();
		properties.keySet().forEach(key -> {
			graphTraversal.has(key, properties.get(key));
		});
		List<Vertex> vertices=graphTraversal.toList();
		List<Node> result=vertices.stream().map(this::buildBaseEntity).collect(Collectors.toList());
		
		return result;
	}
	private Node buildBaseEntity(Vertex vertex){
		return null;
	}
	@Override
	public Node readByURI(String graphEntityId) {
		Node result=null;
		Vertex vertex = getEntityByURI(graphEntityId);
		result=buildBaseEntity(vertex);
		return result;
	}

	private Vertex getEntityByURI(String graphEntityId) {
		List<Vertex> vertices=graph.traversal().V(graphEntityId).toList();
		Vertex vertex=null;
		if(!CollectionUtils.isEmpty(vertices)){
			Preconditions.checkArgument(vertices.size()==1, "Multiple nodes with uri:"+graphEntityId);
			vertex=vertices.get(0);
		}
		return vertex;
	}
	@Override
	public Node update(Node baseEntity, boolean embedded) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
