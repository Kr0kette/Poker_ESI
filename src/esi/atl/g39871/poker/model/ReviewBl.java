package esi.atl.g39871.poker.model;

import esi.atl.g39871.poker.database.ReviewDB;
import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.persistence.dto.ReviewDto;
import esi.atl.g39871.poker.seldto.ReviewSel;
import java.util.Collection;

/**
 *
 * @author g39871
 */
public class ReviewBl{
    
  static ReviewDto findById(int id) throws PokerDbException {
    ReviewSel sel = new ReviewSel(id);
    Collection<ReviewDto> col = ReviewDB.getCollection(sel);
    if (col.size() == 1) {
      return col.iterator().next();
    } else {
      return null;
    }


  }


  static Collection<ReviewDto> find(ReviewSel sel) throws PokerDbException {
    return ReviewDB.getCollection(sel);
  }
  


  static int add(ReviewDto review) throws PokerModelException {

    try {
      if (!review.isPersistant()) {
        return ReviewDB.insertDb(review);
      } else {
        throw new PokerModelException(
            "Review: we can't make a persistant object persistant again !");
      }
    } catch (PokerDbException eDB) {
      throw new PokerModelException(eDB.getMessage());
    }

  }



  static void delete(ReviewSel sel) throws PokerModelException {
    try {
      ReviewDB.deleteDb(sel);
    } catch (PokerDbException eDB) {
      throw new PokerModelException(eDB.getMessage());
    }
  }
    
}
