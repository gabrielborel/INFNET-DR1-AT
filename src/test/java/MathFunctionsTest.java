import net.jqwik.api.*;
import org.INFNET.AT.MathFunctions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathFunctionsTest {
    @Property
    void multiplyByTwoAlwaysReturnsEven(@ForAll int number) {
        int result = MathFunctions.MultiplyByTwo(number);
        assertEquals(0, result % 2, "O resultado deve ser par");
    }

    @Property
    void generateMultiplicationTableElementsAreMultiples(@ForAll("positiveNumbers") int number,
                                                         @ForAll("positiveNumbers") int limit) {
        int[] table = MathFunctions.GenerateMultiplicationTable(number, limit);
        for (int element : table) {
            assertEquals(0, element % number, "Todos os elementos devem ser múltiplos de " + number);
        }
    }

    @Property
    void isPrimeHasOnlyTwoDivisors(@ForAll("primeNumbers") int number) {
        int divisorsCount = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) divisorsCount++;
        }
        assertEquals(2, divisorsCount, "Um número primo deve ter apenas dois divisores: 1 e ele mesmo");
    }

    @Property
    void calculateAverageIsBetweenMinAndMax(@ForAll("arraysOfIntegers") int[] numbers) {
        if (numbers == null || numbers.length == 0) return; // Ignora arrays vazios ou nulos
        double average = MathFunctions.CalculateAverage(numbers);
        double min = Arrays.stream(numbers).min().getAsInt();
        double max = Arrays.stream(numbers).max().getAsInt();
        assertTrue(min <= average && average <= max, "A média deve estar entre o menor e o maior valor");
    }

    @Provide
    Arbitrary<Integer> positiveNumbers() {
        return Arbitraries.integers().between(1, 10000);
    }

    @Provide
    public static Arbitrary<Integer> primeNumbers() {
        return Arbitraries.integers().between(2, 100000).filter(MathFunctions::IsPrime);
    }

    @Provide
    public static Arbitrary<int[]> arraysOfIntegers() {
        return Arbitraries.integers().list().map(list -> list.stream().mapToInt(i -> i).toArray());
    }
}
