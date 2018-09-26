package com.edu.service.user.common.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.edu.facade.user.common.entity.BaseEntity;
import com.edu.facade.user.common.exceptions.BizException;
import com.edu.facade.user.common.page.PageBean;
import com.edu.facade.user.common.page.PageParam;
@Component
public abstract class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport implements BaseDao<T> {

	protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);
	
	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_BATCH_UPDATE = "batchUpdate";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_PAGE_COUNT = "listPageCount";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public String getStatement(String sqlId) {
		String name = this.getClass().getName();
	    StringBuffer sb = new StringBuffer();
	    sb.append(name).append(".").append(sqlId);
	    return sb.toString();
	}
	
	@Override
	public long insert(T entity) {
		long result = sqlSessionTemplate.insert(getStatement(SQL_INSERT), entity);
		if(result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,insert返回0.{%s}", getStatement(SQL_INSERT));
		}
		result = entity.getId();
		return result;
	}

	@Override
	public long insert(List<T> list) {
		if(list == null ||list.size() < 1) {
			return 0;
		}
		long result = sqlSessionTemplate.insert(getStatement(SQL_BATCH_INSERT),list);
		if(result < 1) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,insert返回0.{%s}", getStatement(SQL_BATCH_INSERT));

		}
		return result;
	}

	@Override
	public int update(T entity) {
		int result = sqlSessionTemplate.update(getStatement(SQL_UPDATE),entity);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
		return result;
	}

	@Override
	public int update(List<T> list) {

		if (list == null || list.size() <= 0) {
			return 0;
		}
		int result = sqlSessionTemplate.update(getStatement(SQL_BATCH_UPDATE), list);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
		return result;
	}

	@Override
	public T getById(Long id) {
		return sqlSessionTemplate.selectOne(getStatement(SQL_GET_BY_ID), id);
	}

	@Override
	public int deleteById(long id) {
		return (int) sqlSessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);

	}

	@Override
	public PageBean list(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}

		// 根据页面传来的分页参数构造SQL分页参数
		paramMap.put("pageFirst", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("pageSize", pageParam.getNumPerPage());
		paramMap.put("startRowNum", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("endRowNum", pageParam.getPageNum() * pageParam.getNumPerPage());

		// 统计总记录数
		Long count = sqlSessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);

		// 获取分页数据集
		List<Object> list = sqlSessionTemplate.selectList(getStatement(SQL_LIST_PAGE), paramMap);

		Object isCount = paramMap.get("isCount"); // 是否统计当前分页条件下的数据：1:是，其他为否
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = sqlSessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
			return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list, countResultMap);
		} else {
			// 构造分页对象
			return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
		}
	}

	@Override
	public List<T> listBy(Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectList(getStatement(SQL_LIST_BY), paramMap);
	}

	@Override
	public SqlSessionTemplate getSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Override
	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}

	@Override
	public T getBy(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.isEmpty()) {
			return null;
		}
		return sqlSessionTemplate.selectOne(getStatement(SQL_LIST_BY), paramMap);
	}

	

}
