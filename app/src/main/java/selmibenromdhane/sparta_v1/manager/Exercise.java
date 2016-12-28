package selmibenromdhane.sparta_v1.manager;

import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;

import selmibenromdhane.sparta_v1.enumeration.EnumEquipment;
import selmibenromdhane.sparta_v1.enumeration.EnumMuscleGroups;


public class Exercise implements Serializable {

    private int id, mechanics;
    private String title, img1, img2;
    private EnumMuscleGroups muscle;
    private EnumEquipment equipment;
    private String other_muscles;
    private ArrayList<Set> sets;
    private int resttime;

    /**
     * Constructor with empty sets list
     *
     * @param cursor the cursor from the db
     */
    public Exercise(Cursor cursor) {
        id = cursor.getInt(0);
        title = cursor.getString(1);
        img1 = cursor.getString(2);
        img2 = cursor.getString(3);
        muscle = EnumMuscleGroups.valueOf(cursor.getInt(4));
        other_muscles = cursor.getString(5);
        mechanics = cursor.getInt(6);
        equipment = EnumEquipment.valueOf(cursor.getInt(7));
        sets = new ArrayList<>();
        resttime = 90;
    }

    /**
     * Constructor for exercise with sets
     *
     * @param cursor    the cursor from the db
     * @param setString the string from the db containing the sets for this exercise
     */
    public Exercise(Cursor cursor, String setString) {
        this(cursor);
        String[] tokens = setString.split("-");
        for (String s : tokens) {
            sets.add(new Set(Integer.valueOf(s)));
        }
    }

    /**
     * Constructor used for the initial creation of the database
     *
     * @param title         the title
     * @param image1        image 1 file name
     * @param image2        image 2 file name
     * @param muscle        main muscle group worked
     * @param other_muscles other muscle worked (e.g shoulders-triceps)
     * @param mechanics     the mechanics type of the exercise
     * @param equipment     equipment needed
     */
    public Exercise(String title, String image1, String image2, EnumMuscleGroups muscle,
                    String other_muscles, int mechanics, EnumEquipment equipment) {
        this.title = title;
        img1 = image1;
        img2 = image2;
        this.muscle = muscle;
        this.other_muscles = other_muscles;
        this.mechanics = mechanics;
        this.equipment = equipment;
    }


    public void addSet(Set set) {
        sets.add(set);
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public int getMechanics() {
        return mechanics;
    }

    public void setMechanics(int mechanics) {
        this.mechanics = mechanics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EnumMuscleGroups getMuscle() {
        return muscle;
    }

    public void setMuscle(EnumMuscleGroups muscle) {
        this.muscle = muscle;
    }

    public EnumEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(EnumEquipment equipment) {
        this.equipment = equipment;
    }

    public String getOther_muscles() {
        return other_muscles;
    }

    public void setOther_muscles(String other_muscles) {
        this.other_muscles = other_muscles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }

    public int getResttime() {
        return resttime;
    }

    public void setResttime(int resttime) {
        this.resttime = resttime;
    }
}
