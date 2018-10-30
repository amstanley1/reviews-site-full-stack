package org.wecancodeit.reviewssitefullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String title;

	@Lob
	private String content;

	private String imageUrl;

	@ManyToOne
	private Category category;
	
	@ManyToMany
	private Collection<Tag> tags;
	
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	public Category getCategory() {
		return category;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}
	
	public Collection<Comment> getComments() {
		return comments;
	}

	public Review() {

	}

	public Review(String title, String content, String imageUrl, Category category, Tag...tags) {
		this.title = title;
		this.content = content;
		this.imageUrl = imageUrl;
		this.category = category;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}



	
}
