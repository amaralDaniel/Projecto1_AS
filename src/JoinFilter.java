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

public class JoinFilter extends FilterFramework {

    public void run() {
        int bytes_read = 0;
        int max_bytes_per_source = 3600;
        int sources = 1;
        byte [] buffer = new byte[max_bytes_per_source*sources];
        System.out.println("################ Join Filter ############");
        while (true) {
            try{
                byte b = ReadFilterInputPort();
                buffer[bytes_read] = b;
                bytes_read++;
                if(bytes_read == max_bytes_per_source*sources){
                    for(int i=0; i<max_bytes_per_source*sources; i++){
                        WriteFilterOutputPort(buffer[i]);
                    }
                }

            } catch (EndOfStreamException e1){
                //System.out.println("ENDED #######################################");
            } // catch

        } // while

    } // run


} // FilterTemplate