package usecases;

import lombok.Getter;
import lombok.Setter;
import mybatis.dao.CourseMapper;
import mybatis.dao.StudentMapper;
import mybatis.model.Course;
import mybatis.model.Student;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class CourseStudentsMyBatis {
    @Inject
    StudentMapper studentMapper;

    @Inject
    CourseMapper courseMapper;

    @Getter @Setter
    private Course course;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @Getter
    private List<Student> students;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer courseId = Integer.parseInt(requestParameters.get("courseId"));

        this.course = courseMapper.selectByPrimaryKey(courseId);
        this.students = studentMapper.selectByCourseId(this.course.getId());
    }

    @Transactional
    public String createStudent() {
        studentToCreate.setCourseId(this.course.getId());
        studentMapper.insert(studentToCreate);
        return "/mybatis/students?faces-redirect=true&courseId=" + this.course.getId();
    }
}
