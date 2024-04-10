package hwr.oop;

import java.util.HashMap;
import java.util.Map;

public class DoppelkopfGame {
    private Map<Color, Map<Type, Boolean>> cards;

    public DoppelkopfGame() {
        initializeCards();
    }

    private void initializeCards() {
        cards = new HashMap<>();
        for (Color color : Color.values()) {
            cards.put(color, new HashMap<>());
            for (Type type : Type.values()) {
                cards.get(color).put(type, true); // Alle Karten sind zu Beginn vorhanden
            }
        }
    }

    public boolean hasCard(Color color, Type type) {
        if (cards.containsKey(color)) {
            Map<Type, Boolean> typeMap = cards.get(color);
            if (typeMap.containsKey(type)) {
                return typeMap.get(type); // Gibt true zurück, wenn die Karte vorhanden ist, sonst false
            }
        }
        return false;
    }
}
