package nl.marcenschede.springtest.step3;

import nl.marcenschede.springtest.step2.GmuVO;

/**
 * Created by marc on 10/11/14.
 */
public class GmuVO1 extends GmuVO {
    private String field3;

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "GmuVO1{" +
                "field3='" + field3 + '\'' +
                "} " + super.toString();
    }
}
