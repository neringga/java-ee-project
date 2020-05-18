package helpers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
@Specializes
public class SuperUniversityName extends UniversityName {
    @Override
    public String getUniversityName() {
        return "Super" + super.getUniversityName();
    }
}
