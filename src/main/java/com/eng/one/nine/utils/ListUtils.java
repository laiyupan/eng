package com.eng.one.nine.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



public class ListUtils {
	/**
	 * 取List<T> beginindex-endinex的值
	 * params:起始下标：beginindex；
	 * 			结束下标：endindex;
	 * 			List<T>:list;
	 * */
	
	public  static <T> List<T> getListLimit(int beginindex,int endindex,List<T> list){
		List<T> result=new ArrayList<T>();
		if(endindex>=list.size())endindex=list.size();
		for (int i = beginindex; i < endindex; i++) {
			result.add(list.get(i));
		}
		return result;
	}
	
	/**
	 * Iterator转list
	 * @param iter
	 * @return
	 */
	public static <T> List<T> copyIterator(Iterator<T> iter) {  
	    List<T> copy = new ArrayList<T>();  
	    while (iter.hasNext())  
	        copy.add(iter.next());  
	    return copy;  
	} 
	
	/**
	 * Iterable转list
	 * @param iter
	 * @return
	 */
	public static <T> List<T> copyIterable(Iterable<T> iter) {  
	    List<T> copy = new ArrayList<T>();  
	    for (T t : copy) {
	    	copy.add(t);  
		}
	    return copy;  
	} 
	
	/**
	 * List<T>是否为空
	 * return true:为空
	 * 		 false:不为空
	 * */
	public static <T> boolean isNullOrEmptyList(List<T> list){
		if(list==null || list.size()==0){
			return true;
		}
		return false;
	}
	
	
	public static <T> String ToStringList (Set<T> ipv4s,String prefix, String suffix, String separator){
		Iterator<T> it = ipv4s.iterator();
		if (!it.hasNext())
			return prefix + suffix;

		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		for (;;) {
			T e = it.next();
			sb.append(e);
			if (!it.hasNext())
				return sb.append(suffix).toString();
			sb.append(separator);
		}
	} 
	
	
	
	
	
}
