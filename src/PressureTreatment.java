import java.util.ArrayList;

/**
 * Created by danielamaral on 26/02/16.
 */
public class PressureTreatment extends FilterFramework {

    ArrayList<DataNode> data = new ArrayList<DataNode>();
    private double previous=0.0,actual=0.0,next=0.0;

    public void run() {
        DataNode tempNode;
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
                        double time = getDouble();
                        tempNode = new DataNode(000, time);
                        data.add(tempNode);
                        //writeLong(time);
                        break;
                    }
                    /*************************************************************************
                     * Velocidade: velocidade do ar do veículo em nós por hora.
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 001: {
                        double speed = getDouble();
                        tempNode = new DataNode(001, speed);
                        data.add(tempNode);
                        //writeDouble(speed);
                        break;
                    }
                    /*************************************************************************
                     * Altitude: Distância ao solo do veículo em pés.
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 002: {
                        double altitude = getDouble();
                        tempNode = new DataNode(002, altitude);
                        data.add(tempNode);
                        //writeDouble(altitude);
                        break;
                    }
                    /*************************************************************************
                     * Pressão: Pressão atmosférica à volta do veículo em PSI
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 003: {
                        double pressure = getDouble();
                        tempNode = new DataNode(003, pressure);
                        data.add(tempNode);
                        //writeDouble(pressure);
                        break;
                    }
                    /*************************************************************************
                     * Temperatura: temperatura do casco do veículo em Fahrenheit.
                     * Double
                     * 8 Bytes
                     *************************************************************************/
                    case 004: {
                        double temperature = getDouble();
                        tempNode = new DataNode(004, temperature);
                        data.add(tempNode);
                        //writeDouble(temperature);
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
                        tempNode = new DataNode(005, pitch);
                        data.add(tempNode);
                        //writeDouble(pitch);
                        break;
                    }
                    default:
                        break;
                }
            } // while
        }catch(EndOfStreamException ex) {
            this.treatment(data);
            System.out.println("DID THE TREATMENT!!!");
        }
    } // run


    void treatment(ArrayList<DataNode> data){

        for(int i=0;i<data.size();i++){
            if(data.get(i).getId()==003){
                if(data.get(i).getDataValue()< 50.0 || data.get(i).getDataValue() > 80.0  ){
                    if(previous!=0.0){
                        for(int j=i;j<data.size();j++){
                            if(data.get(j).getId()==003 && (data.get(j).getDataValue()>50.0 && data.get(j).getDataValue()<80.0)) //valid
                                next=data.get(j).getDataValue();
                        }
                        if(next!=0.0){
                            data.get(i).setDataValue((previous+next)/2);
                            previous = (previous+next)/2;
                        }else{
                            data.get(i).setDataValue(previous);
                        }
                    }else{
                        for(int k=i;k<data.size();k++){
                            if(data.get(k).getId()==003 && (data.get(i).getDataValue()>50.0 && data.get(i).getDataValue()<80.0)){
                                next = data.get(k).getDataValue();
                                data.get(i).setDataValue(next);
                            }
                        }
                    }
                }
            }
        }
    }
}
