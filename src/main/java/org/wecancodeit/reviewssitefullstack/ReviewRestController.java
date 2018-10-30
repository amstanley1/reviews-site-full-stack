package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReviewRestController {
	
	@Resource
	ReviewRepository reviewRepo;
	
	@Resource
	TagRepository tagRepo;
	
	@GetMapping("/api/review/{id}/tags")
	public Iterable<Tag> getAllTags(@PathVariable Long id) {
		Optional<Review> result = reviewRepo.findById(id);
		Review reviewResult = result.get();
		Collection<Tag> tags = reviewResult.getTags();
		return tags;
	}
	
	@PostMapping("/api/review/{id}/tags")
	public Tag createTag(@RequestBody Tag newTag) {
		return tagRepo.save(newTag);
	}
	

}
