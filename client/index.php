<!DOCTYPE html>
<html>
<head>
	<?php include 'inc/header.inc.php'; ?>	
</head>
<body>

<?php include 'inc/navbar.inc.php'; ?>

	<div class="container">
		<div class="row">
			<div class="col-lg-4">
				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<h4>Algoritmo</h4>
							<select class="form-control" name="algorithm">
								<option value="sha">SHA-256</option>
								<option value="aes">AES</option>
								<option value="rsa">RSA</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<h4>Parâmetros</h4>
								<div class="btn-group">
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)" style="background-color:#337AB7;color:white">QR Code</button>
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)">Data Matrix</button>
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)">PDF 417</button>
								</div>
								<br /><br />
								<div>
									<input type="hidden" value="qr" name="barcode-type" id="barcode-type" />
									<table width="100%">
										<tr>
											<td><label>Largura (px):</label></td>
											<td><input type="number" placeholder="150" class="form-control" /></td>
										</tr>
										<tr>
											<td><label>Altura (px):</label></td>
											<td><input type="number" placeholder="150" class="form-control" /></td>
										</tr>
										<tr>
											<td><label>Margem (px):</label></td>
											<td><input type="number" placeholder="3" class="form-control" /></td>
										</tr>
									</table>
									<div id="qr-code-params">
										<h5>Error Correction Level:</h5>
										<select class="form-control" name="ecl">
											<option value="h">H ~ 30% de correção</option>
											<option value="l">L ~ 7% de correção</option>
											<option value="m">M ~ 15% de correção</option>
											<option value="q">Q ~ 25% de correção</option>
										</select>
									</div>
									<div id="data-matrix-params" style="display:none">
										<h5>Forma:</h5>
										<select class="form-control" name="shape">
											<option value="force_none">Auto</option>
											<option value="force_square">Quadrado</option>
											<option value="force_rectangle">Rectangulo</option>
										</select>
									</div>
									<div id="pdf-417-params" style="display:none">
										<h5>Compacto:</h5>
										<label class="radio-inline">
											<input type="radio" name="compact" value="true"> Sim
										</label>
										<label class="radio-inline">
											<input type="radio" name="compact" value="false"> Não
										</label>
										<h5>Compactação:</h5>
										<select class="form-control" name="compaction">
											<option value="auto">Auto</option>
											<option value="byte">Byte</option>
											<option value="numeric">Numeric</option>
											<option value="text">Text</option>
										</select>
									</div>
								</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<h4>Dados</h4>
							<textarea placeholder="JSON{...}" class="form-control" rows="3" id="input-data" maxlength="245"></textarea>
							<div style="width:100%;text-align:right">
								<small style="color:#666;"><span id="bytes-left">245</span> bytes</small>
							</div>
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
					<div class="result-container">
						<img src="img/qrcode.png" id="result" />
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>