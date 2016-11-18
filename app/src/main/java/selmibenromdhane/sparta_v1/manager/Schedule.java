package selmibenromdhane.sparta_v1.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sooheib on 11/8/16.
 */

public class Schedule {


    int id;
    String course;
    String trainer;
    String hour;


    public static final List<Schedule> ITEMS = new ArrayList<Schedule>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Schedule> ITEM_MAP = new HashMap<String, Schedule>();


    public Schedule(int id, String course, String trainer, String hour) {
        this.id = id;
        this.course = course;
        this.trainer = trainer;
        this.hour = hour;
    }

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getHour() {
        return hour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
