package nl.marcenschede.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    /**
     * Test commando's
     * curl http://localhost:8080/hallo/marc
     * curl http://localhost:8080/hallo/marc -u user:password
     *
     * @param naam
     * @return
     */
    // Beveiligd, wanwege rule op method
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/hallo/{naam}")
    public String getHello(@PathVariable String naam) {
        return "Hallo " + naam + "!";
    }

    /**
     * Test commando's
     * curl http://localhost:8080/dag/marc
     * curl http://localhost:8080/dag/marc -u user:password
     *
     * @param naam
     * @return
     */
    // Beveiligd, vanwege rule in WebSecurityConfig
    @RequestMapping(method = RequestMethod.GET, value = "/dag/{naam}")
    public String getDag(@PathVariable String naam) {
        return "Dag " + naam + "!";
    }

    /**
     * Test commando's
     * curl http://localhost:8080/totziens/marc
     * curl http://localhost:8080/totziens/marc -u user:password
     *
     * @param naam
     * @return
     */
    // Niet beveiligd
    @RequestMapping(method = RequestMethod.GET, value = "/totziens/{naam}")
    public String getTotziens(@PathVariable String naam) {
        return "Tot ziens " + naam + "!";
    }

    /**
     * Test commando's
     * curl http://localhost:8080/admin/marc
     * curl http://localhost:8080/admin/marc -u user:password
     * curl http://localhost:8080/admin/marc -u admin:password
     *
     * @param naam
     * @return
     */
    // Beveiligd, wanwege rule op method
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/admin/{naam}")
    public String forAdmin(@PathVariable String naam) {
        return "Hallo admin " + naam + "!";
    }

    /*
     * Geen autorisatie als attribuut naam marc is
     */
    @PostAuthorize("returnObject != null")
    @RequestMapping(method = RequestMethod.GET, value = "/adminmarc/{naam}")
    public String forAdminMarc(@PathVariable String naam) {
        return "marc".equalsIgnoreCase(naam) ? null : "Hallo admin " + naam + "!";
    }


}
