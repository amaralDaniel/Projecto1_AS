/*Author: Lizardo*/
/*Title: Main Arch #1*/

public class Plumber_Ex1 {
    public static void main(String argv[]) {
        /*
        * Init the components ( filters, sink and source )
        * */
        Source_Ex1 source = new Source_Ex1();
        TemperatureFilter temperature_f = new TemperatureFilter();
        AltitudeFilter altitude_f = new AltitudeFilter();
        Sink_Ex1 sink = new Sink_Ex1();

        /*
        * Connect all components
        * Idea: Input.connect(element.output)
        * */
        sink.Connect(altitude_f);
        altitude_f.Connect(temperature_f);
        temperature_f.Connect(source);

        /**
         * Start all component
         * */
        source.start();
        sink.start();
        temperature_f.start();
        altitude_f.start();
    }

}