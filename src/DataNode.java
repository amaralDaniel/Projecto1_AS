/**
 * Created by danielamaral on 26/02/16.
 */
public class DataNode {

    private Integer id;
    private Double dataValue;


    public DataNode(Integer id, Double dataValue) {
        this.id = id;
        this.dataValue = dataValue;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDataValue() {
        return dataValue;
    }

    public void setDataValue(Double dataValue) {
        this.dataValue = dataValue;
    }
}
