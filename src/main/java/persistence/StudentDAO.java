package persistence;

import entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class StudentDAO implements DAO<Student> {
    @Inject
    private EntityManager _entityManager;

    @Override
    public List<Student> getAll() {
        return _entityManager.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    @Override
    public void persist(Student student) {
        // Persist takes an entity instance, adds it to the context and makes that instance managed (ie future updates to the entity will be tracked).
        this._entityManager.persist(student);
    }

    public Student findOne(Integer id) {
        return _entityManager.find(Student.class, id);
    }

    public Student update(Student st) {
        return _entityManager.merge(st);
    }

    @Override
    public void setEM(EntityManager em) {
        this._entityManager = em;
    }
}
