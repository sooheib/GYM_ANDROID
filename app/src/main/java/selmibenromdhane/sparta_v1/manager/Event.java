package selmibenromdhane.sparta_v1.manager;

/**
 * Created by sooheib on 11/29/16.
 */

public class Event {

    public String event_id;
    public String event_startDate;
    public String event_endDate;
    public String event_name;
    public String event_cover;
    public String event_location;
    public String event_description;
    public int event_maxCapacity;
    public int event_countReserved;




    public Event() {
    }

    public String getEvent_startDate() {
        return event_startDate;
    }

    public void setEvent_startDate(String event_startDate) {
        this.event_startDate = event_startDate;
    }

    public String getEvent_endDate() {
        return event_endDate;
    }

    public void setEvent_endDate(String event_endDate) {
        this.event_endDate = event_endDate;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_cover() {
        return event_cover;
    }

    public void setEvent_cover(String event_cover) {
        this.event_cover = event_cover;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public int getEvent_maxCapacity() {
        return event_maxCapacity;
    }

    public void setEvent_maxCapacity(int event_maxCapacity) {
        this.event_maxCapacity = event_maxCapacity;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public int getEvent_countReserved() {
        return event_countReserved;
    }

    public void setEvent_countReserved(int event_countReserved) {
        this.event_countReserved = event_countReserved;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
