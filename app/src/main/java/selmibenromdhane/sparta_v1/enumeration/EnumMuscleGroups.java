package selmibenromdhane.sparta_v1.enumeration;


public enum EnumMuscleGroups {
    CHEST(1),
    BACK(2),
    SHOULDERS(3),
    BICEPS(4),
    TRICEPS(5),
    LEGS(6),
    ABS(7);

    private int value;

    private EnumMuscleGroups(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (this) {
            case CHEST:
                return "Chest";

            case BACK:
                return "Back";

            case SHOULDERS:
                return "Shoulders";

            case BICEPS:
                return "Biceps";

            case TRICEPS:
                return "Triceps";

            case LEGS:
                return "Legs";

            case ABS:
                return "Abs";

            default:
                return null;
        }
    }

    public static EnumMuscleGroups valueOf(int value) {
        switch (value) {
            case 1:
                return CHEST;
            case 2:
                return BACK;
            case 3:
                return SHOULDERS;
            case 4:
                return BICEPS;
            case 5:
                return TRICEPS;
            case 6:
                return LEGS;
            case 7:
                return ABS;

        }
        return CHEST;
    }

}
