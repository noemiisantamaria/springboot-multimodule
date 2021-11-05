package com.springboot.multimodule.specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.springboot.multimodule.errors.SearchParamInvalidException;
import com.springboot.multimodule.utils.SearchCriteria;
import com.springboot.multimodule.utils.SearchOperation;

public class GenericSpecificationBuilder<U> {

	private final List<SearchCriteria> params;

	public GenericSpecificationBuilder() {
		this.params = new ArrayList<>();
	}

	public final GenericSpecificationBuilder<U> with(String key, String operation, Object value, String prefix,
			String suffix) {
		return with(null, key, operation, value, prefix, suffix);
	}

	public final GenericSpecificationBuilder<U> with(String precedenceIndicator, String key, String operation,
			Object value, String prefix, String suffix) {
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
			params.add(new SearchCriteria(precedenceIndicator, key, op, value));
		}
		return this;
	}

	public Specification<U> build(Function<SearchCriteria, Specification<U>> converter) {

		if (params.size() == 0) {
			return null;
		}

		final List<Specification<U>> specs = params.stream().map(converter)
				.collect(Collectors.toCollection(ArrayList::new));

		Specification<U> result = specs.get(0);

		for (int idx = 1; idx < specs.size(); idx++) {
			result = params.get(idx).isOrPredicate() ? Specification.where(result).or(specs.get(idx))
					: Specification.where(result).and(specs.get(idx));
		}

		return result;
	}

	public Specification<U> build(Deque<?> postFixedExprStack, Function<SearchCriteria, Specification<U>> converter) {
		try {
			Deque<Specification<U>> specStack = new LinkedList<>();

			Collections.reverse((List<?>) postFixedExprStack);

			while (!postFixedExprStack.isEmpty()) {
				Object mayBeOperand = postFixedExprStack.pop();

				if (!(mayBeOperand instanceof String)) {
					specStack.push(converter.apply((SearchCriteria) mayBeOperand));
				} else {
					Specification<U> operand1 = specStack.pop();
					Specification<U> operand2 = specStack.pop();
					if (mayBeOperand.equals(SearchOperation.AND_OPERATOR))
						specStack.push(Specification.where(operand1).and(operand2));
					else if (mayBeOperand.equals(SearchOperation.OR_OPERATOR))
						specStack.push(Specification.where(operand1).or(operand2));
				}

			}
			return specStack.pop();
		} catch (Exception e) {
			throw new SearchParamInvalidException();
		}

	}

}