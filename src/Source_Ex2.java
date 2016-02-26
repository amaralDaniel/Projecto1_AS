import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by danielamaral on 26/02/16.
 */
public class Source_Ex2 extends FilterFramework {

    public void run()
    {

        String fileName = "resources/FlightData.dat";	// Input data file.
        int bytesread = 0;					// Number of bytes read from the input file.
        int byteswritten = 0;				// Number of bytes written to the stream.
        DataInputStream in = null;			// File stream reference.
        byte databyte;					// The byte of data read from the file

        try
        {
            /***********************************************************************************
             *	Here we open the file and write a message to the terminal.
             ***********************************************************************************/

            in = new DataInputStream(new FileInputStream(fileName));
            System.out.println("\n" + this.getName() + "::Source reading file..." );

            /***********************************************************************************
             *	Here we read the data from the file and send it out the filter's output port one
             * 	byte at a time. The loop stops when it encounters an EOFExecption.
             ***********************************************************************************/

            while(true)
            {
                /*
                * Just read bytes
                * **/
                databyte = in.readByte();
                bytesread++;
                WriteFilterOutputPort(databyte);
                byteswritten++;

            } // while

        } //try

        /***********************************************************************************
         *	The following exception is raised when we hit the end of input file. Once we
         * 	reach this point, we close the input file, close the filter ports and exit.
         ***********************************************************************************/

        catch ( EOFException eoferr )
        {
            System.out.println("\n" + this.getName() + "::End of file reached..." );
            try
            {
                in.close();
                ClosePorts();
                System.out.println( "\n" + this.getName() + "::Read file complete, bytes read::" + bytesread + " bytes written: " + byteswritten );

            }
            /***********************************************************************************
             *	The following exception is raised should we have a problem closing the file.
             ***********************************************************************************/
            catch (Exception closeerr)
            {
                System.out.println("\n" + this.getName() + "::Problem closing input data file::" + closeerr);

            } // catch

        } // catch

        /***********************************************************************************
         *	The following exception is raised should we have a problem openinging the file.
         ***********************************************************************************/

        catch ( IOException iox )
        {
            System.out.println("\n" + this.getName() + "::Problem reading input data file::" + iox );

        } // catch

    }
}
