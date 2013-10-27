package org.opensource.webapp.framework.dao;

import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.domain.SysUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface SysUserDao extends PagingAndSortingRepository<SysUser, Long>,
		JpaSpecificationExecutor<SysUser> {

    @Query(value = "SELECT su.sysRoleSet FROM SysUser su WHERE su.id=:userId")
    public Collection<SysRole> findSysUserRole(@Param("userId")Long userId);

    @Query(value = "SELECT su FROM SysUser su LEFT JOIN FETCH su.sysRoleSet sus WHERE su.id=:userId")
    public SysUser findSysUserFetchRole(@Param("userId")Long userId);

}
