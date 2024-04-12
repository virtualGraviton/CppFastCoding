package cppFastCoding.util;

public record Result(String output, BaseStat verdict) {
    @Override
    public BaseStat verdict() {
        return verdict;
    }

    @Override
    public String output() {
        return output;
    }
}
