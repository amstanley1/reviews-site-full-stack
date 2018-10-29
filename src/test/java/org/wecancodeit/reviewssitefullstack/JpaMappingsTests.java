package org.wecancodeit.reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JpaMappingsTests {

	@Resource
	EntityManager entityManager;
	
	@Resource
	ReviewRepository reviewRepo;
	
	@Resource
	CategoryRepository categoryRepo;
	
	@Resource
	TagRepository tagRepo;
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("review", "content", "imageUrl", null));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		Review reviewResult = result.get();
		assertThat(reviewResult.getTitle(), is("review"));
	}
	
	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("review", "content", "imageUrl", null));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		Category categoryResult = result.get();
		assertThat(categoryResult.getName(), is("category"));
		
	}
	
	@Test
	public void shouldEstablishCategoryToReviewsRelationship() {
		
		Category category = categoryRepo.save(new Category("category"));
		/*reviewRepo.save(review);
		reviewRepo.save(anotherReview);*/
		Review review = reviewRepo.save(new Review("review", "content", "imageUrl", category));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", "imageUrl", category));
		
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		Category categoryResult = result.get();
		assertThat(categoryResult.getReviews(), containsInAnyOrder(review, anotherReview));
	}

	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = tagRepo.save(new Tag("tag"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		Tag tagResult = result.get();
		assertThat(tagResult.getName(), is("tag"));
	}
	
	
	@Test
	public void shouldEstablishTagsToReviewsRelationships() {
		Tag tag = new Tag("tag");
		tag = tagRepo.save(tag);
		Tag tag2 = new Tag("tag2");
		tag2 = tagRepo.save(tag2);
		
		Review review = reviewRepo.save(new Review("review", "content", "imageUrl", null, tag, tag2));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", "imageUrl", null, tag, tag2));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		Review reviewResult = result.get();
		
		assertThat(review.getTags(), containsInAnyOrder(tag, tag2));
	}

	@Test
	public void shouldFindReviewsPerTag() {
		//topic is not the owner so we must create these first
		Tag tag = new Tag("tag");
		tag = tagRepo.save(tag);
		Tag tag2 = new Tag("tag2");
		tag2 = tagRepo.save(tag2);
		
		Review review = reviewRepo.save(new Review("review", "content", "imageUrl", null, tag, tag2));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", "imageUrl", null, tag, tag2));
		
		
		entityManager.flush();
		entityManager.clear();
			
				
				Collection<Review> result = reviewRepo.findByTagsContains(tag);
				
				assertThat(result, containsInAnyOrder(review, anotherReview));
	}
	
	/*@Test
	public void shouldFindCoursesForTopicId() {
		//topic is not the owner so we must create these first
		Topic java = topicRepo.save(new Topic("Java"));
		Topic ruby = topicRepo.save(new Topic("Ruby"));
		
		Course course1 = new Course("OO Languages", "description", java, ruby);
		Course course2 = new Course("Java101", "description",java);
		Course course3 = new Course("Advanced OO Languages", "description", java, ruby);
		course1 = courseRepo.save(course1);
		course2 = courseRepo.save(course2);
		course3 = courseRepo.save(course3);
		long topicId = java.getId();
	
		entityManager.flush();
		entityManager.clear();
		
		Collection<Course> result = courseRepo.findByTopicsId(topicId);
		
		assertThat(result, containsInAnyOrder(course1, course2, course3));
	}*/
	
	
}
	


