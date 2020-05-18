package usecases;

import entities.Student;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.StudentDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateStudentDetails implements Serializable {
    private Student student;

    @Inject
    private StudentDAO studentDao;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentDao.findOne(studentId);
    }

    @Transactional
    @LoggedInvocation
    public String updateStudentNumber() {
        try{
            studentDao.update(this.student);
        } catch (OptimisticLockException e) {
            return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "students.xhtml?courseId=" + this.student.getCourse().getId() + "&faces-redirect=true";
    }
}
