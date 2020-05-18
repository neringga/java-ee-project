package rest;

import contracts.StudentDto;
import entities.Student;
import lombok.Getter;
import lombok.Setter;
import persistence.CourseDAO;
import persistence.StudentDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/students")
public class StudentsController {
    @Inject
    @Setter @Getter
    private StudentDAO studentDAO;

    @Inject
    @Setter @Getter
    private CourseDAO courseDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Student student = studentDAO.findOne(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setNumber(student.getNumber());
        studentDto.setCourseId(student.getCourse().getId());

        return Response.ok(studentDto).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(StudentDto studentData) {
        Student newStudent = new Student();
        newStudent.setName(studentData.getName());
        newStudent.setNumber(studentData.getNumber());
        newStudent.setCourse(courseDAO.findOne(studentData.getCourseId()));

        studentDAO.persist(newStudent);

        return Response.ok().build();
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer studentId, StudentDto studentData) {
        try {
            Student existingStudent = studentDAO.findOne(studentId);
            if (existingStudent == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingStudent.setName(studentData.getName());
            existingStudent.setNumber(studentData.getNumber());
            studentDAO.update(existingStudent);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
