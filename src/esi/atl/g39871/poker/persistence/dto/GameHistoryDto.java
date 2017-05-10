package esi.atl.g39871.poker.persistence.dto;

/**
 * GameHistory Dto's instanciation
 *
 * @author g39871
 */
public class GameHistoryDto extends EntityDto<Integer> {

  private int gain;

  private String handCategory;

  private int idGame;

  private String namePlayer;

  public GameHistoryDto(int id, int idGame, String namePlayer, int gain, String handCategory) {
    this(idGame, namePlayer, gain, handCategory);
    this.id = id;

  }

  public GameHistoryDto(int id, int idGame, String namePlayer, int gain) {
    this(idGame, namePlayer, gain);
    this.id = id;

  }

  public GameHistoryDto(int idGame, String namePlayer, int gain, String handCategory) {
    this(idGame, namePlayer, gain);
    this.handCategory = handCategory;
  }

  public GameHistoryDto(int idGame, String namePlayer) {
    this.idGame = idGame;
    this.namePlayer = namePlayer;
  }

  public GameHistoryDto(int idGame, String namePlayer, int gain) {
    this(idGame, namePlayer);
    this.gain = gain;
  }

  public int getGain() {
    return gain;
  }

  public void setGain(int gain) {
    this.gain = gain;
  }

  public String getHandCategory() {
    return handCategory;
  }

  public void setHandCategory(String handCategory) {
    this.handCategory = handCategory;
  }

  public int getIdGame() {
    return idGame;
  }

  public String getNamePlayer() {
    return namePlayer;
  }

  @Override
  public String toString() {
    String res = "[GameHistory] ";

    return res;
  }
}
