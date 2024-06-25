package store.models;

public enum Category {
    FOOD(1.20), NON_FOOD(1.10);

    private double markUp;

    Category(double markUp) {
        this.markUp = markUp;
    }

    public double getMarkUp() {
        return markUp;
    }
}
