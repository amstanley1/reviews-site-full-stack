var addTagButton = document.querySelector(".add-tag-button");


addTagButton.addEventListener("click", function() {
    const id = this.getAttribute('data-id');
	var newTag = prompt("Please enter new tag");
	if (newTag) {
	      createNewTag(id, newTag);
	    }
	
});


//POST (create)
createNewTag = function(reviewId, newTag) {
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
            console.log(this.responseText);
        }
    };
    
    xhr.open('POST', '/api/review/' + reviewId + '/tags');
    xhr.setRequestHeader('Content-Type', 'application/json');
    const body = JSON.stringify({ name: newTag });
    xhr.send(body);
    
};