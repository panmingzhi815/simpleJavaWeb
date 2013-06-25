package org.opensource.webapp.framework.dao;

import org.opensource.webapp.framework.domain.SysUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(value="sysUserDao")
public interface SysUserDao extends PagingAndSortingRepository<SysUser, Long>,
		JpaSpecificationExecutor<SysUser> {

}
