package hu.cubixwebshop.catalogservice.openapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import hu.cubixwebshop.catalogservice.openapi.model.ProductDto;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CategoryDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-03T13:33:12.168299443+01:00[Europe/Budapest]")
public class CategoryDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("categoryname")
  private String categoryname;

  @JsonProperty("products")
  @Valid
  private List<ProductDto> products;

  public CategoryDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CategoryDto categoryname(String categoryname) {
    this.categoryname = categoryname;
    return this;
  }

  /**
   * Get categoryname
   * @return categoryname
  */
  
  @Schema(name = "categoryname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getCategoryname() {
    return categoryname;
  }

  public void setCategoryname(String categoryname) {
    this.categoryname = categoryname;
  }

  public void setproducts(List<ProductDto> products) {
    this.products = products;
  }
  public List<ProductDto> getproducts() {
    return this.products;
  }
  /**
   * Get products
   * @return products
  */
  @Valid 
  @Schema(name = "products", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public List<ProductDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDto> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryDto categoryDto = (CategoryDto) o;
    return Objects.equals(this.id, categoryDto.id) &&
            Objects.equals(this.categoryname, categoryDto.categoryname) &&
        Objects.equals(this.products, categoryDto.products);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryname, products);
  }



  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoryname: ").append(toIndentedString(categoryname)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
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

