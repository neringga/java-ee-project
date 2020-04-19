package persistence;

import entities.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CourseDAO implements DAO<Course> {
    @Inject
    private EntityManager entityManager;

    @Override
    public List<Course> getAll() {
        return entityManager.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    @Override
    public void persist(Course course) {
        this.entityManager.persist(course);
    }

    @Override
    public void setEM(EntityManager em) {
        this.entityManager = em;
    }

    public Course findOne(Integer id) { return entityManager.find(Course.class, id); }
}
