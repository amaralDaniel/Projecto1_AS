/*Author: Lizardo*/
/*Title: Main Arch #1*/

public class Plumber_Ex3 {
    public static void main(String argv[]) {
        /*
        * Init the components ( filters, sink and source )
        * */
        Source_B_Ex3 sourceB = new Source_B_Ex3();
        JoinFilter joinFilter = new JoinFilter();
        OrderFilter orderFilter = new OrderFilter();
        Sink_Ex3 sink = new Sink_Ex3();

        /*
        * Connect all components
        * Idea: Input.connect(element.output)
        * */
        sink.Connect(orderFilter);
        orderFilter.Connect(joinFilter);
        joinFilter.Connect(sourceB);
        /**
         * Start all component
         * */
        sourceB.start();
        joinFilter.start();
        orderFilter.start();
        sink.start();
        //sourceA.start();
    }

}