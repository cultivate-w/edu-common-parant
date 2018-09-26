package com.edu.service.user.mapper;

import java.util.List;
import java.util.Map;

import com.edu.facade.user.entity.PmsUser;

public interface PmsUserMapper {
	
	 void insert(PmsUser pmsUser);
	
	 
	 void update(PmsUser pmsUser);
	 
	 PmsUser getById(Long id);
	 
	 PmsUser findByUserNo(String userNo);
	 
	 void deleteById(Long id);
	 
	 List<PmsUser> listAll();
	 
	 
	 
	 
	 Long listPageCount(Map<String, Object> map);
	 

}
