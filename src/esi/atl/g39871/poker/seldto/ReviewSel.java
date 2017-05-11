package esi.atl.g39871.poker.seldto;

/**
 *
 * @author g39871
 */
public class ReviewSel {



  private int id;

  private int idGame;

  private String namePlayer;

  private int rating;

  private String details;


  public ReviewSel(){
      this(0);
  }

  public ReviewSel(int id){
      this.id=id;
  }

  public ReviewSel(int idGame, String namePlayer, int rating, String details) {
    this(idGame, namePlayer, rating);
    this.details = details;
  }

  public ReviewSel(String namePlayer) {
    this(0,namePlayer);
    
  }

  public ReviewSel(int idGame, String namePlayer) {
    this(0);
    this.idGame = idGame;
    this.namePlayer = namePlayer;

  }

  public ReviewSel(int idGame, String namePlayer, int rating) {
    this(idGame, namePlayer);
    this.rating = rating;
  }

  public int getRating() {
    return rating;
  }

  public String getDetails() {
    return details;
  }

  public int getIdGame() {
    return idGame;
  }

  public String getNamePlayer() {
    return namePlayer;
  }


  public int getId() {
    return id;
  }
}
