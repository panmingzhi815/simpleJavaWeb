package org.opensource.webapp.framework.service;

import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-15
 * Time: 上午1:26
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractService<T> {

    public Long save(T t);

    public boolean remove(Long id);

    public T getById(Long id);

    public PageResult<T> getPageList(PageParam pageParam, SearchFilter searchFilter);
}
