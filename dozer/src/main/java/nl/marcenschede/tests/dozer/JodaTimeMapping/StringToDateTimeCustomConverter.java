package nl.marcenschede.tests.dozer.JodaTimeMapping;

import org.dozer.DozerConverter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StringToDateTimeCustomConverter extends DozerConverter<String, DateTime> {

    public StringToDateTimeCustomConverter() {
        super(String.class, DateTime.class);
    }

    @Override
    public DateTime convertTo(String source, DateTime destination) {
        if(source==null) {
            return null;
        } else {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy").withZone(DateTimeZone.forID("Europe/Amsterdam"));

            return DateTime.parse(source, dateTimeFormatter);
        }
    }

    @Override
    public String convertFrom(DateTime source, String destination) {
        if(source==null) {
            return null;
        } else {
            return "F*cked by Dozer";
        }
    }
}
