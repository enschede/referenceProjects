package nl.marcenschede.tests.dozer.JodaTimeMapping;

import org.dozer.DozerConverter;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StringToLocalDateCustomConverter extends DozerConverter<String, LocalDate> {

    public StringToLocalDateCustomConverter() {
        super(String.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(String source, LocalDate destination) {
        if(source==null) {
            return null;
        } else {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy").withZone(DateTimeZone.forID("Europe/Amsterdam"));

            return LocalDate.parse(source, dateTimeFormatter);
        }
    }

    @Override
    public String convertFrom(LocalDate source, String destination) {
        if(source==null) {
            return null;
        } else {
            return source.toString("dd-MM-yyyy");
        }
    }
}
