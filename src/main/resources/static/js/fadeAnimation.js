
var img = new Image();
var image_path = 'image/college_1.jpg';

img.onload = function(){
      // image  has been loaded
      $("#background").hide();
      $("#background").css("background-image","url('"+image_path+"')");
      $('#background').fadeIn(1000,"swing");
      setTimeout(function(){
      		$('#background').addClass('background-image-blur');
      		setTimeout(function(){
      		$('#main-login').fadeTo(400,1);
      		$('#main-signup').fadeTo(400,1);

      		},1000);

      },3000);
};
img.src = image_path;

