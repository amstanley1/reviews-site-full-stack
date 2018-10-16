package org.wecancodeit.reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=3")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews", is(review)));
	}
	

	

}
