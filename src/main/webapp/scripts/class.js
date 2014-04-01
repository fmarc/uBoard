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


function saveClassData(element){
    if($('#edit-save').hasClass('edit-class')){
        $('#edit-save p').html('Save');
        
        $('#class-desc').attr('disabled', false);

        changeSide();
    } else {
        var classDesc   = $('#class-desc').val();

        var json = {"desc" : classDesc};
        
        alert('Data sent: ' + JSON.stringify(json));
        
        $.ajax({
            url: '',
            data: json, 
            dataType: 'json', 
            success: function(data) {
                alert('Ajax success!');
                changeSide();
            }, 
            error: function(data) {
                alert('Ajax error');
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


function createNewLesson(element){
    var lessonTitle   = $('#lesson-title').val();

    var json = {"lesson-title" : lessonTitle};

    alert('Data sent: ' + JSON.stringify(json));

    $.ajax({
        url: 'lesson.jsp',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            alert('Ajax success!');
            window.location = 'lesson.jsp';
            changeSide();
        }, 
        error: function(data) {
            alert('Ajax error');
            window.location = 'lesson.jsp';
            changeSide();
        }
    });
}


function createNewAssignment(element){
    var assignmentTitle     = $('#assignment-title').val();
    var assignmentDesc      = $('#assignment-description').val();

    var json = {"assignment-title" : assignmentTitle, "description": assignmentDesc};

    alert('Data sent: ' + JSON.stringify(json));

    $.ajax({
        url: 'lesson.jsp',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            alert('Ajax success!');
            $('#create-new-assignment').before("<p class=\"assignment-title\" onclick=\"window.location = 'assignment.jsp?teacher=mgonz108'\">" +($('.assignment-title').length + 1)+ ". " + assignmentTitle + "</p>");
            toggleModal('create-assignment-modal');
        }, 
        error: function(data) {
            alert('Ajax error');
            $('#create-new-assignment').before("<p class=\"assignment-title\" onclick=\"window.location = 'assignment.jsp?teacher=mgonz108'\">" +($('.assignment-title').length + 1)+ ". " + assignmentTitle + "</p>");
            toggleModal('create-assignment-modal');
        }
    });
}