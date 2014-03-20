//------------------------------------------------------
//      JavaScript File used to Handle events in
//      create_class.jsp.
//-------------------------------------------------------



//This statement will run as soon as the home.jsp page
//loads all assets and is ready for viewing.
$(document).ready(function() {
    
    //--------------------Event Handlers----------------------
    //      Below are all the events that run when the
    //      user interacts with the page.
    
    
    
    //********************************************************
    //Toggles the side bar on and off
    $('#sidebar-click-area, #sidebar-handle').click(function() {
        $('#sidebar').toggleClass('hidden');
        $('#content').css('width', $(window).width() - 270);
    });

    //********************************************************
    
    
    //--------------------Event Handlers END------------------
    
});