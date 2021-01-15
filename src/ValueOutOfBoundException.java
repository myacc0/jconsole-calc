public class ValueOutOfBoundException extends Exception {
    @Override
    public String getMessage() {
        return "Допустимы числа в диапозоне [1, 10]";
    }
}
