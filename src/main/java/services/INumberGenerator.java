package services;

import java.util.concurrent.Future;

public interface INumberGenerator {
    Future<Integer> generateNumber();
}
