var host = "http://localhost";
var porta = ":8080";
var projeto = "/colmeia/";

function init() {
    var myWindow = window.open(host + posta + projeto, "C.O.L.M.E.I.A","width="+  ($(document).width()) + ", height="+ ($(document).height()));
	myWindow.exit();
}

var button = document.getElementById("entrar");
button.onclick = function(){
	alert("Oi");
	init();
	};
	
	jQuery(function($){
	       $(".mask_cpf").mask("999.999.999-99");
	});
