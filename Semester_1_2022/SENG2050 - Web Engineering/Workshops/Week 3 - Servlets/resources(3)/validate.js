function validate(){
	var firstName = document.getElementById("firstName"); 	
	var lastName = document.getElementById("lastName"); 
	var resultStatus = true;
	var messageStr = "The following errors were detected:\n"; 

	if(!firstName){
		resultStatus = false;
		messageStr += "- Could not find input with id 'firstName'\n";
	}
	if(!lastName){
		resultStatus = false;
		messageStr += "- Could not find input with id 'lastName'\n";
	}
	if(resultStatus){
		var firstNameValue = firstName.value;
		var lastNameValue = lastName.value;

		if(firstNameValue == ""){
			resultStatus = false;
			messageStr += "First name is blank\n";
		}
		if(lastNameValue == ""){
			resultStatus = false;
			messageStr += "Last name is blank\n";
		}
	}
	if (!resultStatus){
		alert(messageStr);
	}
	return resultStatus;	
}