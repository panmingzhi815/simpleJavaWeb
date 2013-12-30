package org.opensource.webapp.framework.dao;

import java.util.List;

import org.opensource.webapp.framework.domain.SysRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-15
 * Time: 上午1:16
 * To change this template use File | Settings | File Templates.
 */
public interface SysRoleDao extends PagingAndSortingRepository<SysRole, Long>,
        JpaSpecificationExecutor<SysRole>{
	
    public List<SysRole> findByParentIsNull();

    public List<SysRole> findByParent(SysRole sysRole);

    @Query("SELECT COUNT(sr) FROM SysRole sr WHERE  sr.parent.id= ?1")
	public Long countByParent(Long parentId);
    
}
