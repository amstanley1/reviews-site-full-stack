package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	long id;
	String name;
	
	@ManyToMany(mappedBy = "tags")
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
	
	
	
	public Tag() {
		
	}
	
	public Tag(String name) {
		this.name = name;
	}


}
