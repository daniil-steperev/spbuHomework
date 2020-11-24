package group343.stepyrev.util;

/** Класс, реализующий отрезок. */
public class Segment {
    private double startPoint;
    private double endPoint;

    public Segment() {}

    public Segment(double startPoint, double endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public double getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(double startPoint) {
        this.startPoint = startPoint;
    }

    public double getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(double endPoint) {
        this.endPoint = endPoint;
    }
}
