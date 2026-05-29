// Assignment:5
// Author: Liav Lugasi, ID: 213007271

package cardealership;

/**
 * Enum representing the main menu options for the dealership system.
 */
public enum MenuOption {
    SHOW_EMPLOYEES(1),
    SHOW_UNSOLD_CARS(2),
    SELL_CAR(3),
    ADD_CAR(4),
    ADD_MAINTENANCE(5),
    SHOW_HISTORY(6),
    EXIT(7);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    /**
     * Safely converts an integer input into a MenuOption.
     * @param v The integer to convert.
     * @return The corresponding MenuOption, or null if invalid.
     */
    public static MenuOption fromInt(int v) {
        for (MenuOption m : values()) {
            if (m.value == v) return m;
        }
        return null;
    }
}