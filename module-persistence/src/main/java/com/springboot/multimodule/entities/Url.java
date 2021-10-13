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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="URL")
@NoArgsConstructor
public class Url implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", updatable = false, nullable = false)
	@Getter @Setter
	private Long id;
	
	@Column(name="TYPE")
	@NotNull
	@Getter @Setter
	private String type;
	
	@Column(name="URL")
	@Getter @Setter
	private String url;
	
	@JsonIgnoreProperties({"urls"})
	@ManyToMany(mappedBy = "urls")
	@Getter @Setter
	private Set<Superhero> superheroes;
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Comic [id=")
				.append(id)
				.append(", type=").append(type)
				.append(", url=").append(url)
				.append("]");
		return builder.toString();
	}
	
}
