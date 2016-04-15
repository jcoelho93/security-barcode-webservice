<!DOCTYPE html>
<html>
<head>
	<?php include 'inc/header.inc.php'; ?>	
</head>
<body>

<?php 
	include 'inc/navbar.inc.php'; 
	include 'inc/util.inc.php';
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
							$id = $setting->_id;
							echo '<li class="list-group-item"><a href="?setting='.mongo_id_to_hex($id).'">'.str_replace("_"," ",strtoupper($setting->barcode->type)).' | '.strtoupper($setting->algorithm).' | '.$setting->created_at.'</a></li>';
						}
						echo '</ul>';
					?>
				</div>
			</div>
			<div class="col-lg-5">
				<div class="well">
					<h4>Algoritmo:</h4>
					<?php
					if(isset($_GET['setting'])){
						$uri = "http://localhost:8080/v1/settings/".$_GET['setting'];
						$response = \Httpful\Request::get($uri)->send();
						if(isset($response->body->algorithm)){
							echo '<span>'.strtoupper($response->body->algorithm).'</span>';
						}else{
							echo '<span>Unavailable</span>';
						}
					}
					?>
					<h4>Parâmetros</h4>
					<?php 
					if(isset($_GET['setting'])){
						$uri = "http://localhost:8080/v1/settings/".$_GET['setting'];
						$response = \Httpful\Request::get($uri)->send();
						if(isset($response->body->barcode->type)){
							$barcode = $response->body->barcode;
							echo '<span>'.str_replace("_"," ",strtoupper($barcode->type)).'</span><br/>';
							echo '<span>Largura: '.$barcode->width.'</span><br />';
							echo '<span>Altura: '.$barcode->height.'</span><br />';
							echo '<span>Margem: '.$barcode->margin.'</span><br />';
							switch($barcode->type){
								case "qr_code":
									echo '<span>ECL: '.strtoupper($barcode->ecl).'</span><br/>';
									break;
								case "data_matrix":
									switch($barcode->shape){
										case "force_none":
											echo '<span>Forma: Auto</span><br/>';
											break;
										case "force_square":
											echo '<span>Forma: Quadrado</span><br/>';
											break;
										default:
											echo '<span>Forma: Rectângulo</span><br/>';
									}
									break;
								case "pdf_417":
									echo '<span>Compacto: '.(($barcode->compact)?'Sim':'Não').'</span><br/>';
									echo '<span>Compactação: '.strtoupper($barcode->compaction).'</span><br/>';
									break;
							}
						}else{
							echo '<span>Unavailable</span>';
						}
						echo '<br/><a href="index.php?setting='.$_GET['setting'].'" class="btn btn-primary">Usar</a>&nbsp;';
						echo '<a href="?action=delete&setting='.$_GET['setting'].'" class="btn btn-danger">Desativar</a>';
						if(isset($_GET['action']) && $_GET['action'] == "delete"){
							$response = \Httpful\Request::delete($uri)->send();
							header('Location: settings.php');
						}
					}
					?>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="well">
					<form action="app/create_setting.php" method="post">
					<h3>Criar nova parametrização</h3>
					<div class="row">
					<div class="col-lg-12">
						<div>
							<h4>Algoritmo</h4>
							<select class="form-control" name="algorithm">
								<option value="sha-256">SHA-256</option>
								<option value="aes">AES</option>
								<option value="rsa">RSA</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div>
							<h4>Parâmetros</h4>
								<div class="btn-group">
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)" style="background-color:#337AB7;color:white">QR Code</button>
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)">Data Matrix</button>
									<button type="button" class="btn btn-default" name="code-select" onclick="select(this)">PDF 417</button>
								</div>
								<br /><br />
								<div>
									<input type="hidden" value="qr_code" name="barcode-type" id="barcode-type" />
									<table width="100%">
										<tr>
											<td><label>Largura (px):</label></td>
											<td><input type="number" placeholder="150" class="form-control" name="width" /></td>
										</tr>
										<tr>
											<td><label>Altura (px):</label></td>
											<td><input type="number" placeholder="150" class="form-control" name="height" /></td>
										</tr>
										<tr>
											<td><label>Margem (px):</label></td>
											<td><input type="number" placeholder="3" class="form-control" name="margin" /></td>
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
											<option value="force_none" selected="selected">Auto</option>
											<option value="force_square">Quadrado</option>
											<option value="force_rectangle">Rectangulo</option>
										</select>
									</div>
									<div id="pdf-417-params" style="display:none">
										<h5>Compacto:</h5>
										<label class="radio-inline">
											<input type="radio" name="compact" value="true" checked> Sim
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
				<br/>
				<input type="submit" class="btn btn-primary" value="Criar"/>
				</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>