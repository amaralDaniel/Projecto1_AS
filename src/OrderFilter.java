import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by alizardo on 26/02/16.
 */

public class OrderFilter extends FilterFramework {

    /*
    * @author Lizardo
    * @description Array with all loaded data
    * **/
    private ArrayList data = new ArrayList<Measurements>();

    /*
    * @author Lizardo
    * @description Measurement Class of all data
    * **/
    public class Measurements {
        private long time;
        private double altitude;
        private double speed;
        private double temperature;
        private double pressure;
        private double pitch;

        public Measurements() {

        }

        public Measurements(long time, double altitude, double speed, double temperature, double pressure, double pitch) {
            this.setTime(time);
            this.setAltitude(altitude);
            this.setSpeed(speed);
            this.setPressure(pressure);
            this.setPitch(pitch);
            this.setTemperature(temperature);
        }


        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public double getAltitude() {
            return altitude;
        }

        public void setAltitude(double altitude) {
            this.altitude = altitude;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    }

    /**
     * Order function (asc)
     */
    public int sort(ArrayList list) {
        Collections.sort(list, new Comparator<Measurements>() {
            @Override
            public int compare(Measurements p1, Measurements p2) {
                return (int) (p1.time - p2.time); // Ascending
            }

        });
        return 1;
    }

    /*
    * Write array list to byte buffer
    * */
    public int writeArrayList() {
        for (int i = 0; i < this.data.size(); i++) {
            Measurements aux = (Measurements) this.data.get(i);
            writeInt(000);
            writeLong(aux.getTime());
            writeInt(001);
            writeDouble(aux.getSpeed());
            writeInt(002);
            writeDouble(aux.getAltitude());
            writeInt(003);
            writeDouble(aux.getPressure());
            writeInt(004);
            writeDouble(aux.getTemperature());
        }
        return 1;
    }

    public void run() {
        System.out.println("################ Order Filter ############");

        while (true) {

            int ID = 0;
            long time = 0L;
            double speed = 0;
            double pressure = 0;
            double altitude = 0;
            double pitch = 0;
            double temperature = 0;
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
                }
            } while (ID % 4 != 0 || ID == 0); // do while
            Measurements var = new Measurements(time, altitude, speed, temperature, pressure, pitch);
            this.data.add(var);
            /*
            * last element and print all asc order elements
            * */
            // Isto nao esta muito bonito, mas cada ficheiro tem 50 linhas
            int sources = 1;
            int lines = 50 * sources;
            if(this.data.size() == lines){
                sort(this.data);
                writeArrayList();
            }
        } // while
    } // run
} // FilterTemplate
