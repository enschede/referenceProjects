package nl.marcenschede.springtest.flatfilereader;

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
