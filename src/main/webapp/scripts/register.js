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
    
    $('#register').submit(function() {
        var $form = $('#register');
        
        $.ajax({
            type: "POST",
            url: $form.attr( 'action' ),
            data: $form.serialize(),
            success: function( response ) {
                /*
                 *  Check if the message back is either of the following
                 *      -Username already taken
                 *      -Email Address already registered
                 *      
                 *      :Inform the user to change info
                 *      
                 *  Else
                 *      -Log the user in internally and display a success message
                 *      -Add the user-auth elements in the top bar.
                 */
                console.log( response );
            },
            error: function( response ) {
                alert('Oops, there was an error on our end. Please try again later while we fix the issue.');
            }
        });
    });
    
    //********************************************************
    
    
    //--------------------Event Handlers END------------------
    
});