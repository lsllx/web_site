package com.website.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.website.admin.dao.impl.AdminDao;
import com.website.admin.service.IAdminService;
import com.website.entities.AdminEntity;

@Service
public class AdminServiceImpl implements IAdminService {

	@Resource
	private AdminDao dao;

	public AdminEntity getAdminUserById(int id) {
		return dao.getBeanById(id);
	}

}
