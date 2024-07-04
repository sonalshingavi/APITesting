package models;

import lombok.Data;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class Order {
    private boolean IsAdult;
    private int ID;
    private int TypeId;
    private int Quantity;
    private String Name;
    private String Email;
    private BigDecimal Price;
    private String Date;
}