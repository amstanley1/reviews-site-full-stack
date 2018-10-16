package org.wecancodeit.reviewssitefullstack;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private Review review;
	private Review anotherReview;
	
	@Mock
	private Category category;
	private Category anotherCategory;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		underTest.findOneCategory(arbitraryCategoryId, model);
		verify(model).addAttribute("categories", category);
	}
	
	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("reviews", review);
	}
	
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}
	
	
	
	
}
