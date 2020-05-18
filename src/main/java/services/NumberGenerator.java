package services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@ApplicationScoped
public class NumberGenerator implements Serializable, INumberGenerator {
    @Futureable
    public Future<Integer> generateNumber() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        final Integer generatedNumber = new Random().nextInt(500);
        return new AsyncResult<>(generatedNumber);
    }
}
