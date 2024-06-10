package hwr.oop.most_impressive_doppelkopf_experience;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class CardGenerator implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  Card d9 = new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0);
  Card d10 = new Card(CardSymbols.TEN, CardColours.TRUMP, 12, "D10", 10);
  Card dj = new Card(CardSymbols.JACK, CardColours.TRUMP, 14, "DJ", 2);
  Card dq = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 18, "DQ", 3);
  Card dk = new Card(CardSymbols.KING, CardColours.TRUMP, 13, "DK", 4);
  Card da = new Card(CardSymbols.ACE, CardColours.TRUMP, 11, "DA", 11);
  Card h9 = new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0);
  Card h10 = new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10);
  Card hj = new Card(CardSymbols.JACK, CardColours.TRUMP, 15, "HJ", 2);
  Card hq = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 19, "HQ", 3);
  Card hk = new Card(CardSymbols.KING, CardColours.HEARTS, 3, "HK", 4);
  Card ha = new Card(CardSymbols.ACE, CardColours.HEARTS, 4, "HA", 11);
  Card s9 = new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0);
  Card s10 = new Card(CardSymbols.TEN, CardColours.SPADES, 1, "S10", 10);
  Card sj = new Card(CardSymbols.JACK, CardColours.TRUMP, 16, "SJ", 2);
  Card sq = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 20, "SQ", 3);
  Card sk = new Card(CardSymbols.KING, CardColours.SPADES, 3, "SK", 4);
  Card sa = new Card(CardSymbols.ACE, CardColours.SPADES, 4, "SA", 11);
  Card c9 = new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0);
  Card c10 = new Card(CardSymbols.TEN, CardColours.CLUBS, 1, "C10", 10);
  Card cj = new Card(CardSymbols.JACK, CardColours.TRUMP, 17, "CJ", 2);
  Card cq = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3);
  Card ck = new Card(CardSymbols.KING, CardColours.CLUBS, 3, "CK", 4);
  Card ca = new Card(CardSymbols.ACE, CardColours.CLUBS, 4, "CA", 11);
  Card d9b = new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0);
  Card d10b = new Card(CardSymbols.TEN, CardColours.TRUMP, 12, "D10", 10);
  Card djb = new Card(CardSymbols.JACK, CardColours.TRUMP, 14, "DJ", 2);
  Card dqb = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 18, "DQ", 3);
  Card dkb = new Card(CardSymbols.KING, CardColours.TRUMP, 13, "DK", 4);
  Card dab = new Card(CardSymbols.ACE, CardColours.TRUMP, 11, "DA", 11);
  Card h9b = new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0);
  Card h10b = new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10);
  Card hjb = new Card(CardSymbols.JACK, CardColours.TRUMP, 15, "HJ", 2);
  Card hqb = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 19, "HQ", 3);
  Card hkb = new Card(CardSymbols.KING, CardColours.HEARTS, 3, "HK", 4);
  Card hab = new Card(CardSymbols.ACE, CardColours.TRUMP, 4, "HA", 11);
  Card s9b = new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0);
  Card s10b = new Card(CardSymbols.TEN, CardColours.SPADES, 1, "S10", 10);
  Card sjb = new Card(CardSymbols.JACK, CardColours.TRUMP, 16, "SJ", 2);
  Card sqb = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 20, "SQ", 3);
  Card skb = new Card(CardSymbols.KING, CardColours.SPADES, 3, "SK", 4);
  Card sab = new Card(CardSymbols.ACE, CardColours.SPADES, 4, "SA", 11);
  Card c9b = new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0);
  Card c10b = new Card(CardSymbols.TEN, CardColours.CLUBS, 1, "C10", 10);
  Card cjb = new Card(CardSymbols.JACK, CardColours.TRUMP, 17, "CJ", 2);
  Card cqb = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3);
  Card ckb = new Card(CardSymbols.KING, CardColours.CLUBS, 3, "CK", 4);
  Card cab = new Card(CardSymbols.ACE, CardColours.CLUBS, 4, "CA", 11);

  public List<Card> generateAllCards() {

    return List.of(
            d9, d10, dj, dq, dk, da,
            h9, h10, hj, hq, hk, ha,
            s9, s10, sj, sq, sk, sa,
            c9, c10, cj, cq, ck, ca,
            d9b, d10b, djb, dqb, dkb, dab,
            h9b, h10b, hjb, hqb, hkb, hab,
            s9b, s10b, sjb, sqb, skb, sab,
            c9b, c10b, cjb, cqb, ckb, cab
            );
    }
}
