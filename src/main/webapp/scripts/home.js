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
    
    
    $("#search").submit(function(e){
        var form = JSON.stringify($(this).serializeArray());
        
        window.location = "?search=" + form.username;

        return false;
    });
    
    // This method is invoked whtn the user Logs in.
    //It successfully logs the user in to the application.
    $('#login-form').submit(function(e){
        var username = $('#login-user').val();
        var password = $('#login-pass').val();

        var json = {page: "all", method: "login", username: username, password: password};

        $.ajax({
            url: '/controller',
            type: "POST",
            data: json, 
            dataType: 'json', 
            success: function(data) {
                alert(data);
                window.location.reload();
            }, 
            error: function(data) {
                alert('Ajax error');
                //changeSide();
            }
        });
        
        e.preventDefault();
        return false;
    });
    
});

// This method is invoked whtn the user click on the "Logout" button.
//It successfully logs the user out of the application.
function logout(sessionId) {
    var json = {page: "all", method: "logout"};

    $.ajax({
        url: '/controller',
        type: "POST",
        data: json, 
        dataType: 'json', 
        success: function(data) {
            alert("Logout Successful!");
            window.location = "/";
        }, 
        error: function(data) {
            alert('Ajax error');
            //changeSide();
        }
    });
    //return false;
}

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



    