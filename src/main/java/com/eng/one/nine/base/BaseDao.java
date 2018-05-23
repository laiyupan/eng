/**
 * 
 */
package com.eng.one.nine.base;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


/**
 * @author laiyp
 * @date 2018年4月9日 
 *
 */
public interface BaseDao<T> extends CrudRepository<T, Integer>,JpaSpecificationExecutor<T>{

}
