package selmibenromdhane.sparta_v1.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sooheib on 11/12/16.
 */

public class Session {

    public int schedule_id;
    public String schedule_i;
    public String day;
    public String startTime;
    public String endTime;
    public String course;
    public String trainer;
    public int course_crn;
    public int teacher_id;
    public String course_cover;
    public String course_desc;
    public int course_maxC;
    public String trainer_photo;
    public String room_name;
    public int countMumber;
    public String startDate;

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String maxCapacity;

    public int getCountMumber() {
        return countMumber;
    }

    public void setCountMumber(int countMumber) {
        this.countMumber = countMumber;
    }

    public String getSchedule_i() {
        return schedule_i;
    }

    public void setSchedule_i(String schedule_i) {
        this.schedule_i = schedule_i;
    }

    public String getCourse_cover() {
        return course_cover;
    }

    public void setCourse_cover(String course_cover) {
        this.course_cover = course_cover;
    }

    public String getCourse_desc() {
        return course_desc;
    }

    public void setCourse_desc(String course_desc) {
        this.course_desc = course_desc;
    }

    public int getCourse_maxC() {
        return course_maxC;
    }

    public void setCourse_maxC(int course_maxC) {
        this.course_maxC = course_maxC;
    }

    public String getTrainer_photo() {
        return trainer_photo;
    }

    public void setTrainer_photo(String trainer_photo) {
        this.trainer_photo = trainer_photo;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getCourse_crn() {
        return course_crn;
    }

    public void setCourse_crn(int course_crn) {
        this.course_crn = course_crn;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public static final List<Session> ITEMS = new ArrayList<Session>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Session> ITEM_MAP = new HashMap<String, Session>();

    public Session() {
    }

    public Session(int schedule_id, String day, String startTime, String endTime, String trainer, String course) {
        this.schedule_id = schedule_id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainer = trainer;
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
