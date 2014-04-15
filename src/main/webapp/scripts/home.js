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
        e.preventDefault();
        
        var username = $('#login-user').val();
        var password = $('#login-pass').val();

        login(username, password);
        
        return false;
    });
    
});

//This method handles the log in for the application
function login(username, password){
    var json = {page: "all", method: "login", username: username, password: password};

    $.ajax({
        url: '/controller',
        type: "POST",
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data === 1){
                window.location.reload();
            } else {
                $('#login-error').css('display', 'inline-block');
            }
        }, 
        error: function(data) {
            alert('Ajax error');
        }
    });
}

// This method is invoked whtn the user click on the "Logout" button.
//It successfully logs the user out of the application.
function logout() {
    var json = {page: "all", method: "logout"};

    $.ajax({
        url: '/controller',
        type: "POST",
        data: json, 
        dataType: 'json', 
        success: function() {
            window.location = "/";
            return true;
        }, 
        error: function(data) {
            alert('Ajax error: ' + data);
            //changeSide();
        }
    });
     return false;
}

//Toggles all the modals based on their element ID
var num = 0;
function toggleModal(id){
    num++;
    if(num%2 === 0) {
        $('#'+ id +', #modal').hide();
    } else {
        $('#'+ id +', #modal').show();
    }
}


/**
 * Creates a new lesson and re-directs the user to the newly created lesson
 * @param {type} username - The User's username
 * @param {type} title - The lesson title
 * @param {type} classId - The class ID for the created lesson
 * @returns {Boolean} - Indicates whether the process was successful.
 */
function createNewLesson(username, title, classId) {
    
    //Creates the JSON object being sent to the server
    var json = {page: "all", method: "createLesson", username: username, classId: classId, title: title};

    //Creates an async call to the server using AJAX
    $.ajax({
        url: '/controller',
        type: "POST",
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data >= 1){
                window.location = "/lesson.jsp?lesson_id=" + data;
                return true;
            } else {
                alert('There was an error while creating the lesson. Please try again later');
            }
        }, 
        error: function(data) {
            alert('There was an error with the server! Please contact an admin: ' + data);
        }
    });
    return false;
}

/**
 * Creates a new class in the server
 * @param {type} username
 * @param {type} title
 * @param {type} price
 * @param {type} limit
 * @returns {Boolean}
 */
function createNewClass(username, title, price, limit) {
    
    //Creates the JSON object being sent to the server
    var json = {page: "all", method: "createClass", username: username, name: title, price: price, limit: limit};

    //Creates an async call to the server using AJAX
    $.ajax({
        url: '/controller',
        type: "POST",
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data >= 1){
                window.location = "/class.jsp?class_id=" + data;
                return true;
            } else {
                alert('There was an error while creating the lesson. Please try again later');
            }
        }, 
        error: function(data) {
            alert('There was an error with the server! Please contact an admin: ' + data);
        }
    });
    return false;
}


/**
 * Creates a new ajax request which is sent to the server
 * in order to create a new comment under this lesson
 * @param {type} user - User posting comment
 * @param {type} classId - The class ID for this lesson
 * @param {type} lessonId - The lesson ID for this lesson
 * @returns {Boolean} - Indicates wether the process was successful
 */
function postNewComment(user, classId, lessonId) {
    var comment = $('#text-comment').val();
    
    //If the comment length is equal to 0 (Empty) the
    //method informs the user that they must input text
    if(comment.length === 0){
        alert("Comment text cannot be empty. Please type in your comment.");
        return false;
    }
    
    //This is a JSON object which is sent to the Server
    var json = {page: 'lesson', method: 'comment', username: user, classId: classId, lessonId: lessonId, text: comment};
    
    //This is an ajax request which runs asynchrounously
    $.ajax({
        type: 'POST',
        //The url where the POST request should be sent
        url: '/controller',
        data: json, 
        dataType: 'json', 
        //If the request was successful (no errors server-side) then this will hit
        // data - The information that gets returned from the Server
        success: function(data) {
            if(data === 1){
                postComment(user, comment);
            } else {
                alert('An error occured while posting a new comment. Please try again later');
            }
            
            toggleModal('post-comment-modal');
            return true;
        },
        //IF there was an error server-side, this will hit.
        error: function(data) {
            alert('An error occured while posting a new comment. Please try again later');
            toggleModal('post-comment-modal');
            return false;
        }
    });
    
    //This function attaches the recently created comment at the end
    //of the comment section.
    function postComment(user, comment){
        var commentHtml = '<div class="comment">' +
                            '<div class="comment-user"><img src="/images/comments/user-comment.png"></div>' +
                            '<div class="comment-text"><p class="user">'+ user +'</p><p class="text">'+ comment +'</p></div>' +
                            '</div>';
                    
        $('#comment-section').append(commentHtml);
    }
}



    