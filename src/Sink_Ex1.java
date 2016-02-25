import java.text.SimpleDateFormat;
import java.util.Calendar;

/******************************************************************************************************************
 * File:SinkFilterTemplate.java
 * Course: 17655
 * Project: Assignment 1
 * Copyright: Copyright (c) 2003 Carnegie Mellon University
 * Versions:
 * 1.0 November 2008 - Initial rewrite of original assignment 1 (ajl).
 * <p>
 * Description:
 * <p>
 * This class serves as a template for creating sink filters. The details of threading, connections writing output
 * are contained in the FilterFramework super class. In order to use this template the program should rename the class.
 * The template includes the run() method which is executed when the filter is started.
 * The run() method is the guts of the filter and is where the programmer should put their filter specific code.
 * In the template there is a main read-write loop for reading from the input port of the filter. The programmer is
 * responsible for writing the data to a file, or device of some kind. This template assumes that the filter is a sink
 * filter that reads data from the input file and writes the output from this filter to a file or device of some kind.
 * In this case, only the input port is used by the filter. In cases where the filter is a standard filter or a source
 * filter, you should use the FilterTemplate.java or the SourceFilterTemplate.java as a starting point for creating
 * standard or source filters.
 * <p>
 * Parameters: 		None
 * <p>
 * Internal Methods:
 * <p>
 * public void run() - this method must be overridden by this class.
 ******************************************************************************************************************/

public class Sink_Ex1 extends FilterFramework {

    public void run() {
        /************************************************************************************
         *	TimeStamp is used to compute time using java.util's Calendar class.
         * 	TimeStampFormat is used to format the time value so that it can be easily printed
         *	to the terminal.
         *************************************************************************************/

        Calendar TimeStamp = Calendar.getInstance();
        SimpleDateFormat TimeStampFormat = new SimpleDateFormat("yyyy:dd::hh:mm:ss");

        long measurement;                // This is the word used to store all measurements - conversions are illustrated.
        int id;                            // This is the measurement id
        int i;                            // This is a loop counter

        /*************************************************************
         *	First we announce to the world that we are alive...
         **************************************************************/

        System.out.print("\n" + this.getName() + "::Sink Reading \n");

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
            } while (ID % 4 != 0 || ID == 0);

            if (ID == 0) {
                TimeStamp.setTimeInMillis(time);
            }
            /*
            * First Exercise output Time + Temperature + Altitude
            * */
            System.out.print(TimeStampFormat.format(TimeStamp.getTime()) + "\t" + temperature + "\t" + altitude);
            System.out.print("\n");

        } // while

    } // run

} // FilterTemplate