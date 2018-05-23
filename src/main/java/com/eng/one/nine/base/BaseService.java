/**
 * 
 */
package com.eng.one.nine.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.eng.one.nine.utils.ListUtils;






/**
 * @author laiyp
 * @date 2018年4月10日 
 *
 */
public class BaseService<T> {
	
	protected Pageable pageable=null;
	
	@Autowired
	public BaseDao<T> dao;
	
	@Transactional
	public T save(T t){
		return dao.save(t);
	}
	
	@Transactional
	public Iterable<T> save(Iterable<T> t){
		return dao.save(t);
	}
	
	@Transactional
	public void delete(T t){
		dao.delete(t);
	}
	
	/**
	 * 根据条件查询(where) 带select字段
	 * @param params
	 * @return
	 */
	public Page<T> findWithParams(final List<BaseParams> params,Pageable pageable){
		return this.findWithParams(params, null, pageable);
	}
	
	/**
	 * 根据条件查询(where) 带select字段
	 * @param params
	 * @return
	 */
	public Page<T> findWithParams(final List<BaseParams> params,final List<BaseParams> select,Pageable pageable){
		Specification<T> querySpecifi = new Specification<T>(){
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate p1 = null;
				if(!ListUtils.isNullOrEmptyList(select)){
					 List<Selection<?>> selections = new ArrayList<Selection<?>>();
					 for (BaseParams baseParams : select) {
						 selections.add(root.get(baseParams.getColumn()).as(baseParams.getClassName()));
					}
					//设定select字段
				    cq.multiselect(selections);
				}
				
				for (BaseParams baseParams : params) {
					Predicate p2 = cb.equal(root.get(baseParams.getColumn()).as(baseParams.getClassName()), baseParams.getValue());
					if (p1 != null) {
						p1 = cb.and(p1, p2);
					} else {
						p1 = p2;
					}
				}
				return p1;
			}
		};
		return dao.findAll(querySpecifi,pageable);
	}
	
	public List<T> findWithParamsList(final List<BaseParams> params,Pageable pageable){
		return findWithParams(params, pageable).getContent();
	}
	
	public List<T> findWithParamsList(final List<BaseParams> params,final List<BaseParams> select,Pageable pageable){
		return findWithParams(params,select,pageable).getContent();
	}
	
	
	

}
