var addTagButton = document.querySelector("#add-tag-button");

addTagButton.addEventListener("click", function() {
	var tagInput = document.createElement("INPUT");
	var addTagDiv = document.querySelector("#add-tag");
	tagInput.setAttribute("type", "text");
	tagInput.setAttribute("id", "tag-input");
	addTagDiv.appendChild(tagInput);
	
})