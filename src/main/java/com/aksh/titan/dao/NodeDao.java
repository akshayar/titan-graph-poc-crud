package com.aksh.titan.dao;

import java.util.List;
import java.util.Map;

import com.aksh.titan.dto.Node;

public interface NodeDao {
	
	Node create(Node baseEntity,boolean embedded);

	Node update(Node baseEntity,boolean embedded);

	Node readByURI(String uri);
	
	List<Node> query(Map<String, Object> properties);
	
	void delete(String uri);
	
	
}
