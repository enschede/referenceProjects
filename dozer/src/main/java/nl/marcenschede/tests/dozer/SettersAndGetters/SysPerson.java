package nl.marcenschede.tests.dozer.SettersAndGetters;

/**
 * Created by marc on 19/09/15.
 */
public class SysPerson {
    private String voornaam;
    private SysAddress address = new SysAddress();

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public SysAddress getAddress() {
        return address;
    }

    public void setAddress(SysAddress address) {
        this.address = address;
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    @Override
    public String toString() {
        return "SysPerson{" +
                "voornaam='" + voornaam + '\'' +
                ", address=" + address +
                '}';
    }
}
