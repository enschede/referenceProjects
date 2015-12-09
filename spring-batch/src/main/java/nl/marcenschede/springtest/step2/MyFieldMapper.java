package nl.marcenschede.springtest.step2;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * Created by marc on 10/11/14.
 */
public class MyFieldMapper implements FieldSetMapper<GmuVO> {

    public GmuVO mapFieldSet(FieldSet fs) {
        System.out.println("fieldset=" + fs.toString());
        
        if (fs == null) {
            return null;
        }

        GmuVO gmuVO = new GmuVO();
        gmuVO.setField1(fs.readString("field1"));
        gmuVO.setField2(fs.readString("field2"));

        return gmuVO;
    }
}