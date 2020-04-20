package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "UniClass.findAll", query = "select a from UniClass as a")
})
@Table(name = "UniClass")
@Getter @Setter
public class UniClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "uniClass")
    private List<Student> students = new ArrayList<Student>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniClass uniClass_ = (UniClass) o;
        return Objects.equals(name, uniClass_.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
