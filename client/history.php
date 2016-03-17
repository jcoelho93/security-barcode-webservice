<!DOCTYPE html>
<html>
<head>
	<?php include 'inc/header.inc.php'; ?>
</head>
<body>

	<?php include 'inc/navbar.inc.php'; ?>

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
						<li class="list-group-item"><a href="#">QR Code | SHA-256 | 17-03-2016</a></li>
						<li class="list-group-item"><a href="#">Data Matrix | SHA-256 | 12-03-2016</a></li>
						<li class="list-group-item"><a href="#">QR Code | SHA-256 | 12-03-2016</a></li>
						<li class="list-group-item"><a href="#">PDF417 | SHA-256 | 10-03-2016</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="well">
					
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