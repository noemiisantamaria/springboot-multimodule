package com.springboot.multimodule.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SearchCriteria {

	@Getter
	@Setter
	String key;

	@Getter
	@Setter
	SearchOperation operation;

	@Getter
	@Setter
	Object value;

	@Getter
	@Setter
	boolean orPredicate;

	public SearchCriteria(String key, SearchOperation operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria(String orPredicate, String key, SearchOperation operation, Object value) {
		super();
		this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria(String key, String operation, String prefix, String value, String suffix) {
		SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
		if (op != null) {
			if (op == SearchOperation.EQUALITY) {
				final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
				final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

				if (startWithAsterisk && endWithAsterisk) {
					op = SearchOperation.CONTAINS;
				} else if (startWithAsterisk) {
					op = SearchOperation.ENDS_WITH;
				} else if (endWithAsterisk) {
					op = SearchOperation.STARTS_WITH;
				}
			}
		}
		this.key = key;
		this.operation = op;
		this.value = value;
	}

}
