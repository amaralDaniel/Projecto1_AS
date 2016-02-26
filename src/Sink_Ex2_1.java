import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by danielamaral on 26/02/16.
 */
public class Sink_Ex2_1 extends FilterFramework {

    public void run() {
        /************************************************************************************
         *	TimeStamp is used to compute time using java.util's Calendar class.
         * 	TimeStampFormat is used to format the time value so that it can be easily printed
         *	to the terminal.
         *************************************************************************************/
        Date TimeStamp = new Date();
        SimpleDateFormat TimeStampFormat = new SimpleDateFormat("yyyy:dd:hh:mm:ss");

        long measurement;                // This is the word used to store all measurements - conversions are illustrated.
        int id;                            // This is the measurement id
        int i;                            // This is a loop counter

        /*************************************************************
         *	First we announce to the world that we are alive...
         **************************************************************/
        System.out.print("\n" + this.getName() + "::Sink Reading \n");
        try {
            while (true) {
                long time = 0;
                double altitude = 0, speed = 0, pressure = 0, pitch = 0, temperature = 0;
                int ID;
                do {
                    ID = getInt();
                    /*************************************************************************
                     * Read data
                     *************************************************************************/
                    switch (ID) {
                        /*************************************************************************
                         * Tempo: Valor em milissegundos desde a Epoch (00:00:00 GMT em 1 de Janeiro, 1970).
                         * Inteiro long
                         * 8 Bytes
                         *************************************************************************/
                        case 000: {
                            time = getLong();
                            Date aux = new Date(time);
                            TimeStamp.setTime(aux.getTime());
                            break;
                        }
                        /*************************************************************************
                         * Velocidade: velocidade do ar do veículo em nós por hora.
                         * Double
                         * 8 Bytes
                         *************************************************************************/
                        case 001: {
                            speed = getDouble();
                            break;
                        }
                        /*************************************************************************
                         * Altitude: Distância ao solo do veículo em pés.
                         * Double
                         * 8 Bytes
                         *************************************************************************/
                        case 002: {
                            altitude = getDouble();
                            break;
                        }
                        /*************************************************************************
                         * Pressão: Pressão atmosférica à volta do veículo em PSI
                         * Double
                         * 8 Bytes
                         *************************************************************************/
                        case 003: {
                            pressure = getDouble();
                            break;
                        }
                        /*************************************************************************
                         * Temperatura: temperatura do casco do veículo em Fahrenheit.
                         * Double
                         * 8 Bytes
                         *************************************************************************/
                        case 004: {
                            temperature = getDouble();
                            break;
                        }
                        /*************************************************************************
                         * Pitch: ângulo de elevação do nariz do veículo. Pitch 0 corresponde a uma posição paralela à terra.
                         *  O valor positivo indica que o veículo vai a subir, negativo a descer.
                         * Double
                         * 8 Bytes
                         *************************************************************************/
                        case 005: {
                            pitch = getDouble();
                            break;
                        }
                        default: {

                        }
                    }
                } while (ID % 4 != 0 || ID == 0);

            /*
            * Second Exercise output Time + Temperature + Altitude
            * */
                System.out.print(TimeStampFormat.format(TimeStamp.getTime()) + "\t" + temperature + "\t" + altitude + "\n");
            } // while

        }catch (EndOfStreamException ex){}
    }
}
