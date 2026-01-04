package hu.cubixwebshop.catalogservice.openapi.model;

import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import hu.cubixwebshop.catalogservice.openapi.model.CategoryDto;
import java.util.Arrays;

import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * HistoryDataCategoryDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-03T13:33:12.168299443+01:00[Europe/Budapest]")
public class HistoryDataCategoryDto {

  @JsonProperty("data")
  private CategoryDto data;

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
  private RevTypeEnum revType;

  @JsonProperty("revision")
  private Integer revision;

  @JsonProperty("date")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime date;

  public HistoryDataCategoryDto data(CategoryDto data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @Valid 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public CategoryDto getData() {
    return data;
  }

  public void setData(CategoryDto data) {
    this.data = data;
  }

  public HistoryDataCategoryDto revType(RevTypeEnum revType) {
    this.revType = revType;
    return this;
  }

  /**
   * Get revType
   * @return revType
  */
  

  public RevTypeEnum getRevType() {
    return revType;
  }

  public void setRevType(RevTypeEnum revType) {
    this.revType = revType;
  }

  public HistoryDataCategoryDto revision(Integer revision) {
    this.revision = revision;
    return this;
  }

  /**
   * Get revision
   * @return revision
  */

  @ApiModelProperty(value = "")


  public Integer getRevision() {
    return revision;
  }

  public void setRevision(Integer revision) {
    this.revision = revision;
  }

  public HistoryDataCategoryDto date(LocalDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
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
    HistoryDataCategoryDto historyDataCategoryDto = (HistoryDataCategoryDto) o;
    return Objects.equals(this.data, historyDataCategoryDto.data) &&
            Objects.equals(this.revType, historyDataCategoryDto.revType) &&
            Objects.equals(this.revision, historyDataCategoryDto.revision) &&
            Objects.equals(this.date, historyDataCategoryDto.date);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, revType, revision, date);
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
    sb.append("class HistoryDataCategoryDto {\n");
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

