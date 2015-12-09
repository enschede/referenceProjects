package nl.marcenschede.springtest.step3;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * Created by marc on 10/11/14.
 */
public class MyFieldMapper3 implements FieldSetMapper<GmuVO3> {

    public GmuVO3 mapFieldSet(FieldSet fs) {
        System.out.println("fieldset=" + fs.toString());

        if (fs == null) {
            return null;
        }

        GmuVO3 gmuVO = new GmuVO3();
        gmuVO.setField1(fs.readString("field1"));
        gmuVO.setField2(fs.readString("field2"));
        gmuVO.setField3(fs.readString("field3"));

        return gmuVO;
    }
}