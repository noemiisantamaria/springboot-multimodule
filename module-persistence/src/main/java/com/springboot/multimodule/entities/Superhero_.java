package com.springboot.multimodule.entities;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Superhero.class)
public class Superhero_ {

	public static volatile SingularAttribute<Superhero, Long> id;

	public static volatile SingularAttribute<Superhero, String> name;

	public static volatile SingularAttribute<Superhero, String> description;

	public static volatile SingularAttribute<Superhero, String> thumbnail;

	public static volatile SetAttribute<Superhero, Comic> comics;

	public static volatile SetAttribute<Superhero, Url> urls;

}
