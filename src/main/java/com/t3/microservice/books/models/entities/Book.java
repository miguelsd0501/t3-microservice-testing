/**
 * 
 */
package com.t3.microservice.books.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author santiago
 *
 */
@Entity
@Table(name = "books")
public class Book implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false)
	@Size(min=1, max=100, message="el tamaño tiene que estár entre 1 y 100") // for api
	private String title;
	
	@Size(max=200, message="el tamaño no debe rebasar 200 caracteres")
	private String description;
	
	@Column(nullable = false)
	@Min(value = 0L, message = "The value must be positive")
	private Integer price;
	
	@Column(name = "author_id")
	private Long authorId;

	@Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
	
	
	public Book() {
		
	}
	
	public Book(Long id, @Size(min = 1, max = 100, message = "el tamaño tiene que estár entre 1 y 100") String title,
			@Size(max = 200, message = "el tamaño no debe rebasar 200 caracteres") String description,
			@Min(value = 0, message = "The value must be positive") Integer price, Long authorId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.authorId = authorId;
	}
	
	public Book(@Size(min = 1, max = 100, message = "el tamaño tiene que estár entre 1 y 100") String title,
			@Size(max = 200, message = "el tamaño no debe rebasar 200 caracteres") String description,
			@Min(value = 0, message = "The value must be positive") Integer price, Long authorId) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.authorId = authorId;
	}

	@PrePersist // funcion EJECUTADA antes de realizar la persistencia de datos
	public void prePersist() {
	    this.createdAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
	
	private static final long serialVersionUID = 1L;
}
