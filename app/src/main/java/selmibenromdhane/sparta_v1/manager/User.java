package selmibenromdhane.sparta_v1.manager;


public class User {

    private String name, birthDate;
    private int age;
    private int height;
    private double weight;
    private double bmi;
    private double fat;
    private boolean isMale;

    public User(String name, int age, int height, double weight, double bmi, double fat, boolean isMale) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.fat = fat;
        this.isMale = isMale;
    }

    public String getName() {
        return name;
    }

    public void setName(String sName) {
        this.name = sName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
}
