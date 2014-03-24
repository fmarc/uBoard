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
        //axis: 'y',
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
                if($('#content > .box').length-1 <= 1){
                    $(this).parent().remove();
                    $('#content .remove').css('visibility', 'hidden');
                } else {
                    $(this).parent().remove();
                }
            });
            $('#content .remove').css('visibility', 'visible');
            //********************************************************
            
        }
    });
    
    //Initializes the resizable elements
    //$('.image-box, .video-box').resizable({
    //    ghost: true,
    //    containment: '#content'
    //});
    //********************************************************
    
    if($('#content > .box').length === 1){
        $('#content .remove').css('visibility', 'hidden');
    } else {
        $('#content .remove').css('visibility', 'visible');
    }
    
    //********************************************************
    //Toggles the side bar on and off
    $('#sidebar-click-area, #sidebar-handle').click(function() {
        $('#sidebar').toggleClass('hidden');
        $('#content').css('width', $(window).width() - 270);
    });
    //********************************************************
    
    
    //--------------------Event Handlers END------------------
});