package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.BarcodeParams;
import java.util.Map;
import java.util.Set;
import org.bson.Document;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class InputDataSettings   {
  
  private BarcodeParams barcode = null;
  private String algorithm = null;

  
  /**
   **/
  public InputDataSettings barcode(BarcodeParams barcode) {
    this.barcode = barcode;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("barcode")
  public BarcodeParams getBarcode() {
    return barcode;
  }
  public void setBarcode(BarcodeParams barcode) {
    this.barcode = barcode;
  }

  
  /**
   **/
  public InputDataSettings algorithm(String algorithm) {
    this.algorithm = algorithm;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("algorithm")
  public String getAlgorithm() {
    return algorithm;
  }
  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InputDataSettings inputDataSettings = (InputDataSettings) o;
    return Objects.equals(barcode, inputDataSettings.barcode) &&
        Objects.equals(algorithm, inputDataSettings.algorithm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(barcode, algorithm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InputDataSettings {\n");
    
    sb.append("    barcode: ").append(toIndentedString(barcode)).append("\n");
    sb.append("    algorithm: ").append(toIndentedString(algorithm)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public Document toQuery()
  {
      
      Document setting = new Document();
      
      setting.append("algorithm", this.getAlgorithm());
     
      Map map = this.getBarcode().toMap();

      for (Map.Entry entry : ((Set<Map.Entry>) map.entrySet())) {
          if(entry.getValue() != null){
              setting.append("barcode." + entry.getKey().toString(), entry.getValue());
          }
      }
      
      return setting;
      
  }

  public Document toDocument()
  {
      
      Document setting = new Document();
      
      setting.append("algorithm", this.getAlgorithm());
     
      Map map = this.getBarcode().toMap();
      Document barcodeParams = new Document();
      for (Map.Entry entry : ((Set<Map.Entry>) map.entrySet())) {
          if(entry.getValue() != null){
              barcodeParams.append(entry.getKey().toString(), entry.getValue());
          }
      }
      
      setting.append("barcode",barcodeParams);
      
      return setting;
      
  }
  
}

