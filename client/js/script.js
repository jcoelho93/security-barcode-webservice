function select(obj)
{
	var btns = document.getElementsByName('code-select');
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
	}
	if(btns[1] == obj){
		btns[0].style.backgroundColor = WHITE;
		btns[0].style.color = GRAY;
		btns[1].style.backgroundColor = BLUE;
		btns[1].style.color = WHITE;
		btns[2].style.backgroundColor = WHITE;
		btns[2].style.color = GRAY;
	}
	if(btns[2] == obj){
		btns[0].style.backgroundColor = WHITE;
		btns[0].style.color = GRAY;
		btns[1].style.backgroundColor = WHITE;
		btns[1].style.color = GRAY;
		btns[2].style.backgroundColor = BLUE;
		btns[2].style.color = WHITE;
	}

}