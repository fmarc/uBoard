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
        
        if($('#login').hasClass('display-modal')){
            $('#login-user').focus();
        }
    });
    
    //********************************************************
    //Hides the login modal when the user clicks outside of it
    $('html').click(function() {
        $('#login').removeClass('display-modal');
    });
    
    //Prevents the above function from attaching the click event
    //to the specified elements
    $('#login-modal, #login').click(function(e){
        e.stopPropagation();
    });
    //********************************************************
    
//    $("#login-form").submit(function(e){
//        var form = JSON.stringify($(this).serializeArray());
//        
//        window.location = "?id=" + form.username;
//
//        return false;
//    });
//    
//    $("#search").submit(function(e){
//        var form = JSON.stringify($(this).serializeArray());
//        
//        window.location = "?search=" + form.username;
//
//        return false;
//    });
    
});


var num = 0;
function toggleModal(id){
    num++;
    if(num%2 === 0) {
        $('#'+ id +', #modal').hide();
    } else {
        $('#'+ id +', #modal').show();
    }
}


function postNewComment(user) {
    var comment = $('#text-comment').val();
    
    if(comment.length === 0){
        alert("Comment text cannot be empty. Please type in your comment.");
        return false;
    }
    
    var json = {"user": user, "comment" : comment};
    alert('Data sent: ' + JSON.stringify(json));
    
    $.ajax({
        url: '',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            alert('Ajax success!');
            postComment(user, comment);
            toggleModal('post-comment-modal');
        }, 
        error: function(data) {
            alert('Ajax error');
            postComment(user, comment);
            toggleModal('post-comment-modal');
        }
    });
    
    function postComment(user, comment){
        var commentHtml = '<div class="comment">' +
                            '<div class="comment-user"><img src="/images/comments/user-comment.png"></div>' +
                            '<div class="comment-text"><p class="user">'+ user +'</p><p class="text">'+ comment +'</p></div>' +
                            '</div>';
                    
        $('#comment-section').append(commentHtml);
    }
}



    