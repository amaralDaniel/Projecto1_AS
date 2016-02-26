/**
 * Created by danielamaral on 26/02/16.
 */
public class Plumber_Ex2 {
    public static void main(String argv[]) {
        /*
        * Init the components ( filters, sink and source )
        * */
        Source_Ex2 source = new Source_Ex2();
        TemperatureFilter temperature_f = new TemperatureFilter();
        AltitudeFilter altitude_f = new AltitudeFilter();
        PressureFilter split = new PressureFilter();
        PressureTreatment pressure_treatment = new PressureTreatment();
        PressureSavage pressure_savage = new PressureSavage();
        Sink_Ex2_1 sink1 = new Sink_Ex2_1();
        Sink_Ex2_2 sink2 = new Sink_Ex2_2();

        /*
        * Connect all components
        * Idea: Input.connect(element.output)
        * */

        sink1.Connect(pressure_treatment);
        sink2.Connect(pressure_savage);
        pressure_savage.Connect(split);
        pressure_treatment.Connect2(split);
        split.Connect(altitude_f);
        altitude_f.Connect(temperature_f);
        temperature_f.Connect(source);

        /**
         * Start all component
         * */
        source.start();
        temperature_f.start();
        altitude_f.start();
        split.start();
        pressure_treatment.start();
        pressure_savage.start();

        sink1.start();
        sink2.start();

    }
}
