package nl.marcenschede.tests.dozer.map2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marc on 19/09/15.
 */
public class SysPerson {
    private String voornaam;
    private Map<String, String> props = new HashMap<String, String>();

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "SysPerson{" +
                "voornaam='" + voornaam + '\'' +
                ", props=" + props +
                '}';
    }
}
