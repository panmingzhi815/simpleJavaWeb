package org.opensource.webapp.framework.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.PageParam.SortType;
import org.opensource.webapp.framework.page.SearchFilter;
import org.opensource.webapp.framework.page.SearchOperator;
import org.opensource.webapp.framework.util.StrUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

public class PageServiceImpl<T> {

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

	@SuppressWarnings({"unchecked","rawtypes"})
	public Specification<T> buildSpecification(final SearchFilter searchFilter) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<SearchOperator> searchOperatorList = searchFilter.getSearchOperatorList();
				if (StrUtil.isNotEmpty(searchOperatorList)) {

					List<Predicate> predicates = new ArrayList<Predicate>();
					for (SearchOperator searchOperator : searchOperatorList) {
						// nested path translate, 如User的名为"menu.name"的filedName, 转换为User.menu.name属性
						String[] names = StringUtils.split(searchOperator.getFieldName(),"-");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						switch (searchOperator.getSearchType()) {
						case eq:
							predicates.add(builder.equal(expression, searchOperator.getFieldValue()));
							break;
						case start:
							predicates.add(builder.like(expression, searchOperator.getFieldValue() + "%"));
							break;
						case end:
							predicates.add(builder.like(expression, "%" + searchOperator.getFieldValue()));
							break;
						case any:
							predicates.add(builder.like(expression, "%" + searchOperator.getFieldValue() + "%"));
							break;
						case gt:
							predicates.add(builder.greaterThan(expression, (Comparable) searchOperator.getFieldValue()));
							break;
						case lt:
							predicates.add(builder.lessThan(expression, (Comparable) searchOperator.getFieldValue()));
							break;
						case gte:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) searchOperator.getFieldValue()));
							break;
						case lte:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) searchOperator.getFieldValue()));
							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (predicates.size() > 0) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}

}
