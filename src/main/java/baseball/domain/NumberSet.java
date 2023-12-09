package baseball.domain;

import baseball.domain.configure.IntConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberSet {
    private static final int NUM_OF_DIGITS = IntConstants.NUMBER_SET_SIZE.getValue();

    private List<Integer> digits;

    public NumberSet(int number) {
        init(number);
        validate();
    }

    private void init(int number) {
        String numberAsString = String.valueOf(number);
        List<Integer> wrapper = Arrays.stream(numberAsString.split(""))
                .mapToInt(Integer::parseInt)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        this.digits = Collections.unmodifiableList(wrapper);
    }

    private void validate() {
        NumberSetValidator.validateSize(this.digits);
        NumberSetValidator.validateEachDigitRange(this.digits);
        NumberSetValidator.validateDuplicate(this.digits);
    }

    private static class NumberSetValidator {

        public static void validateSize(List<Integer> field) {
            if (field.size() != IntConstants.NUMBER_SET_SIZE.getValue()) {
                throw new IllegalArgumentException();
            }
        }

        public static void validateEachDigitRange(List<Integer> field) {
            field.forEach(num -> {
                if (isSmall(IntConstants.DIGIT_MIN.getValue(), num)
                        || isBigger(IntConstants.DIGIT_MAX.getValue(), num)) {
                    throw new IllegalArgumentException();
                }
            });
        }

        private static boolean isSmall(int standard, int toCheck) {
            return toCheck < standard;
        }

        private static boolean isBigger(int standard, int toCheck) {
            return toCheck > standard;
        }

        public static void validateDuplicate(List<Integer> field) {
            Set<Integer> withoutDuplicate = new HashSet<>(field);
            if (withoutDuplicate.size() != field.size()) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int getDigit(int index) {
        return this.digits.get(index);
    }

    public boolean isStrike(int digit, int index) {
        return this.digits.get(index) == digit;
    }

    public boolean isBall(int digit, int index) {
        int sizeOfDigits = size();
        for (int i = index + 1; i < index + sizeOfDigits; i++) {
            if (this.digits.get(i % sizeOfDigits) == digit) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.digits.size();
    }

    @Override
    public String toString() {
        return this.digits
                .stream()
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
