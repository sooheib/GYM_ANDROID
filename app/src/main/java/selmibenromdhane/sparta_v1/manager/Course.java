package selmibenromdhane.sparta_v1.manager;

/**
 * Created by Oclemy on 6/5/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class Course {

    int course_crn;
    String course_desc;
    String course_code;
    String course_cover;

    public int getCourse_crn() {
        return course_crn;
    }

    public void setCourse_crn(int course_crn) {
        this.course_crn = course_crn;
    }

    public String getCourse_desc() {
        return course_desc;
    }

    public void setCourse_desc(String course_desc) {
        this.course_desc = course_desc;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_cover() {
        return course_cover;
    }

    public void setCourse_cover(String course_cover) {
        this.course_cover = course_cover;
    }
}
