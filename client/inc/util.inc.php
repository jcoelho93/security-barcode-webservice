<?php 

	function base64_url_encode($input) {
		return strtr($input, '+/=', '-_,');
	}

	function base64_url_decode($input) {
		return strtr($input, '-_,', '+/=');
	}

 ?>
