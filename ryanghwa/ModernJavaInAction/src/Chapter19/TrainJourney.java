package Chapter19;

public class TrainJourney {
    public int price;
    public TrainJourney onward;
    public TrainJourney(int p, TrainJourney t){
        this.price =  p;
        this.onward = t;
    }
    static TrainJourney link(TrainJourney a, TrainJourney b){
        if(a==null){
            return b;
        }
        TrainJourney t = a;
        while(t.onward != null){
            t = t.onward;
        }
        t.onward=b;
        return  a;
    }
}
