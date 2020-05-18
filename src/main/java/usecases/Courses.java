package usecases;

import entities.Course;
import helpers.UniversityName;
import lombok.Getter;
import lombok.Setter;
import persistence.CourseDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Courses {

    @Inject
    private CourseDAO courseDAO;

    @Inject
    private UniversityName universityName;

    @Getter @Setter
    private Course courseToCreate = new Course();

    @Getter
    private List<Course> courses;

    @PostConstruct
    public void init(){
        loadAllCourses();
    }

    public String getUniversityName() {
        return universityName.getUniversityName();
    }

    @Transactional
    public String createCourse(){
        this.courseDAO.persist(courseToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllCourses(){
        this.courses = courseDAO.getAll();
    }
}
