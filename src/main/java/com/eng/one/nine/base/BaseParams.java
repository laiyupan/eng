/**
 * 
 */
package com.eng.one.nine.base;

/**
 * @author laiyp
 * @date 2018年4月11日 
 *
 */
@SuppressWarnings("rawtypes")
public class BaseParams {

	private String column;
	
	
	private Class className;
	
	private Object value;
	
	
	public BaseParams(String column, Class className) {
		super();
		this.column = column;
		this.className = className;
	}
	

	public BaseParams(String column, Class className, Object value) {
		super();
		this.column = column;
		this.className = className;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	
	public Class getClassName() {
		return className;
	}

	public void setClassName(Class className) {
		this.className = className;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
