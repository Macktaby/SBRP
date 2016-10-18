package com.models;

public class Project {

	private int projectID;

	private String name;
	private String techReflection;
	private String mngReflection;
	private String bzReflection;

	private int parentID;

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTechReflection() {
		return techReflection;
	}

	public void setTechReflection(String techReflection) {
		this.techReflection = techReflection;
	}

	public String getMngReflection() {
		return mngReflection;
	}

	public void setMngReflection(String mngReflection) {
		this.mngReflection = mngReflection;
	}

	public String getBzReflection() {
		return bzReflection;
	}

	public void setBzReflection(String bzReflection) {
		this.bzReflection = bzReflection;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

}
