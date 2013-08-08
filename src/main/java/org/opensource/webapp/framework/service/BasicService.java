package org.opensource.webapp.framework.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.opensource.webapp.framework.domain.page.PageParam;
import org.opensource.webapp.framework.domain.page.PageResult;
import org.opensource.webapp.framework.domain.page.PageParam.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

public class BasicService<T> {

	public PageRequest buildPageRequest(PageParam pageParam) {
		List<Order> orders = new ArrayList<Sort.Order>();
		Sort sort = null;
		if (pageParam.getSortMap() != null) {
			Set<String> keySet = pageParam.getSortMap().keySet();
			for (String key : keySet) {
				SortType sortType = pageParam.getSortMap().get(key);
				Order order = null;
				if (sortType == SortType.ASC)
					order = new Order(Direction.ASC, key);
				else
					order = new Order(Direction.DESC, key);
				orders.add(order);
			}
			if (orders.size() > 0)
				sort = new Sort(orders);
		}
		return new PageRequest(pageParam.getStart(), pageParam.getLimit(),
				sort);
	}

	public PageResult<T> buildPageResult(Page<T> page) {
		PageResult<T> pageResult = new PageResult<T>();
		pageResult.setRows(page.getContent());
		pageResult.setTotal(page.getTotalElements());
		return pageResult;
	}

	public Specification<T> buildSpecification(T t) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.conjunction();
			}
		};
	}

}
