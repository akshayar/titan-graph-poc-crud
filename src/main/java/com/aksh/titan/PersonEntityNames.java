package com.aksh.titan;

public interface PersonEntityNames {
	public static final String LABEL_PERSON = "person";
	public static String getLabel(String keyName){
		return LABEL_PERSON+"_"+keyName;
	}
	public static final String PERSON_EMAILID_PROPERTY = getLabel("emailId");
	public static final String PERSON_NAME_PROPERTY = getLabel("name");
	public static final String PERSON_ISINTERNAL_PROPERTY = getLabel("isInternal");
	public static final String PERSON_DEPARTMENT=getLabel("department");
	public static final String PERSON_AGE=getLabel("age");
	public static final String PERSON_SALARY=getLabel("salary");

}
