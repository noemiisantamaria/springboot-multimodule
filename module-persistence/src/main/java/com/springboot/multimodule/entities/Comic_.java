package com.springboot.multimodule.entities;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Comic.class)
public class Comic_ {

	public static volatile SingularAttribute<Comic, Long> id;

	public static volatile SingularAttribute<Comic, String> collectionUri;

	public static volatile SetAttribute<Comic, Item> items;

	public static volatile SetAttribute<Comic, Superhero> superheroes;

}
