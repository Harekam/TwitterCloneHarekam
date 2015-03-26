function validate() {

	var rem = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
	var reu = /^\W+.$/;
	var rep = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
	var ref = /^[a-zA-Z ]+$/;
	var user, pass;
	var returnValue = true;
	user = document.forms["formsign"]["susername"].value;
	pass = document.forms["formsign"]["spassword"].value;
	full = document.forms["formsign"]["fullname"].value;
	mail = document.forms["formsign"]["semail"].value;

	if ((user == null || user == "") && (pass == null || pass == "")
			&& (full == null || full == "") && (mail == null || mail == "")) {

		returnValue = false;
		errorShow("Fields cannot be empty!");

		return returnValue;

	}

	if (user == null || user == "") {
		returnValue = false;
		errorShow("Username cannot be empty!");

		document.formsign.susername.focus();
		return returnValue;
	} else if (reu.test(user)) {
		returnValue = false;
		errorShow("Username must contain only letters, numbers and underscores!");
		document.formsign.susername.value = "";/* reset the field */
		document.formsign.susername.focus();
		return returnValue;
	} else if (user.length < 6 || user.length > 20) {
		returnValue = false;
		errorShow("Username length should be between 6 to 20!");
		document.formsign.susername.value = "";
		document.formsign.susername.focus();
		return returnValue;
	}

	if (pass == null || pass == "") {
		returnValue = false;
		errorShow("Password Cannot be empty");
		return returnValue;

	} else if (!rep.test(pass)) {
		returnValue = false;
		errorShow("Password should be at least one number, one lowercase, one uppercase letter and least six characters");
		return returnValue;

	}
	if (user == pass) {
		returnValue = false;
		errorShow("Password too obvious!");
		return returnValue;

	}
	if (full == null || full == "") {
		returnValue = false;
		errorShow("Full name cannot be empty!");

		document.formsign.fullname.focus();
		return returnValue;
	} else if (!ref.test(full)) {

		returnValue = false;
		errorShow("Name should have alphabet characters only");
		return returnValue;

	}
	if (mail == null || mail == "") {
		returnValue = false;
		errorShow("Email cannot be empty!");

		document.formsign.semail.focus();
		return returnValue;
	} else if (!rem.test(mail)) {
		returnValue = false;
		document.formsign.semail.focus();
		errorShow("Enter Valid email");
		return returnValue;
	}
	/*
	 * if (returnValue != false) {
	 * 
	 * alert("succesful login!"); return returnValue; }
	 */

}
function errorShow(error) {
	document.getElementById("errorDisplay").innerHTML = "*" + error;
}
