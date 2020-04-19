package usecases;

import mybatis.model.Course;
import lombok.Getter;
import lombok.Setter;
import mybatis.dao.CourseMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CoursesMyBatis {
    @Inject
    private CourseMapper courseMapper;

    @Getter
    private List<Course> courses;

    @Getter @Setter
    private Course courseToCreate = new Course();

    @PostConstruct
    public void init() {
        this.loadAllTeams();
    }

    private void loadAllTeams() {
        this.courses = courseMapper.selectAll();
    }

    @Transactional
    public String createCourse() {
        courseMapper.insert(courseToCreate);
        return "/mybatis/courses?faces-redirect=true";
    }
}
