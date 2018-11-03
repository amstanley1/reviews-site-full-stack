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
        	if(response) {
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
//        	const removeTag = JSON.parse(this.responseText);
//            addTagToPage(newTag);
        	removeTagFromPage(deleteTagElement);
        	
            
        }
    };
    
    xhr.open('PUT', '/api/reviews/' + reviewId + '/tags/' + removeTag + '/remove');
    xhr.send();
    
};

addTagToPage = function(newTag) {
	var tagList = document.querySelector('#tags-container');
	var tagListItem = document.createElement('li');
	tagListItem.setAttribute('id', 'show-tags');
	var newTagAnchor = document.createElement('a');
	var deleteTagSpan = document.createElement('span');
	newTagAnchor.setAttribute('class', 'tag');
	newTagAnchor.textContent = newTag.name;
	deleteTagSpan.textContent = 'DELETE';
	deleteTagSpan.setAttribute('class', 'delete');
	deleteTagSpan.setAttribute('data-id', newTag.id);
	tagList.appendChild(tagListItem);
	tagListItem.appendChild(newTagAnchor);
	tagListItem.appendChild(deleteTagSpan);
	addDeleteListener(deleteTagSpan);
	
}

removeTagFromPage = function(deleteTagElement) {
	deleteTagElement.parentNode.remove();
}
