package java8.time;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by marc on 27/11/14.
 */
public class TimeTests {

  public void execute() {
    localTimeTests();

    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    System.out.println("Current zone = " + zonedDateTime.getZone().toString());
    System.out.println("Current zone datetime = " + zonedDateTime.toString());
    System.out.println("Current zone to Londontime = " + ZonedDateTime.of(zonedDateTime.toLocalDateTime() , ZoneId.of("Europe/London")));

    
  }

  public void localTimeTests() {
    LocalTime localNow = LocalTime.now();
    System.out.println("LocalTime now = " + localNow.toString());

    ZoneId paris = ZoneId.of("Europe/Paris");
    System.out.println("Current zone = " + paris.toString());

    System.out.println("Paris java8.time = " + LocalTime.now(paris).toString());
    System.out.println("Amsterdam java8.time = " + LocalTime.now(ZoneId.of("Europe/Amsterdam")).toString());
    System.out.println("London java8.time = " + LocalTime.now(ZoneId.of("Europe/London")).toString());

    for (String zoneId : ZoneId.getAvailableZoneIds())
      if (zoneId.startsWith("Europe"))
        System.out.println(zoneId.toString());

  }


}
