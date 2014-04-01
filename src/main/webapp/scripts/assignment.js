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


function submitAssignment(username, assignmentId) {
    var assignmentText = $('#assignment').val();
    
    var json = {"username" : username, "assignment-id": assignmentId, "assignment-text": assignmentText};
    alert('Data sent: ' + JSON.stringify(json));
    
    $.ajax({
        url: '',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            alert('Ajax success!');
        }, 
        error: function(data) {
            alert('Ajax error');
        }
    });
}

function submitFeedback(username, assignmentId) {
    var feedbackText = $('#feedback').val();
    
    var json = {"username" : username, "assignment-id": assignmentId, "feedback-text": feedbackText};
    alert('Data sent: ' + JSON.stringify(json));
    
    $.ajax({
        url: '',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            alert('Ajax success!');
        }, 
        error: function(data) {
            alert('Ajax error');
        }
    });
}