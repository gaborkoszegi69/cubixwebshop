package hu.cubixwebshop.catalogservice.openapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import hu.cubixwebshop.catalogservice.openapi.model.ProductDto;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * HistoryDataProductDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-03T13:33:12.168299443+01:00[Europe/Budapest]")
public class HistoryDataProductDto {

  @JsonProperty("data")
  private ProductDto data;

  /**
   * Gets or Sets revType
   */
  public enum RevTypeEnum {
    ADD("ADD"),
    
    MOD("MOD"),
    
    DEL("DEL");

    private Object value;

    RevTypeEnum(Object value) {
      this.value = value;
    }

    @JsonValue
    public Object getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RevTypeEnum fromValue(Object value) {
      for (RevTypeEnum b : RevTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("revType")
  private JsonNullable<RevTypeEnum> revType = JsonNullable.undefined();

  @JsonProperty("revision")
  private JsonNullable<Object> revision = JsonNullable.undefined();

  @JsonProperty("date")
  private JsonNullable<Object> date = JsonNullable.undefined();

  public HistoryDataProductDto data(ProductDto data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @Valid 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public ProductDto getData() {
    return data;
  }

  public void setData(ProductDto data) {
    this.data = data;
  }

  public HistoryDataProductDto revType(RevTypeEnum revType) {
    this.revType = JsonNullable.of(revType);
    return this;
  }

  /**
   * Get revType
   * @return revType
  */
  
  @Schema(name = "revType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public JsonNullable<RevTypeEnum> getRevType() {
    return revType;
  }

  public void setRevType(JsonNullable<RevTypeEnum> revType) {
    this.revType = revType;
  }

  public HistoryDataProductDto revision(Object revision) {
    this.revision = JsonNullable.of(revision);
    return this;
  }

  /**
   * Get revision
   * @return revision
  */
  
  @Schema(name = "revision", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public JsonNullable<Object> getRevision() {
    return revision;
  }

  public void setRevision(JsonNullable<Object> revision) {
    this.revision = revision;
  }

  public HistoryDataProductDto date(Object date) {
    this.date = JsonNullable.of(date);
    return this;
  }

  /**
   * Get date
   * @return date
  */
  
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public JsonNullable<Object> getDate() {
    return date;
  }

  public void setDate(JsonNullable<Object> date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoryDataProductDto historyDataProductDto = (HistoryDataProductDto) o;
    return Objects.equals(this.data, historyDataProductDto.data) &&
        equalsNullable(this.revType, historyDataProductDto.revType) &&
        equalsNullable(this.revision, historyDataProductDto.revision) &&
        equalsNullable(this.date, historyDataProductDto.date);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, hashCodeNullable(revType), hashCodeNullable(revision), hashCodeNullable(date));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoryDataProductDto {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    revType: ").append(toIndentedString(revType)).append("\n");
    sb.append("    revision: ").append(toIndentedString(revision)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

