function validate() {
	var user, pass;
	var returnValue = true;
	user = document.forms["formlog"]["username"].value;
	pass = document.forms["formlog"]["inpassword"].value;

	if ((user == null || user == "") && (pass == null || pass == "")) {

		returnValue = false;
		errorShow("Fields cannot be empty!");

		return returnValue;

	}

	if (user == null || user == "") {
		returnValue = false;
		errorShow("Username cannot be empty!");

		document.formlog.username.focus();
		return returnValue;
	}
	if (pass == null || pass == "") {
		returnValue = false;
		errorShow("Password Cannot be empty");
		return returnValue;

	}

	/*
	 * if (returnValue != false) {
	 * 
	 * alert("succesful login!"); return returnValue;
	 *  }
	 */

}
function errorShow(error) {
	document.getElementById("errorDisplay").innerHTML = "*" + error;
}
