/******************************************************************************************************************
 * File:FilterTemplate.java
 * Course: 17655
 * Project: Assignment 1
 * Copyright: Copyright (c) 2003 Carnegie Mellon University
 * Versions:
 * 1.0 November 2008 - Initial rewrite of original assignment 1 (ajl).
 * <p>
 * Description:
 * <p>
 * This class serves as a template for creating filters. The details of threading, filter connections, input, and output
 * are contained in the FilterFramework super class. In order to use this template the program should rename the class.
 * The template includes the run() method which is executed when the filter is started.
 * The run() method is the guts of the filter and is where the programmer should put their filter specific code.
 * In the template there is a main read-write loop for reading from the input port of the filter and writing to the
 * output port of the filter. This template assumes that the filter is a "normal" that it both reads and writes data.
 * That is both the input and output ports are used - its input port is connected to a pipe from an up-stream filter and
 * its output port is connected to a pipe to a down-stream filter. In cases where the filter is a source or sink, you
 * should use the SourceFilterTemplate.java or SinkFilterTemplate.java as a starting point for creating source or sink
 * filters.
 * <p>
 * Parameters: 		None
 * <p>
 * Internal Methods:
 * <p>
 * public void run() - this method must be overridden by this class.
 ******************************************************************************************************************/

public class TemperatureFilter extends FilterFramework {
    /**
     * @param value
     * @return
     * @author Lizardo
     * @description Fahrenheit to Celsius
     */
    private double farToCelsius(double value) {
        return ((value - 32.0) * 5.0) / 9.0;
    }

    public void run() {
        System.out.println("################ Temperature Filter ############");
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
                    temperature = this.farToCelsius(temperature);
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

    } // run

} // FilterTemplate