$(document).ready(function(){
	$("#input-data").bind("input propertychange", function(){
		$("#bytes-left").html(245 - getByteSize(this.value));
	});
});
	function select(obj)
	{
		var btns = document.getElementsByName('code-select');
		var input = document.getElementById('barcode-type');
		var BLUE = '#337AB7';
		var WHITE = '#FFF';
		var GRAY = '#333';

		if(btns[0] == obj){
			btns[0].style.backgroundColor = BLUE;
			btns[0].style.color = WHITE;
			btns[1].style.backgroundColor = WHITE;
			btns[1].style.color = GRAY;
			btns[2].style.backgroundColor = WHITE;
			btns[2].style.color = GRAY;
			input.value = "qr";
		}
		if(btns[1] == obj){
			btns[0].style.backgroundColor = WHITE;
			btns[0].style.color = GRAY;
			btns[1].style.backgroundColor = BLUE;
			btns[1].style.color = WHITE;
			btns[2].style.backgroundColor = WHITE;
			btns[2].style.color = GRAY;
			input.value = "dm";
		}
		if(btns[2] == obj){
			btns[0].style.backgroundColor = WHITE;
			btns[0].style.color = GRAY;
			btns[1].style.backgroundColor = WHITE;
			btns[1].style.color = GRAY;
			btns[2].style.backgroundColor = BLUE;
			btns[2].style.color = WHITE;
			input.value = "pdf";
		}

	}

	function getByteSize(data)
	{
		var str = data.replace(/\r?\n|\r/g,"");
		return encodeURI(str).split(/%(?:u[0-9A-F]{2})?[0-9A-F]{2}|./).length - 1;

	}