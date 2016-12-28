package selmibenromdhane.sparta_v1.manager;


public class ActivityChartDataSet {
    public double value;
    public StepCount stepCount;

    public ActivityChartDataSet(double value, StepCount stepCount) {
        this.value = value;
        this.stepCount = stepCount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public StepCount getStepCount() {
        return stepCount;
    }

    public void setStepCount(StepCount stepCount) {
        this.stepCount = stepCount;
    }
}
