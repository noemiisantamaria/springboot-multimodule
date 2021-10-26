package com.springboot.multimodule.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="SUPERHERO")
@NoArgsConstructor
public class Superhero implements Serializable {

	private static final long serialVersionUID = 1L;

	public Superhero(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", updatable = false, nullable = false)
	@Getter @Setter
	private Long id;
	
	@Column(name="NAME")
	@NotNull
	@Getter @Setter
	private String name;
	
	@Column(name="DESCRIPTION")
	@Getter @Setter
	private String description;
	
	@Column(name="THUMBNAIL")
	@Getter @Setter
	private String thumbnail;
	
	@JsonIgnoreProperties({"superheroes"})
	@ManyToMany
	@JoinTable(
	  name = "SUPERHEROCOMIC", 
	  joinColumns = @JoinColumn(name = "IDSUPERHERO"), 
	  inverseJoinColumns = @JoinColumn(name = "IDCOMIC"))
	@Getter @Setter
	private Set<Comic> comics;
	
	@JsonIgnoreProperties({"superheroes"})
	@ManyToMany
	@JoinTable(
	  name = "SUPERHEROURL", 
	  joinColumns = @JoinColumn(name = "IDSUPERHERO"), 
	  inverseJoinColumns = @JoinColumn(name = "IDURL"))
	@Getter @Setter
	private Set<Url> urls;
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Superhero [id=")
				.append(id)
				.append(", name=").append(name)
				.append("]");
		return builder.toString();
	}
	
}
