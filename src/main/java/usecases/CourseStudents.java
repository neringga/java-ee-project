package usecases;

import entities.Course;
import entities.Student;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.CourseDAO;
import persistence.StudentDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class CourseStudents implements Serializable {
    @Inject
    private CourseDAO courseDAO;

    @Inject
    private StudentDAO studentDAO;

    @Getter @Setter
    private Course course;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer courseId = Integer.parseInt(requestParameters.get("courseId"));
        this.course = courseDAO.findOne(courseId);
    }

    @Transactional
    @LoggedInvocation
    public String createStudent() {
        studentToCreate.setCourse(this.course);
        studentDAO.persist(studentToCreate);
        return "students?faces-redirect=true&courseId=" + this.course.getId();
    }
}
