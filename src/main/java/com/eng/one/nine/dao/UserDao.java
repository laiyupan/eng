package com.eng.one.nine.dao;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eng.one.nine.base.BaseDao;
import com.eng.one.nine.entity.User;

//相当于dao层
@Repository
@Qualifier(value="userDao")
public interface UserDao  extends BaseDao<User>{

}
