package selmibenromdhane.sparta_v1.Enumeration;

public enum EnumEquipment {
    BARBELL(1),
    DUMBBELL(2),
    MACHINE(3),
    CABLE(4),
    BODY_ONLY(5);

    private int value;

    private EnumEquipment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (this) {
            case BARBELL:
                return "Barbell";

            case DUMBBELL:
                return "Dumbbells";

            case MACHINE:
                return "Machine";

            case CABLE:
                return "Cable";

            case BODY_ONLY:
                return "Body Only";

            default:
                return null;
        }
    }

    public static EnumEquipment valueOf(int value) {
        switch (value) {
            case 1:
                return BARBELL;
            case 2:
                return DUMBBELL;
            case 3:
                return MACHINE;
            case 4:
                return CABLE;
            case 5:
                return BODY_ONLY;

        }
        return BARBELL;
    }
}
