package com.aksh.titan.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Node implements Cloneable{
	
	private String uri;
	/**
	 * To be added as labels
	 */
	private String entityType;
	private Map<String, Object> attributes;
	private Set<String> literalSynonyms;
	private List<Relationship> relationships;
	private boolean isSoftDeleted=true;
	private boolean isSearchable=true;

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public List<Relationship> getRelationships() {
		return relationships;
	}
	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
	public boolean isSoftDeleted() {
		return isSoftDeleted;
	}
	public void setSoftDeleted(boolean isSoftDeleted) {
		this.isSoftDeleted = isSoftDeleted;
	}
	public boolean isSearchable() {
		return isSearchable;
	}
	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public Set<String> getLiteralSynonyms() {
		return literalSynonyms;
	}
	public void setLiteralSynonyms(Set<String> literalSynonyms) {
		this.literalSynonyms = literalSynonyms;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
