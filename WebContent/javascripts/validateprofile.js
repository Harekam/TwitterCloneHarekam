function validatepass() {

	var rep = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;

	var passold, passnew, passconfirm;
	var returnValue = true;

	passold = document.forms["formPassChange"]["editPasswordOld"].value;
	passnew = document.forms["formPassChange"]["editPasswordNew"].value;
	passconfirm = document.forms["formPassChange"]["editConfirmPasswordNew"].value;

	if ((passold == null || passold == "")
			&& (passnew == null || passnew == "")
			&& (passconfirm == null || passconfirm == "")) {

		returnValue = false;
		errorPassShow("Fields cannot be empty!");

		return returnValue;

	}

	if (passold == null || passold == "") {
		returnValue = false;
		errorPassShow("Old Password Field Cannot be empty");
		return returnValue;

	}
	if (passnew == null || passnew == "") {
		returnValue = false;
		errorPassShow("New Password Field Cannot be empty");
		return returnValue;

	} else if (!rep.test(passnew)) {
		returnValue = false;
		errorPassShow("Password should be at least one number, one lowercase, one uppercase letter and least six characters");
		return returnValue;

	}
	if (passconfirm == null || passconfirm == "") {
		returnValue = false;
		errorPassShow("Confirm Password Field Cannot be empty");
		return returnValue;

	}
	if (passnew != passconfirm) {
		returnValue = false;
		errorPassShow("New and confirm password field should be same");
		return returnValue;

	}

	/*
	 * if (returnValue != false) {
	 * 
	 * alert("succesful login!"); return returnValue; }
	 */

}
function validateeditpro() {
	var rem = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;

	var ref = /^[a-zA-Z ]+$/;

	var returnValue = true;
	var full = document.forms["formProfileUpdate"]["editFullName"].value;
	var mail = document.forms["formProfileUpdate"]["editEmail"].value;

	if ((full == null || full == "") && (mail == null || mail == "")) {

		returnValue = false;
		errorShow("Fields cannot be empty!");

		return returnValue;

	}
	if (full == null || full == "") {
		returnValue = false;
		errorShow("Full name cannot be empty!");

		// document.formsign.fullname.focus();
		return returnValue;
	} else if (!ref.test(full)) {

		returnValue = false;
		errorShow("Name should have alphabet characters only");
		return returnValue;

	}
	if (mail == null || mail == "") {
		returnValue = false;
		errorShow("Email cannot be empty!");

		// document.formsign.semail.focus();
		return returnValue;
	} else if (!rem.test(mail)) {
		returnValue = false;
		// document.formsign.semail.focus();
		errorShow("Enter Valid email");
		return returnValue;
	}

}
function errorPassShow(error) {
	document.getElementById("errorEditPassDisplay").innerHTML = "*" + error;

}
function errorShow(error) {
	document.getElementById("errorEditDisplay").innerHTML = "*" + error;
}

function validateSearch() {
	var search;
	var returnValue = true;
	search = document.forms["formSearch"]["search"].value;

	if (search == null || search == "") {

		returnValue = false;
		errorDisp("Search Field cannot be empty!", "errorSearch");

		return returnValue;
	}
}

function validateEditTweet() {

	var tweet;
	var returnValue = true;
	tweet = document.forms["formEditTweet"]["tweetEditArea"].value;
	tweet = tweet.trim();
	if (tweet == null || tweet == "") {

		returnValue = false;
		document.getElementById("erroredittweet").innerHTML = "*"
				+ "Tweet Field cannot be empty!";

		return returnValue;

	}

}
function errorDisp(error, idname) {
	document.getElementById(idname).innerHTML = "*" + error;
}
function textAreaAdjust(o) {
	o.style.height = "1px";
	o.style.height = (25 + o.scrollHeight) + "px";
}
