(function() {

	var addTagButton = document.querySelector(".add-tag-button");
	var deleteTagButtons = document.querySelectorAll(".delete");

	addDeleteListener = function(deleteTagButton) {
		deleteTagButton.addEventListener("click", function() {
			confirm("Are you sure you want to delete this tag?");
			const removeTagId = this.getAttribute('data-id');
			const reviewId = document.querySelector(".add-tag-button").getAttribute('data-id');
			deleteTag(removeTagId, reviewId, this);
		});
	};

	for (let i = 0; i < deleteTagButtons.length; i++) {
		addDeleteListener(deleteTagButtons[i]);
	};

	addTagButton.addEventListener("click", function() {
		const reviewId = this.getAttribute('data-id');
		var newTag = prompt("Please enter new tag");
		if (newTag) {
			createNewTag(newTag, reviewId);
		}
	});

	//PUT (update)
	createNewTag = function(newTag, reviewId) {
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (this.status === 200 && this.readyState === 4) {
				const response = this.responseText;
				if (response) {
					const newTag = JSON.parse(response);
					addTagToPage(newTag);
				}
			}
		};
		xhr.open('PUT', '/api/reviews/' + reviewId + '/tags/' + newTag);
		xhr.send();
	};

	//PUT (update)
	deleteTag = function(removeTag, reviewId, deleteTagElement) {
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (this.status === 200 && this.readyState === 4) {
				removeTagFromPage(deleteTagElement);
			}
		};
		xhr.open('PUT', '/api/reviews/' + reviewId + '/tags/' + removeTag
				+ '/remove');
		xhr.send();

	};

	addTagToPage = function(newTag) {
		var tagList = document.querySelector('#tags-container');
		var tagListItem = document.createElement('li');
		tagListItem.setAttribute('class', 'show-tags');
		var newTagAnchor = document.createElement('a');
		var deleteTagAnchor = document.createElement('a');
		var deleteTagIcon = document.createElement('i');
		newTagAnchor.setAttribute('class', 'tag');
		newTagAnchor.setAttribute('href', '/tag?id=' + newTag.id);
		newTagAnchor.textContent = newTag.name;
		newTagAnchor.setAttribute('data-id', newTag.id);
		deleteTagAnchor.setAttribute('class', 'delete');
		deleteTagAnchor.setAttribute('data-id', newTag.id);
		deleteTagAnchor.setAttribute('href', 'javascript:void(0)');
		deleteTagAnchor.innerHTML = '<i class="fa fa-trash" aria-hidden="true"></i>';
		tagList.appendChild(tagListItem);
		tagListItem.appendChild(newTagAnchor);
		tagListItem.appendChild(deleteTagAnchor);
		addDeleteListener(deleteTagAnchor);
	}

	removeTagFromPage = function(deleteTagElement) {
		deleteTagElement.parentNode.remove();
	}
})();
