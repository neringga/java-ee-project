package services;

import interceptors.LoggedInvocation;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;
import java.util.concurrent.Future;

@ApplicationScoped
@Alternative
public class ExtremelyFastNumberGenerator extends FastNumberGenerator {
    @LoggedInvocation
    @Override
    @Futureable
    public Future<Integer> generateNumber() {
        return new AsyncResult<>(new Random().nextInt(500));
    }
}
