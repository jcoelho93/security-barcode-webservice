<?php 

	function base64_url_encode($input) {
		return strtr($input, '+/=', '-_,');
	}

	function base64_url_decode($input) {
		return strtr($input, '-_,', '+/=');
	}

	function mongo_id_to_hex($id){

		$seconds = dechex($id->timestamp);
		$machine = dechex($id->machineIdentifier);
		$process = dechex($id->processIdentifier);
		$counter = dechex($id->counter);

		return $seconds.$machine.$process.$counter;

	}

 ?>
