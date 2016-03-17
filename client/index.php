<!DOCTYPE html>
<html>
<head>
	<title>Barcode Security Module</title>
	<link rel="stylesheet" type="text/css" href="css/custom.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/script.js"></script>
</head>
<body>

<?php include 'navbar.php'; ?>

	<div class="container">
		<div class="row">
			<div class="col-lg-4">
				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<h4>Algoritmo</h4>
							<select class="form-control">
								<option>SHA-256</option>
								<option>AES</option>
								<option>RSA</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<h4>Parâmetros</h4>
								<div class="btn-group">
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)">QR Code</button>
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)">Data Matrix</button>
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)">PDF 417</button>
								</div>
								<div>
									
								</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<h4>Dados</h4>
							<textarea placeholder="JSON{...}" class="form-control" rows="3"></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<button class="btn btn-primary">Gerar</button>
					</div>
				</div>
			</div>
			<div class="col-lg-8">
				<div class="well">
					<h4>Resultado</h4>
				</div>
			</div>
		</div>
	</div>

</body>
</html>