package CFCoding.Services;

public record Result(String output,int verdict) {
    @Override
    public int verdict() {
        return verdict;
    }

    @Override
    public String output() {
        return output;
    }
}
