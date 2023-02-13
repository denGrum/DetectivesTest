import com.fasterxml.jackson.databind.json.JsonMapper;
import core.data.JSONPaths;
import core.pojo.Category;
import core.pojo.Detective;
import core.pojo.Detectives;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TestClass {

  private Detectives detectives;

  @SneakyThrows
  @BeforeClass(alwaysRun = true)
  private void beforeMethod() {
    detectives = new JsonMapper().readValue(new File(JSONPaths.DETECTIVES), Detectives.class);
  }

  @Test(
      description = "Количество детективов",
      groups = {"positive"}
  )
  @Description("Проверка корректности количества детективов: от 1 до 3")
  @Owner("Грумеза Д.О.")
  void detectivesCountTest() {
    int actualSize = detectives.getDetectives().size();
    Assert.assertTrue(actualSize > 0);
    Assert.assertTrue(actualSize < 4);
  }

  @Test(
      description = "Основной идентификатор детектива",
      groups = {"positive"}
  )
  @Description("Проверка корректности значения основного идентификатора детектива: 0 - 10")
  @Owner("Грумеза Д.О.")
  void detectivesMainIdTest() {
    boolean actualStatement = detectives.getDetectives().parallelStream().map(Detective::getMainId)
        .allMatch(id -> id >= 0 && id < 11);
    Assert.assertTrue(actualStatement);
  }

  @Test(
      description = "Идентификатор категории детектива",
      groups = {"positive"}
  )
  @Description("Проверка корректности значения идентификатора категории детектива: 1 или 2")
  @Owner("Грумеза Д.О.")
  void detectivesCategoriesIdTest() {
    detectives.getDetectives().forEach(detective -> {
      boolean actualStatement = detective.getCategories().parallelStream()
          .map(Category::getCategoryID).allMatch(id -> id == 1 || id == 2);
      Assert.assertTrue(actualStatement);
    });
  }

  @Test(
      description = "Дополнительные способности детектива первой категории",
      groups = {"positive"}
  )
  @Description("Проверка дополнительных способностей детектива первой категории: не менее 1 способности")
  @Owner("Грумеза Д.О.")
  void firstCategoryExtraTest() {
    detectives.getDetectives().forEach(detective -> {
      detective.getCategories().parallelStream().filter(category -> category.getCategoryID() == 1)
          .forEach(category -> {
            Assert.assertNotNull(category.getExtra());
            Assert.assertFalse(category.getExtra().getExtraArray().isEmpty());
          });
    });
  }

  @Test(
      description = "Шерлок здесь!",
      groups = {"positive"}
  )
  @Description("Проверка присутствия Шерлока на месте и успешности исхода расследования")
  @Owner("Грумеза Д.О.")
  void sherlockResultTest() {
    String expectedName = "Sherlock";
    boolean sherlockIsHere = detectives.getDetectives().parallelStream().map(Detective::getFirstName)
        .anyMatch(name -> name.equals(expectedName));
    Assert.assertTrue(sherlockIsHere);
    Assert.assertTrue(detectives.getSuccess());
  }

  @Test(
      description = "Шерлока нет среди детективов",
      groups = {"negative"}
  )
  @Description("Проверка отсутствия Шерлока на месте и безуспешности исхода расследования")
  @Owner("Грумеза Д.О.")
  void sherlockIsAbsentTest() {
    String expectedName = "Sherlock";
    boolean sherlockIsAbsent = detectives.getDetectives().parallelStream().map(Detective::getFirstName)
        .noneMatch(name -> name.equals(expectedName));
    Assert.assertTrue(sherlockIsAbsent);
    Assert.assertFalse(detectives.getSuccess());
  }
}
