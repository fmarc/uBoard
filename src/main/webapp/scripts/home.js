//------------------------------------------------------
//      JavaScript File used to Handle events in
//      home.jsp.
//-------------------------------------------------------



//This statement will run as soon as the home.jsp page
//loads all assets and is ready for viewing.
$(document).ready(function() {
    
    
    
    
    //--------------------Event Handlers----------------------
    //      Below are all the events that run when the
    //      user interacts with the page.

    //Displays the Login Modal when the LOG IN button is pressed
    $('#login').click(function() { 
        $('#login').toggleClass('display-modal');
    });
    
});




    