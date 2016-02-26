/**
 * Created by danielamaral on 26/02/16.
 */
public class PressureFilter extends FilterFramework {


    public void run() {
        System.out.println("################ Pressure Filter ############");
        try {
            while (true) {
                int ID = getInt();
                writeInt(ID);
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
                        long time = getLong();
                        writeLong(time);
                        break;
                    }
                    /*************************************************************************
                     * Velocidade: velocidade do ar do veículo em nós por hora.
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 001: {
                        double speed = getDouble();
                        writeDouble(speed);
                        break;
                    }
                    /*************************************************************************
                     * Altitude: Distância ao solo do veículo em pés.
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 002: {
                        double altitude = getDouble();
                        // altitude = altitudeToMeter(altitude);
                        writeDouble(altitude);
                        break;
                    }
                    /*************************************************************************
                     * Pressão: Pressão atmosférica à volta do veículo em PSI
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 003: {
                        double pressure = getDouble();
                        /*************************************************************************
                         * Pressure: write to the out system
                         *************************************************************************/
                        writeDouble(pressure);
                        break;
                    }
                    /*************************************************************************
                     * Temperatura: temperatura do casco do veículo em Fahrenheit.
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 004: {
                        double temperature = getDouble();
                        writeDouble(temperature);
                        break;
                    }
                    /*************************************************************************
                     * Pitch: ângulo de elevação do nariz do veículo. Pitch 0 corresponde a uma posição paralela à terra.
                     *  O valor positivo indica que o veículo vai a subir, negativo a descer.
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 005: {
                        double pitch = getDouble();
                        writeDouble(pitch);
                        break;
                    }
                }

            } // while
        }catch (EndOfStreamException ex){}// run
    }

}
