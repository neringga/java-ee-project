package persistence;

import javax.persistence.EntityManager;
import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    void persist(T t);
    void setEM(EntityManager em);
}
