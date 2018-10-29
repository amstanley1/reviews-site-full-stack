package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategoryContains(Category category);

	Collection<Review> findByTagsContains(Tag tag);

	/*Collection<Review> findByTagId(long tagId);*/

}
