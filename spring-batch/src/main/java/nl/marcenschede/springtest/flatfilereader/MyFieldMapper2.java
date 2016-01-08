package nl.marcenschede.springtest.flatfilereader;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * Created by marc on 10/11/14.
 */
public class MyFieldMapper2 implements FieldSetMapper<GmuVO2> {

    public GmuVO2 mapFieldSet(FieldSet fs) {
        System.out.println("fieldset=" + fs.toString());

        if (fs == null) {
            return null;
        }

        GmuVO2 gmuVO = new GmuVO2();
        gmuVO.setField1(fs.readString("field1"));
        gmuVO.setField2(fs.readString("field2"));
        gmuVO.setField4(fs.readString("field3"));

        return gmuVO;
    }
}