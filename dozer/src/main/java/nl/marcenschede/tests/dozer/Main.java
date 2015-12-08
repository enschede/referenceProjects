package nl.marcenschede.tests.dozer;

import nl.marcenschede.tests.dozer.JodaTimeMapping.TestJodaTime;
import nl.marcenschede.tests.dozer.SettersAndGetters.TestMap4;
import nl.marcenschede.tests.dozer.abstractClass.ListTest;
import nl.marcenschede.tests.dozer.map1.TestMap1;
import nl.marcenschede.tests.dozer.map2.TestMap2;
import nl.marcenschede.tests.dozer.map3.TestMap3;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Created by marc on 19/09/15.
 */
public class Main {
    public static void main(String... args) throws FileNotFoundException, URISyntaxException {
        TestMap1 testMap1 = new TestMap1();
        testMap1.test();

        TestMap2 testMap2 = new TestMap2();
        testMap2.test();

        TestMap3 testMap3 = new TestMap3();
        testMap3.test();

        TestJodaTime testJodaTime = new TestJodaTime();
        testJodaTime.test();

        TestMap4 testMap4 = new TestMap4();
        testMap4.test();

        ListTest listTest = new ListTest();
        listTest.test();
    }
}
