package com.edu.service.user.common.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.edu.facade.user.common.page.PageBean;
import com.edu.facade.user.common.page.PageParam;

public interface BaseDao <T>{
	
	long insert(T entity);
	
	
	long insert(List<T> list);
	
	
	int update(T entity);
	
	int update(List<T> list);
	
	T getById(Long id);
	
	int deleteById(long id);
	
	public PageBean list(PageParam pageParam,Map<String, Object> paramMap);
	
	public List<T> listBy(Map<String, Object> paramMap);
	
	public T getBy(Map<String, Object> paramMap);
	
	public SqlSessionTemplate getSessionTemplate();
	
	public SqlSession getSqlSession();
	

}
