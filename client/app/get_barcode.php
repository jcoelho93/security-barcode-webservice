<?php 
	
		require __DIR__ . '/../vendor/autoload.php';

		require '/../inc/util.inc.php';

		$uri = "http://localhost:8080/v1/barcodes";
		$body = [
			'data' => $_POST['input-data'],
			'settings' => [
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
				]
		];

		$response = \Httpful\Request::post($uri)
                ->method(\Httpful\Http::POST)        // Alternative to Request::post
                ->withoutStrictSsl()        // Ease up on some of the SSL checks
                ->expectsJson()             // Expect HTML responses
                ->sendsJson()
                ->body(json_encode($body))
                ->send();
        var_dump($response);
		header('Location: ../index.php?result='.base64_url_encode($response->body->base64));

 ?>