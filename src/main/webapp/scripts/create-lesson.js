//------------------------------------------------------
//      JavaScript File used to Handle events in
//      create_class.jsp.
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
    $('.box').draggable({
        revert: 'invalid',
        helper: 'clone',
        connectToSortable: '#content',
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
    $('#content').sortable({
        handle: '.handle',
        cursor: 'url("/images/cursors/closedhand.cur")!important',
        opacity: '0.5',
        tolerance: 'intersect',
        distance: 10,
        items: '> div',
        scrollSensitivity: 200,
        scrollSpeed: 50
    }).droppable({
        drop: function(){
            
            //********************************************************
            //Deletes the element when clicking on the remove button
            $('.remove').unbind('click').click( function() {
                $(this).parent().remove();
            });
            //********************************************************
            
        }
    });
    
    //Initializes the resizable elements
    //$('.image-box, .video-box').resizable({
    //    ghost: true,
    //    containment: '#content'
    //});
    //********************************************************
    
    
    //********************************************************
    //Toggles the side bar on and off
    $('#sidebar-click-area, #sidebar-handle').click(function() {
        $('#sidebar').toggleClass('hidden');
        $('#content').css('width', $(window).width() - 270);
    });
    //********************************************************
    
    
    //--------------------Event Handlers END------------------
});

//**********************************************************
//                    CLICK EVENTS


//--------------------------------------------------------
//            IMAGE MODAL
//Declaring the currently selected Image
var $currentImageSelected;

function openImageModal(element) {
    //Initialize the current image src into the modal
    $('#image-url').val($(element).attr('src'));
    
    //Shows the background Modal
   $('#modal').show();
   //Show the Change Image Modal
   $('#img-box-modal').show();
   
   //Get the current image selected in order to change the
   //image source.
   $currentImageSelected = $(element);
}

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
    $('.box-modal').hide();
    $('#modal').hide();
}