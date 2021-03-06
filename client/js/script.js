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
			input.value = "qr_code";
			document.getElementById('qr-code-params').style.display = "block";
			document.getElementById('data-matrix-params').style.display = "none";
			document.getElementById('pdf-417-params').style.display = "none";
		}
		if(btns[1] == obj){
			btns[0].style.backgroundColor = WHITE;
			btns[0].style.color = GRAY;
			btns[1].style.backgroundColor = BLUE;
			btns[1].style.color = WHITE;
			btns[2].style.backgroundColor = WHITE;
			btns[2].style.color = GRAY;
			input.value = "data_matrix";
			document.getElementById('qr-code-params').style.display = "none";
			document.getElementById('data-matrix-params').style.display = "block";
			document.getElementById('pdf-417-params').style.display = "none";
		}
		if(btns[2] == obj){
			btns[0].style.backgroundColor = WHITE;
			btns[0].style.color = GRAY;
			btns[1].style.backgroundColor = WHITE;
			btns[1].style.color = GRAY;
			btns[2].style.backgroundColor = BLUE;
			btns[2].style.color = WHITE;
			input.value = "pdf_417";
			document.getElementById('qr-code-params').style.display = "none";
			document.getElementById('data-matrix-params').style.display = "none";
			document.getElementById('pdf-417-params').style.display = "block";
		}

	}

	function getByteSize(data)
	{
		var str = data.replace(/\r?\n|\r/g,"");
		return encodeURI(str).split(/%(?:u[0-9A-F]{2})?[0-9A-F]{2}|./).length - 1;

	}