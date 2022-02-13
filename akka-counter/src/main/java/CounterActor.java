import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class CounterActor extends AbstractLoggingActor {

    private int counter;

    {
        receive(ReceiveBuilder
                .match(Increasing.class, this::onIncrease)
                .match(Decreasing.class, this::onDecrease)
                .build()
        );
    }

    public static Props props() {
        return Props.create(CounterActor.class);
    }

    private void onIncrease(Increasing increasing) {
        counter += increasing.getValue();
        log().info(getActorRefName() + " => counter (inc) = " + counter);
    }

    private void onDecrease(Decreasing decreasing) {
        counter -= decreasing.getValue();
        log().info(getActorRefName() + " => counter (dec) = " + counter);
    }

    private String getActorRefName() {
        return self().path().name();
    }

}
