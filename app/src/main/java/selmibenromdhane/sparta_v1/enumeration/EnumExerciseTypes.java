package selmibenromdhane.sparta_v1.Enumeration;


public enum EnumExerciseTypes {
    MUSCLE_GROWTH(1),
    STRENGTH(2),
    STAMINA(3),
    FAT_LOSS(4),
    RIPPING(5),
    FITNESS(6);

    private int value;

    private EnumExerciseTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (this) {
            case MUSCLE_GROWTH:
                return "Muscle Growth";

            case STRENGTH:
                return "Strength";

            case STAMINA:
                return "Stamina";

            case FAT_LOSS:
                return "Fat Loss";

            case RIPPING:
                return "Ripping";

            case FITNESS:
                return "Fitness";

            default:
                return null;
        }
    }

    public static EnumExerciseTypes valueOf(int value) {
        switch (value) {
            case 1:
                return MUSCLE_GROWTH;
            case 2:
                return STRENGTH;
            case 3:
                return STAMINA;
            case 4:
                return FAT_LOSS;
            case 5:
                return RIPPING;
            case 6:
                return FITNESS;

        }
        return MUSCLE_GROWTH;
    }
}
