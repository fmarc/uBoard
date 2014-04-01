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

function changeStream() {
    var name = $('#stream-channel').val();
    
    $('#stream-video').prop('data', 'http://www.twitch.tv/widgets/live_embed_player.swf?channel='+ name);
    $('#stream-chat').prop('src', 'http://twitch.tv/'+ name +'/chat?popout=');
    $('#twitch-param').prop('value', 'hostname=www.twitch.tv&channel=' + name + '&auto_play=true&start_volume=25');
    
    var container = document.getElementById('stream-video');
    var content = container.innerHTML;
    container.innerHTML = content;
    
    toggleModal('change-stream-modal');
}

function saveStream(classId) {
    var streamUrl = $('#stream-video').prop('data');
    var streamChannel = streamUrl.substring( streamUrl.indexOf('=') + 1, streamUrl.length );
    
    alert(streamChannel);
    
    var json = {"class-id": classId ,"stream-name" : streamChannel};
    alert('Data sent: ' + JSON.stringify(json));
    
    $.ajax({
        url: '',
        data: json, 
        dataType: 'json', 
        success: function(data) {
            alert('Ajax success!');
            toggleModal('save-confirm-modal');
        }, 
        error: function(data) {
            alert('Ajax error');
            toggleModal('save-confirm-modal');
        }
    });
    
    
}