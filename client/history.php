<!DOCTYPE html>
<html>
<head>
	<?php include 'inc/header.inc.php'; ?>
</head>
<body>

	<?php include 'inc/navbar.inc.php'; 
		include 'inc/util.inc.php';
		require __DIR__ . '/vendor/autoload.php';
	?>

	<div class="container">
		<div class="row">
			<div class="col-lg-5">
				<div class="input-group">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Procurar... <span class="caret"></span></button>
						<ul class="dropdown-menu">
							<li><a href="#">Barcode</a></li>
							<li><a href="#">Algoritmo</a></li>
							<li><a href="#">Data</a></li>
						</ul>
					</div>
					<input type="text" class="form-control" aria-label="...">
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-lg-3">
				<div class="well">
					<ul class="list-group">
						<?php 
							$uri = "http://localhost:8080/v1/barcodes";
							$response = \Httpful\Request::get($uri)->send();
							$barcodes = $response->body;
							foreach($barcodes as $barcode){
								$id = mongo_id_to_hex($barcode->_id);
								echo '<li class="list-group-item"><a href="?barcode='.$id.'">'.str_replace("_"," ",strtoupper($barcode->settings->barcode->type)).' | '.str_replace("_"," ",strtoupper($barcode->settings->algorithm)).' | '.$barcode->created_at.'</a></li>';
							}
						?>
					</ul>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="well">
					<?php 
						if(isset($_GET['barcode'])){
							$uri = "http://localhost:8080/v1/barcodes/".$_GET['barcode'];
							$response = \Httpful\Request::get($uri)->send();
							$base64 = $response->body->base64;
							echo '<img src="data:image/png;base64,'.$base64.'" />';
						}
					 ?>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="well">
					
				</div>
			</div>
		</div>
	</div>

	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</body>
</html>