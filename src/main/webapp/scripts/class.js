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
   
   toggleModal();
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

function toggleModal(){
    num++;
    if(num%2 === 0) {
        $('.box-modal, #modal').hide();
    } else {
        $('.box-modal, #modal').show();
    }
        
}