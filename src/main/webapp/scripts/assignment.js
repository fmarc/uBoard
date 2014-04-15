//------------------------------------------------------
//      JavaScript File used to Handle events in
//      assignment.jsp.
//-------------------------------------------------------


//This statement will run as soon as the home.jsp page
//loads all assets and is ready for viewing.
$(document).ready(function() {
    
    //--------------------Event Handlers----------------------
    //      Below are all the events that run when the
    //      user interacts with the page.
});


function submitAssignment(assignmentId, username, submission) {
    var json = {page: 'assignment', method: 'subAssignment', username: username, assignmentId: assignmentId, submission: submission, title: ''};
    
    $.ajax({
        type: 'POST',
        url: '/controller',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data === 1) {
                alert('Assignment submitted successfully!');
            } else {
                alert('There was an error while submitting your assignment, please try again.');
            }
        }, 
        error: function() {
            alert('There was an error in the server. Please try again later.');
        }
    });
}

function submitFeedback(assignmentId, submitBy, feedback) {
    var json = {page: 'assignment', method: 'feedback', submitBy: submitBy, assignId: assignmentId, feedback: feedback};
    
    $.ajax({
        type: 'POST',
        url: '/controller',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data === 1) {
                alert('Assignment feedback submitted successfully!');
            } else {
                alert('There was an error while submitting your feedback, please try again.');
            }
        }, 
        error: function() {
            alert('There was an error in the server. Please try again later.');
        }
    });
}