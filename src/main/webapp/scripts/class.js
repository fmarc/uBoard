//------------------------------------------------------
//      JavaScript File used to Handle events in
//      class.jsp.
//-------------------------------------------------------



//This statement will run as soon as the home.jsp page
//loads all assets and is ready for viewing.
$(document).ready(function() {

});


//--------------------------------------------------------
//            VIDEO MODAL
//Declaring the currently selected Image
var $currentVideoSelected;

function openEnrollModal(element) {
    //Initialize the current image src into the modal
    //$('#video-url').val($(element).siblings('.video').attr('src'));
    
    //Shows the background Modal
   //$('#modal').show();
   //Show the Change Image Modal
   //$('#enroll-modal').show();
   
   toggleModal('enroll-modal');
   //Get the current image selected in order to change the
   //image source.
   //$currentVideoSelected = $(element).siblings('.video');
}

function changeVideo(){
    var src = $('#video-url').val();
    src = "//www.youtube.com/embed/" + src.substring(src.indexOf('v=')+2, src.length);
    $currentVideoSelected.attr('src', src);
    hideModal();
}
//--------------------------------------------------------

var num = 0;

function toggleModal(id){
    num++;
    if(num%2 === 0) {
        $('#'+ id +', #modal').hide();
    } else {
        $('#'+ id +', #modal').show();
    }
}


function saveClassData(classId){
    if($('#edit-save').hasClass('edit-class')){
        $('#edit-save p').html('Save');
        
        $('#class-desc').attr('disabled', false);

        changeSide();
    } else {
        var classDesc   = $('#class-desc').val();

        var json = {page: 'class', method: 'saveClass',  classId: classId, description: classDesc};
        
        $.ajax({
            type: 'POST',
            url: '/controller',
            data: json, 
            dataType: 'json', 
            success: function(data) {
                if(data === 1) {
                    alert("Class saved successfully!");
                } else {
                    alert("The class could not be saved successfully. Please try again.");
                }
                changeSide();
            }, 
            error: function() {
                alert("There was an error in the server. Please try again later");
                changeSide();
            }
        });
        
        $('#class-desc').attr('disabled', true);
        $('#edit-save p').html('Edit');
    }
    
    function changeSide() {
        $('#edit-save').toggleClass('edit-class').toggleClass('save-class');
    }
}


function createNewAssignment(classId, username, name, desc){
    
    var json = {page: 'class', method: 'createAssignment', classId: classId, username: username, name: name, description: desc};

    $.ajax({
        type: 'POST',
        url: '/controller',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data >= 0) {
                window.location = "assignment.jsp?assignment_id=" + data;
                return true;
            } else {
                alert('There was an error while creating the Assignment. Please try again later');
            }
        }, 
        error: function() {
            alert("There was a server error. Please try again later.");
        }
    });
    
    toggleModal('create-assignment-modal');
    return false;
}


function enroll(classId, username, paypal){
    if(paypal.length > 4) {
        var json = {page: 'class', method: 'enroll', classId: classId, username: username};

        $.ajax({
            type: 'POST',
            url: '/controller',
            data: json, 
            dataType: 'json', 
            success: function(data) {
                if(data === 1){
                    window.location.reload();
                } else {
                    alert('There was an error while enrolling you into the class, please try again later');
                }
                toggleModal('enroll-modal');
            }, 
            error: function(data) {
                alert('There was an error while enrolling you into the class, please try again later');
                toggleModal('enroll-modal');
            }
        });
    } else {
        toggleModal('enroll-modal');
        toggleModal('error-modal');
    }
}

function redirectProfile(username){
    window.location = "/profile.jsp?username=" + username;
}