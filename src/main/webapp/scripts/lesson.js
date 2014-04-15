//------------------------------------------------------
//      JavaScript File used to Handle events in
//      lesson.jsp.
//-------------------------------------------------------



//This statement will run as soon as the home.jsp page
//loads all assets and is ready for viewing.
$(document).ready(function() {
    
    $('.remove').unbind('click').click( function() {
        $(this).parent().remove();
    });
    
    //--------------------Event Handlers----------------------
    //      Below are all the events that run when the
    //      user interacts with the page.
    
    
    //********************************************************
    //Initializes the draggable elements to be used with the
    //droppable target list in create a lesson
    $('.drag').draggable({
        revert: 'invalid',
        helper: 'clone',
        connectToSortable: '#sort',
        scrollSensitivity: 200,
        scrollSpeed: 50,
        drag: function(event, ui) {
            if($(ui.helper).css('opacity') > 0.5) {
                $(ui.helper).removeClass('ui-sortable-helper');
                $(ui.helper).addClass('invalid');
            } else {
                $(ui.helper).removeClass('invalid');
            }
        }
    });
    
    //Initializes the sortable and droppable list of target elements
    //for the create a lesson.
    $('#sort').sortable({
        handle: '.handle',
        cursor: 'url("/images/cursors/closedhand.cur")!important',
        opacity: '0.5',
        tolerance: 'intersect',
        distance: 10,
        items: '> div',
        scrollSensitivity: 200,
        scrollSpeed: 50
    }).droppable({
        drop: function(dropObject, domObject){
            
            //Remove the drag tag
            $($(domObject)[0].draggable[0]).removeClass('drag');
            
            //********************************************************
            //Deletes the element when clicking on the remove button
            $('.remove').unbind('click').click( function() {
                $(this).parent().remove();
            });
            //********************************************************
            
        }
    });
    //--------------------Event Handlers END------------------
});

//**********************************************************
//                    CLICK EVENTS


//--------------------------------------------------------
//              SIDE BAR TOGGLE
//Toggles the side bar on and off
var num = 0;
function toggleSideBar() {
    $('#sidebar').toggleClass('hidden');
    $('#content').css('width', $(window).width() - 270);
    $('#content .box .edit').toggleClass('hide');
    if(++num%2) {
       $('.editable').prop('contenteditable', 'true');
    } else {
        $('.editable').prop('contenteditable', 'false');
    }
};
//--------------------------------------------------------


//--------------------------------------------------------
//            IMAGE MODAL
//Declaring the currently selected Image
var $currentImageSelected;

function openImageModal(element) {
    console.log($(element).parent().parent().find($('.image')).get($(element).index()));
    
    //Gets the right image matching it's button
    var $image = $($(element).parent().parent().find($('.image')).get($(element).index()));
    
    //Initialize the current image src into the modal
    $('#image-url').val($(element).attr('src'));
    
    //Shows the background Modal
   $('#modal').show();
   //Show the Change Image Modal
   $('#img-box-modal').show();
   
   //Get the current image selected in order to change the
   //image source.
   $currentImageSelected = $image;
}

//Changes the image of with the desired url supplied by the user
function changeImage(){
    var src = $('#image-url').val();
    
    if(src){
        $currentImageSelected.attr('src', src);
    } else {
        $currentImageSelected.attr('src', "/images/blanked.png");
    }
    
    hideModal();
}
//--------------------------------------------------------

//--------------------------------------------------------
//            VIDEO MODAL
//Declaring the currently selected Image
var $currentVideoSelected;

function openVideoModal(element) {
    //Initialize the current image src into the modal
    $('#video-url').val($(element).siblings('.video').attr('src'));
    
    //Shows the background Modal
   $('#modal').show();
   //Show the Change Image Modal
   $('#video-box-modal').show();
   
   //Get the current image selected in order to change the
   //image source.
   $currentVideoSelected = $(element).siblings('.video');
}

function changeVideo(){
    var src = $('#video-url').val();
    src = "//www.youtube.com/embed/" + src.substring(src.indexOf('v=')+2, src.length);
    $currentVideoSelected.attr('src', src);
    hideModal();
}
//--------------------------------------------------------

function hideModal(){
    $('.box-modal, #modal').hide();
}

/**
 * Saves the new lesson data edited by the author of the page.
 * @param lessonId - The lesson ID of the lesson being saved.
 * @returns boolean - Indicates whether the process succeeded
 */
function saveLessonData(lessonId) {
    
    var isHidden = false;
    
    //Removes formatting capabilities
    if(!$('#content .box .edit').hasClass('hide')) {
        isHidden = true;
        $('#content .box .edit').toggleClass('hide');
        $('.editable').prop('contenteditable', 'false');
    }
    
    var html = $('#sort').html();
    
    var json = {page: "lesson", method: "saveLesson",  lessonId: lessonId, html: html};
        
    $.ajax({
        type: 'POST',
        url: '/controller',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data === 1) {
                toggleModal('save-confirm-modal');
            } else {
                alert("Lesson not saved successfully");
            }
        }, 
        error: function() {
            alert('There was an error in the server. Please try again.');
        }
    });
    
    if(isHidden) {
        $('#content .box .edit').toggleClass('hide');
        $('.editable').prop('contenteditable', 'true');
    }
}

/**
 * Rates the current lesson with the specified rating
 * @param {type} num - The rating (1 or -1) from the user
 * @param lessonId - The lessons ID
 * @param username - The user rating the lesson
 * @returns boolean - Indicates whether the process succeeded
 */
function rate(username, lessonId, num){
    //Remove the click event on the rating so the user cannot rate the lesson
    //again
    $('.rate').prop('onclick', null);
    $('#rate-positive, #rate-negative').addClass('rated');
    
    //Keep track of whether the rating was positive or negative
    var positive = false;
    if(num > 0) {
        positive = true;
    }
    
    var json = {page: 'lesson', method: 'rate', username: username, lessonId: lessonId, rating: num};
    
    $.ajax({
        type: 'POST',
        url: '/controller',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            if(data === 1){
                $('#rating p').html(parseInt($('#rating p').html()) + num);
                if(positive){
                    $('#rate-positive').addClass('user-rated');
                } else {
                    $('#rate-negative').addClass('user-rated');
                }
                checkRating();
            } else {
                alert("There was an erro while saving your rate selection. Please try again.");
            }
        }, 
        error: function(data) {
            alert('There was an error in the server. Please try again later.');
        }
    });
    
    function checkRating(){
        if(parseInt($('#rating p').html()) < 0){
            $('#rating p').removeClass('positive-rating');
            $('#rating p').addClass('negative-rating');
        } else {
            $('#rating p').removeClass('negative-rating');
            $('#rating p').addClass('positive-rating');
        }
    }
}