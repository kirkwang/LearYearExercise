package LeapYearMain;

public class Team {

  private String name;
  private Integer score;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public void setNameScore(String name, Integer score) {
    this.name = name;
    this.score = score;
  }


}
