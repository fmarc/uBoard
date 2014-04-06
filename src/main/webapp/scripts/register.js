//------------------------------------------------------
//      JavaScript File used to Handle events in
//      register.jsp.
//-------------------------------------------------------



//This statement will run as soon as the home.jsp page
//loads all assets and is ready for viewing.
$(document).ready(function() {
    
    //--------------------Event Handlers----------------------
    //      Below are all the events that run when the
    //      user interacts with the page.
    
    
    
    //********************************************************
    //Handles the submit handler for the form when the user is
    //registering for the application
    
    $('#register').submit(function(e) {
        e.preventDefault();
        
        var username    = $('#u-username').val().trim();
        var name        = $('#u-name').val().trim();
        var email       = $('#u-email').val().trim();
        var password    = $('#u-password').val().trim();
        
        if(username.indexOf(" ") !== -1 || email.indexOf(" ") !== -1) {
            alert("Username or Email cannot contain spaces!");
            return false;
        }
            
        var json = {page: 'register', method: 'register', username: username, name: name, email: email, password: password};
        
        $.ajax({
            type: "POST",
            url: '/controller',
            data: json,
            dataType: 'json',
            success: function( data ) {
                
                $('#u-username, #u-email').css("border-color", "whitesmoke");
                $('#u-username, #u-email').css("background-color", "whitesmoke");
                $('#u-username, #u-email').css("color", "black");
                
                if(data === 2){
                    $('#u-username').css("border-color", "#DE0042");
                    $('#u-username').css("background-color", "#DE0042");
                    $('#u-username').css("color", "whitesmoke");
                } else if (data === 3){
                    $('#u-email').css("border-color", "#DE0042");
                    $('#u-email').css("background-color", "#DE0042");
                    $('#u-email').css("color", "whitesmoke");
                } else {
                    window.location = "";
                }
            },
            error: function( xhr, status, error ) {
                alert(status + " ---- " + error);
                alert('Oops, there was an error on our end. Please try again later while we fix the issue.');
            }
        });
        
        return false;
    });
    
    //********************************************************
    
    
    //--------------------Event Handlers END------------------
    
});