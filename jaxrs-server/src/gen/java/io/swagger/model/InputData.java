package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.InputDataSettings;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class InputData   {
  
  private String data = null;
  private InputDataSettings settings = null;

  
  /**
   **/
  public InputData data(String data) {
    this.data = data;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("data")
  public String getData() {
    return data;
  }
  public void setData(String data) {
    this.data = data;
  }

  
  /**
   **/
  public InputData settings(InputDataSettings settings) {
    this.settings = settings;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("settings")
  public InputDataSettings getSettings() {
    return settings;
  }
  public void setSettings(InputDataSettings settings) {
    this.settings = settings;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InputData inputData = (InputData) o;
    return Objects.equals(data, inputData.data) &&
        Objects.equals(settings, inputData.settings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, settings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InputData {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    settings: ").append(toIndentedString(settings)).append("\n");
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

