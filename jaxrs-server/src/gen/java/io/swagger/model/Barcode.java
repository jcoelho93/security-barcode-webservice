package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.HATEOASLinks;
import io.swagger.model.Setting;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class Barcode   {
  
  private Long id = null;
  private Setting setting = null;
  private byte[] base64 = null;
  private Date createdAt = null;
  private List<HATEOASLinks> links = new ArrayList<HATEOASLinks>();

  
  /**
   **/
  public Barcode id(Long id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   **/
  public Barcode setting(Setting setting) {
    this.setting = setting;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("setting")
  public Setting getSetting() {
    return setting;
  }
  public void setSetting(Setting setting) {
    this.setting = setting;
  }

  
  /**
   **/
  public Barcode base64(byte[] base64) {
    this.base64 = base64;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("base64")
  public byte[] getBase64() {
    return base64;
  }
  public void setBase64(byte[] base64) {
    this.base64 = base64;
  }

  
  /**
   **/
  public Barcode createdAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("created_at")
  public Date getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  
  /**
   **/
  public Barcode links(List<HATEOASLinks> links) {
    this.links = links;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("links")
  public List<HATEOASLinks> getLinks() {
    return links;
  }
  public void setLinks(List<HATEOASLinks> links) {
    this.links = links;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Barcode barcode = (Barcode) o;
    return Objects.equals(id, barcode.id) &&
        Objects.equals(setting, barcode.setting) &&
        Objects.equals(base64, barcode.base64) &&
        Objects.equals(createdAt, barcode.createdAt) &&
        Objects.equals(links, barcode.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, setting, base64, createdAt, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Barcode {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    setting: ").append(toIndentedString(setting)).append("\n");
    sb.append("    base64: ").append(toIndentedString(base64)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

