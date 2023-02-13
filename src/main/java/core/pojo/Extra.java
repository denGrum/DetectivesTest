package core.pojo;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Extra {
  private List<Map<String, Integer>> extraArray;
}
