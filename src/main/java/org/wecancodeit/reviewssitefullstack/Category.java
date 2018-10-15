package org.wecancodeit.reviewssitefullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue
	long id;
	String name;
	
	@OneToMany
	private Collection<Review> reviews;
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public Collection<Review> getReviews() {
		return reviews;
	}
	
	public Category() {
		
	}
	
	public Category(String name, Review...reviews) {
		this.name = name;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
		
	}
	
	
}
 