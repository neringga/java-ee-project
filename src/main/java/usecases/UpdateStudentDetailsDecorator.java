package usecases;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class UpdateStudentDetailsDecorator implements IUpdateStudentDetails {
    @Inject @Delegate @Any
    IUpdateStudentDetails updateStudentDetails;

    @Override
    public String updateStudentNumber() {
        String result = updateStudentDetails.updateStudentNumber();

        if (result.contains("error=optimistic-lock-exception")) {
            System.out.println("Optimistic lock exc has occurred");
        }

        return result;
    }
}
