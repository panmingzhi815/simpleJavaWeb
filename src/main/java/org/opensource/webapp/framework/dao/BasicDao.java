package org.opensource.webapp.framework.dao;

import org.opensource.webapp.framework.domain.page.PageParam;
import org.opensource.webapp.framework.domain.page.PageResult;

public interface BasicDao<T> {

	/**
	 * 保存对象,当getId() == null时,则为新建,否则为更新
	 * @param t
	 * @return
	 */
	public T save(T t);
	
	/**
	 * 删除一个对象
	 * @param t
	 */
	public void remove(T t);
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public T get(Long id);
	
	/**
	 * 以 T 的属性为 where 查询条件
	 * @param pageParam
	 * @param t
	 * @return
	 */
	public PageResult<T> getPage(PageParam pageParam,T t);
	
}
