package usecases;

import interceptors.LoggedInvocation;
import services.INumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateStudentNumber implements Serializable {
    @Inject
    INumberGenerator numberGenerator;

    private Future<Integer> numberGenerationTask = null;

    @LoggedInvocation
    public String generateNewNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        numberGenerationTask = numberGenerator.generateNumber();
        return  "/studentDetails.xhtml?faces-redirect=true&studentId=" + requestParameters.get("studentId");
    }

    public boolean isNumberGenerationRunning() {
        return numberGenerationTask != null && !numberGenerationTask.isDone();
    }

    public String getNumberGenerationStatus() throws ExecutionException, InterruptedException {
        if (numberGenerationTask == null) {
            return null;
        } else if (isNumberGenerationRunning()) {
            return "Number generation in progress";
        }
        return "Suggested number: " + numberGenerationTask.get();
    }
}
