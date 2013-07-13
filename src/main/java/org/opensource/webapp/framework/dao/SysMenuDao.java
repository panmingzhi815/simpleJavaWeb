package org.opensource.webapp.framework.dao;

import org.opensource.webapp.framework.domain.SysMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysMenuDao extends PagingAndSortingRepository<SysMenu, Long>,
		JpaSpecificationExecutor<SysMenu> {

}
