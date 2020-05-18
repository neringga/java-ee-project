package helpers;

import interfaces.IUniversityName;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UniversityName implements IUniversityName {
    @Override
    public String getUniversityName() {
        return "Vilnius University";
    }
}
