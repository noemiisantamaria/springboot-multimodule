package com.springboot.multimodule.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.springboot.multimodule.utils.SearchCriteria;

public class GenericSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = 1L;

	private SearchCriteria criteria;

	public GenericSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		switch (criteria.getOperation()) {
		case EQUALITY:
			return builder.equal(getPath(root), criteria.getValue());
		case NEGATION:
			return builder.notEqual(getPath(root), criteria.getValue());
		case GREATER_THAN:
			return builder.greaterThan(getPath(root).as(String.class), criteria.getValue().toString());
		case LESS_THAN:
			return builder.lessThan(getPath(root).as(String.class), criteria.getValue().toString());
		case LIKE:
			return builder.like(getPath(root).as(String.class), criteria.getValue().toString());
		case STARTS_WITH:
			return builder.like(getPath(root).as(String.class), criteria.getValue() + "%");
		case ENDS_WITH:
			return builder.like(getPath(root).as(String.class), "%" + criteria.getValue());
		case CONTAINS:
			return builder.like(getPath(root).as(String.class), "%" + criteria.getValue() + "%");
		default:
			return null;
		}
	}

	private Path<?> getPath(Root<T> root) {

		if (!criteria.getKey().contains(".")) {
			return root.get(criteria.getKey());
		}

		String[] levels = criteria.getKey().split("\\.");
		Path<?> path = root;
		for (String level : levels) {
			path = path.get(level);
		}

		return path;
	}

}