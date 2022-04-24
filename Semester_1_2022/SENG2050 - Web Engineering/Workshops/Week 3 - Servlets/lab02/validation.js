function validate() {
    var firstName = document.getElementById("firstName")
    var lastName = document.getElementById("lastName")

    var resultStatus = true;
    var messageString = "The following errors were detected:\n"

    if (firstName == null || firstName.value == "") {
        resultStatus = false
        messageString += "- First name is empty or blank\n"
    }

    if (lastName == null || lastName.value == "") {
        resultStatus = false
        messageString += "- Last name is empty or blank\n"
    }

    if (!resultStatus) {
        alert(messageString)
    }

    return resultStatus
}