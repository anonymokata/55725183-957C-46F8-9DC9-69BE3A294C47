package convertions;

enum NumeralType {
    POWER_OF_TEN(3), HALF_POWER_OF_TEN(1);


    private final int maxNumberOfRepetitions;

    NumeralType(final int maxNumberOfRepetitions) {
        this.maxNumberOfRepetitions = maxNumberOfRepetitions;
    }

    public int getMaxNumberOfRepetitions() {
        return maxNumberOfRepetitions;
    }
}
