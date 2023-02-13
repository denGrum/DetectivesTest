package core.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Category {
  @NonNull
  @JsonProperty("CategoryID")
  private Integer categoryID;
  @NonNull
  @JsonProperty("CategoryName")
  private String categoryName;
  private Extra extra;
}
