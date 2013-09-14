package org.opensource.webapp.framework.dao;

import org.opensource.webapp.framework.domain.SysMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SysMenuDao extends PagingAndSortingRepository<SysMenu, Long>,
		JpaSpecificationExecutor<SysMenu> {

    public List<SysMenu> findByParentIsNull();

    public List<SysMenu> findByParent(SysMenu sysMenu);

    @Query("SELECT COUNT(sm) FROM SysMenu sm WHERE  sm.parent.id= ?1")
    public Long countByParent(Long parentId);

}
