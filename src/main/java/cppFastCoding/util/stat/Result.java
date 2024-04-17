package cppFastCoding.util.stat;

public record Result(String output, Stat verdict) {
    @Override
    public Stat verdict() {
        return verdict;
    }

    @Override
    public String output() {
        return output;
    }
}
