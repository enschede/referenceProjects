package nl.marcenschede.tests.dozer.SettersAndGetters;

/**
 * Created by marc on 28/09/15.
 */
public class SysAddress {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "SysName{" +
                "city='" + city + '\'' +
                '}';
    }
}
