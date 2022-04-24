function validate(){
    let userName = document.getElementById("userName"); 	
    let msgTitle = document.getElementById("msgTitle"); 
    let msgContent = document.getElementById("msgContent"); 
    let resultStatus = true;
    let messageStr = "The following errors were detected:\n"; 

    if(!userName){
        resultStatus = false;
        messageStr += "- Input with id 'userName' missing.\n";
    }
    if(!msgTitle){
        resultStatus = false;
        messageStr += "- Input with id 'msgTitle' missing.\n";
    }
    if(!msgContent){
        resultStatus = false;
        messageStr += "- Input with id 'msgContent' missing.\n";
    }
    if(resultStatus){
        let userNameValue = userName.value;
        let msgTitleValue = msgTitle.value ?? "anonymous";
        let msgContentValue = msgContent.value;
        
        if (userNameValue == ""){
            userName.value = "Anonymous";
        }
        if (msgTitleValue == ""){
            resultStatus = false;
            messageStr += " - Title is missing\n";
        }
        if (msgContentValue == "" || msgContentValue.length < 5 || msgContentValue.length > 50){
            resultStatus = false;
            messageStr += " - Content must contain 5 to 50 characters.";
        }
    }
    if (!resultStatus){
        alert(messageStr);
    }
    return resultStatus;	
}