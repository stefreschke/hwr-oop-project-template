package hwr.oop.group4.todo.ui.controller.menu;

import java.util.Collection;

public class Menu {

    private final String title;
    private final String description;
    private final Collection<Entry> entries;

    public Menu(String title, String description, Collection<Entry> entries) {
        this.title = title;
        this.description = description;
        this.entries = entries;
    }

    @Override
    public String toString() {
        final StringBuilder menuStringBuilder = new StringBuilder()
                .append("\033[1m<==== ").append(title).append(" ====>\033[0m").append(System.lineSeparator());
        if (!description.isBlank()) {
            menuStringBuilder.append(description).append(System.lineSeparator());
        }
        menuStringBuilder.append(System.lineSeparator())
                .append("Commands: ").append(System.lineSeparator());
        entries.forEach(entry -> menuStringBuilder.append(entry.toString("  ")));
        return menuStringBuilder.toString();
    }
}
