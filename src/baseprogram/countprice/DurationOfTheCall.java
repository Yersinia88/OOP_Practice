package baseprogram.countprice;

public class DurationOfTheCall {
    private static final int ONE_MIN_IN_SEC = 60;
    private int min;
    private int sec;


    public DurationOfTheCall(int min, int sec) {
        this.min = min;
        this.sec = sec;
    }

    public int DurationOfTheCallInSec() {
        return min * ONE_MIN_IN_SEC + sec;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
