package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategory(Category category);
	
	Collection<Review> findByCategoryId(long id);

	Collection<Review> findByTagsContains(Tag tag);

	Collection<Review> findByTagsId(long id);

}
