package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.BarcodeParams;
import javax.ws.rs.core.Context;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class Setting   {
  
    
    
  public enum AlgorithmEnum {
    SHA_256("sha-256"),
    AES("aes"),
    RSA("rsa");

    private String value;

    AlgorithmEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private AlgorithmEnum algorithm = AlgorithmEnum.SHA_256;
  private BarcodeParams barcode = null;
  private boolean active = true;
  
  /**
   **/
  public Setting algorithm(AlgorithmEnum algorithm) {
    this.algorithm = algorithm;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("algorithm")
  public AlgorithmEnum getAlgorithm() {
    return algorithm;
  }
  public void setAlgorithm(AlgorithmEnum algorithm) {
    this.algorithm = algorithm;
  }

  
  /**
   **/
  public Setting barcode(BarcodeParams barcode) {
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

  @ApiModelProperty(value = "")
  @JsonProperty("active")
  public boolean isActive(){
      return this.active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Setting setting = (Setting) o;
    return Objects.equals(algorithm, setting.algorithm) &&
        Objects.equals(barcode, setting.barcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, barcode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Setting {\n");
    
    sb.append("    algorithm: ").append(toIndentedString(algorithm)).append("\n");
    sb.append("    barcode: ").append(toIndentedString(barcode)).append("\n");
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
}

