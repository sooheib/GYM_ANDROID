package selmibenromdhane.sparta_v1.enumeration;


public enum EnumWeekDays {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private int value;

    private EnumWeekDays(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (this) {

            case MONDAY:
                return "Monday";

            case TUESDAY:
                return "Tuesday";

            case WEDNESDAY:
                return "Wednesday";

            case THURSDAY:
                return "Thursday";

            case FRIDAY:
                return "Friday";

            case SATURDAY:
                return "Saturday";

            case SUNDAY:
                return "Sunday";

            default:
                return null;
        }
    }
}
