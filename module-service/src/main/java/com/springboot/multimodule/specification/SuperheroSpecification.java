package com.springboot.multimodule.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.springboot.multimodule.entities.Comic;
import com.springboot.multimodule.entities.Comic_;
import com.springboot.multimodule.entities.Superhero;
import com.springboot.multimodule.entities.Superhero_;

public class SuperheroSpecification {

	public static Specification<Superhero> havingComicsIdSpec(List<Integer> comics) {
		return new Specification<Superhero>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Superhero> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Join<Superhero, Comic> joinComic_Comic = root.join(Superhero_.comics);
				return criteriaBuilder
						.and(joinComic_Comic.get(Comic_.id).in(comics));
			}

		};
	}

}