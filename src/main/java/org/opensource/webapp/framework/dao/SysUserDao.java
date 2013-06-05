package org.opensource.webapp.framework.dao;

import org.opensource.webapp.framework.domain.SysUser;
import org.springframework.data.repository.CrudRepository;

public interface SysUserDao extends CrudRepository<SysUser, Long>{

}
