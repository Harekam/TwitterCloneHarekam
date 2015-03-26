function textAreaAdjust(o) {
	o.style.height = "1px";
	o.style.height = (25 + o.scrollHeight) + "px";
}
function validateTweet() {
	var tweet;
	var returnValue = true;
	tweet = document.forms["formHomeUpdate"]["tweetArea"].value;
	tweet = tweet.trim();
	if (tweet == null || tweet == "") {

		returnValue = false;
		errorShow("Tweet Field cannot be empty!", "errorDisplay");

		return returnValue;

	}

}

function validateSearch() {
	var search;
	var returnValue = true;
	search = document.forms["formSearch"]["search"].value;

	if (search == null || search == "") {

		returnValue = false;
		errorShow("Search Field cannot be empty!", "errorSearch");

		return returnValue;
	}
}
function errorShow(error, idname) {
	document.getElementById(idname).innerHTML = "*" + error;
}