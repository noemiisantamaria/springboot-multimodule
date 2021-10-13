package com.springboot.multimodule.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ITEM")
@NoArgsConstructor
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", updatable = false, nullable = false)
	@Getter @Setter
	private Long id;
	
	@Column(name="NAME")
	@NotNull
	@Getter @Setter
	private String name;
	
	@Column(name="RESOURCEURI")
	@NotNull
	@Getter @Setter
	private String resourceUri;
	
	@ManyToMany(mappedBy = "items")
	@Getter @Setter
	private Set<Comic> comics;
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Item [id=")
				.append(id)
				.append(", name=").append(name)
				.append(", resourceUri=").append(resourceUri)
				.append("]");
		return builder.toString();
	}
	
}
