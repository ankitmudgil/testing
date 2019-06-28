package com.sapient.code;

public class Average {
    private int count;
    private Float avg;

    public Average(int count, Float avg) {
        this.count = count;
        this.avg = avg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "Average{" +
                "avg=" + avg +
                '}';
    }
}
