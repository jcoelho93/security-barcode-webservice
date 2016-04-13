<!DOCTYPE html>
<html>
<head>
	<?php include 'inc/header.inc.php'; ?>	
</head>
<body>

<?php 
	include 'inc/navbar.inc.php'; 
	require __DIR__ . '/vendor/autoload.php';
?>

	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="well">
					<?php
						$uri = "http://localhost:8080/v1/settings";
						$response = \Httpful\Request::get($uri)->send();
						$settings = $response->body;
						echo '<ul class="list-group">';
						foreach($settings as $setting){
							echo '<li class="list-group-item"><a href="#">'.str_replace("_"," ",strtoupper($setting->barcode->type)).' | '.strtoupper($setting->algorithm).' | '.$setting->created_at.'</a></li>';
						}
						echo '</ul>';
					?>
				</div>
			</div>
		</div>
	</div>

</body>
</html>