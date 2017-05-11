package esi.atl.g39871.poker.persistence.dto;

/**
 *
 * @author g39871
 */
public class ReviewDto extends EntityDto<Integer> {

  private int rating;

  private String details;

  private int idGame;

  private String namePlayer;

  public ReviewDto(int id, int idGame, String namePlayer, int rating, String details) {
    this(idGame, namePlayer, rating, details);
    this.id = id;

  }

  public ReviewDto(int id, int idGame, String namePlayer, int rating) {
    this(idGame, namePlayer, rating);
    this.id = id;

  }

  public ReviewDto(int idGame, String namePlayer, int rating, String details) {
    this(idGame, namePlayer, rating);
    this.details = details;
  }

  public ReviewDto(int idGame, String namePlayer) {
    this.idGame = idGame;
    this.namePlayer = namePlayer;
  }

  public ReviewDto(int idGame, String namePlayer, int rating) {
    this(idGame, namePlayer);
    this.rating = rating;
  }

  public int getRating() {
    return rating;
  }

  public void setRatiing(int rating) {
    this.rating = rating;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public int getIdGame() {
    return idGame;
  }

  public String getNamePlayer() {
    return namePlayer;
  }

  @Override
  public String toString() {
    String res = "[review] ";

    return res;
  }
    
}
