package app.application;

import app.domain.VergunningAanvraag;
import app.domain.VergunningPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by marc on 22/05/15.
 */
@Controller
public class VerwerkVergunningProcessor {
    
    private final VergunningPolicy vergunningPolicy;
    private final BrievenProcessor brievenProcessor;

    @Autowired
    public VerwerkVergunningProcessor(VergunningPolicy vergunningPolicy, BrievenProcessor brievenProcessor) {
        this.vergunningPolicy = vergunningPolicy;
        this.brievenProcessor = brievenProcessor;
    }

    public void verwerk(VergunningAanvraag vergunningAanvraag) {
        if(vergunningPolicy.isToegestaan(vergunningAanvraag.getGebied(), vergunningAanvraag.getBouwhoogte())) {
            brievenProcessor.verzendVergunning(vergunningAanvraag);
        } else {
            brievenProcessor.verzendAfwijzingsbrief(vergunningAanvraag.getGebied(), vergunningAanvraag.getBouwhoogte());
        }
    }
}
