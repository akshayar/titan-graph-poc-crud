package com.aksh.titan.dao;

import com.aksh.titan.dto.Node;
import com.aksh.titan.dto.Relationship;

public interface RelationshipDao {
	
	String create(Relationship baseRelationship);

	Iterable<Relationship> getRelationFrom(String fromId,String label);

	Relationship getRelationBetween(String fromId,String toId);
	
	Node delete(Relationship baseEntity);
	
}
