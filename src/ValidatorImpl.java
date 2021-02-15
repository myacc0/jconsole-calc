public class ValidatorImpl implements Validator {

    @Override
    public boolean checkNumberRange(int value) {
        return value > 0 && value < 11;
    }
}
