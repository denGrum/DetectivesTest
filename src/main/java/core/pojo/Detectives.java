package core.pojo;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Detectives {
  @NonNull
  private List<Detective> detectives;
  @NonNull
  private Boolean success;
}
