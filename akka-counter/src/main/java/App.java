import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class App {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("app");
        ActorRef counterInc = system.actorOf(CounterActor.props(), "counterInc");
        ActorRef counterInt2 = system.actorOf(CounterActor.props(), "counterInc2");
        ActorRef counterDec = system.actorOf(CounterActor.props(), "counterDec");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                counterInc.tell(new Increasing(1), ActorRef.noSender());
                counterInt2.tell(new Increasing(2), ActorRef.noSender());
                counterDec.tell(new Decreasing(1), ActorRef.noSender());
            }).start();
        }
    }

}
