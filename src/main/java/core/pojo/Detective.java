package core.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Detective {
  @NonNull
  @JsonProperty("MainId")
  private Integer mainId;
  @NonNull
  private String firstName;
  @NonNull
  private String lastName;
  @NonNull
  private Boolean violinPlayer;
  @NonNull
  private List<Category> categories;
}
