package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;



/**
 * Parameters available to customize barcodes, stored in setting models
 **/

@ApiModel(description = "Parameters available to customize barcodes, stored in setting models")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class BarcodeParams   {
  


  public enum TypeEnum {
    QR_CODE("qr_code"),
    DATA_MATRIX("data_matrix"),
    PDF_417("pdf_417");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private TypeEnum type = TypeEnum.QR_CODE;
  private Integer width = null;
  private Integer height = null;
  private Integer margin = null;


  public enum EclEnum {
    H("h"),
    L("l"),
    M("m"),
    Q("q");

    private String value;

    EclEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private EclEnum ecl = EclEnum.H;
  private Integer version = null;
  private Boolean compact = null;


  public enum CompactionEnum {
    AUTO("auto"),
    BYTE("byte"),
    NUMERIC("numeric"),
    TEXT("text");

    private String value;

    CompactionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private CompactionEnum compaction = CompactionEnum.AUTO;


  public enum ShapeEnum {
    NONE("force_none"),
    SQUARE("force_square"),
    RECTANGLE("force_rectangle");

    private String value;

    ShapeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private ShapeEnum shape = ShapeEnum.NONE;

  
  /**
   **/
  public BarcodeParams type(TypeEnum type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }
  public void setType(TypeEnum type) {
    this.type = type;
  }

  
  /**
   * minimum: 1.0
   **/
  public BarcodeParams width(Integer width) {
    this.width = width;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("width")
  public Integer getWidth() {
    return width;
  }
  public void setWidth(Integer width) {
    this.width = width;
  }

  
  /**
   * minimum: 1.0
   **/
  public BarcodeParams height(Integer height) {
    this.height = height;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("height")
  public Integer getHeight() {
    return height;
  }
  public void setHeight(Integer height) {
    this.height = height;
  }

  
  /**
   * minimum: 1.0
   **/
  public BarcodeParams margin(Integer margin) {
    this.margin = margin;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("margin")
  public Integer getMargin() {
    return margin;
  }
  public void setMargin(Integer margin) {
    this.margin = margin;
  }

  
  /**
   **/
  public BarcodeParams ecl(EclEnum ecl) {
    this.ecl = ecl;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("ecl")
  public EclEnum getEcl() {
    return ecl;
  }
  public void setEcl(EclEnum ecl) {
    this.ecl = ecl;
  }

  
  /**
   **/
  public BarcodeParams version(Integer version) {
    this.version = version;
    return this;
  }

  
  /*@ApiModelProperty(value = "")
  @JsonProperty("version")
  public Integer getVersion() {
    return version;
  }
  public void setVersion(Integer version) {
    this.version = version;
  }*/

  
  /**
   **/
  public BarcodeParams compact(Boolean compact) {
    this.compact = compact;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("compact")
  public Boolean getCompact() {
    return compact;
  }
  public void setCompact(Boolean compact) {
    this.compact = compact;
  }

  
  /**
   **/
  public BarcodeParams compaction(CompactionEnum compaction) {
    this.compaction = compaction;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("compaction")
  public CompactionEnum getCompaction() {
    return compaction;
  }
  public void setCompaction(CompactionEnum compaction) {
    this.compaction = compaction;
  }

  
  /**
   **/
  public BarcodeParams shape(ShapeEnum shape) {
    this.shape = shape;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("shape")
  public ShapeEnum getShape() {
    return shape;
  }
  public void setShape(ShapeEnum shape) {
    this.shape = shape;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BarcodeParams barcodeParams = (BarcodeParams) o;
    return Objects.equals(type, barcodeParams.type) &&
        Objects.equals(width, barcodeParams.width) &&
        Objects.equals(height, barcodeParams.height) &&
        Objects.equals(margin, barcodeParams.margin) &&
        Objects.equals(ecl, barcodeParams.ecl) &&
        Objects.equals(version, barcodeParams.version) &&
        Objects.equals(compact, barcodeParams.compact) &&
        Objects.equals(compaction, barcodeParams.compaction) &&
        Objects.equals(shape, barcodeParams.shape);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, width, height, margin, ecl, version, compact, compaction, shape);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BarcodeParams {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    margin: ").append(toIndentedString(margin)).append("\n");
    sb.append("    ecl: ").append(toIndentedString(ecl)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    compact: ").append(toIndentedString(compact)).append("\n");
    sb.append("    compaction: ").append(toIndentedString(compaction)).append("\n");
    sb.append("    shape: ").append(toIndentedString(shape)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  
  public Map toMap()
  {
      Map map = new HashMap();

      map.put("type", this.type.toString());
      map.put("width", this.width);
      map.put("height", this.height);
      map.put("margin", this.margin);
      map.put("ecl", this.ecl.toString());
      map.put("version", this.version);
      map.put("compact", this.compact);
      map.put("compaction", this.compaction.toString());
      map.put("shape", this.shape.toString());
      
      return map;
      
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

