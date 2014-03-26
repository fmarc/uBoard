//------------------------------------------------------
//      JavaScript File used to Handle events in
//      profile.jsp.
//-------------------------------------------------------

//This statement will run as soon as the home.jsp page
//loads all assets and is ready for viewing.
$(document).ready(function() {

    //--------------------Event Handlers----------------------
    //      Below are all the events that run when the
    //      user interacts with the page.


});


function saveUserData(element){
    if($('#edit-save').hasClass('edit-profile')){
        $('#edit-save p').html('Save');
        
        $('.edit').each(function() {
            $(this).replaceWith('<input type="text" id="'+ $(this).attr('id') +'" class="edit" value="' + $(this).html() + '">');
        });
        
        $('#about').attr('disabled', false);

        changeSide();
    } else {
        var name   = $('#name').val();
        var paypal = $('#paypal-email').val();
        var about  = $('#about').val();
        
        var json = {"name" : name, "paypal" : paypal, "about" : about};
        
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
        
        $('.edit').each(function() {
            $(this).replaceWith('<p id="'+ $(this).attr('id') +'" class="edit" >' + $(this).val() + '</p>');
        });
        $('#about').attr('disabled', true);
        $('#edit-save p').html('Edit');
    }
    
    function changeSide() {
        $('#edit-save').toggleClass('edit-profile').toggleClass('save-profile');
    }
}