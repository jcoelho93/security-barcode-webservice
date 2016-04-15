<?php 

	require __DIR__ . '/../vendor/autoload.php';

	require '/../inc/util.inc.php';

	$uri = "http://localhost:8080/v1/settings";
	$body = [
			"algorithm" => $_POST['algorithm'],
			"barcode" => [
					"type" => $_POST['barcode-type'],
					"width" => intval($_POST['width']),
					"height" => intval($_POST['height']),
					"margin" => intval($_POST['margin']),
					"ecl" => $_POST['ecl'],
					"shape" => $_POST['shape'],
					"compact" => boolval($_POST['compact']),
					"compaction" => $_POST['compaction']
				]
	];


		$response = \Httpful\Request::post($uri)
                ->method(\Httpful\Http::POST)        // Alternative to Request::post
                ->withoutStrictSsl()        // Ease up on some of the SSL checks
                ->expectsJson()             // Expect HTML responses
                ->sendsJson()
                ->body(json_encode($body))
                ->send();

        
        if($response->code == 201){
        	header('Location: ../settings.php?setting='.mongo_id_to_hex($response->body->_id));
        }

 ?>