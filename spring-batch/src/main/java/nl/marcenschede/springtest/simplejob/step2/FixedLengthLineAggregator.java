package nl.marcenschede.springtest.step2;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Marc Enschede ($Author: marce $)
 * @version $Revision: 0 $ $Date: 0 $
 */
 public class FixedLengthLineAggregator implements LineAggregator<Object> {

    private List<String> formats = null;
    
    @Override
    public String aggregate(Object item) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String format : formats) {
            String formatData[] = format.split(",");
            Object value = getPropertyRecursive(item, formatData[0]);
            
            String field = String.format(formatData[1], String.valueOf(value));
            stringBuilder.append(field);
        }

        return stringBuilder.toString();
    }

    /**
     * Haalt property op uit een object, dit kan ook een verwijzend property zijn (property.subproperty.subsubproperty)
     *
     * @param item Object waaruit de property op te halen
     * @param propertyList Naam van de op te halen property, bijv. persoon.adres.straatnaam
     * @return De gevraagde property, null als een van de waarden in het pad null is
     */
    private Object getPropertyRecursive(Object item, String propertyList) {
        return getPropertyRecursive(item, propertyList.split("\\."));
    }

    private Object getPropertyRecursive(Object item, String[] properties) {
        if(item==null)
            return null;
        
        Field prop = ReflectionUtils.findField(item.getClass(), properties[0]);

        ReflectionUtils.makeAccessible(prop);
        Object value =  ReflectionUtils.getField(prop, item);
        
        if(properties.length>1) {
            // Als er een subproperty wordt gevraagd, ga dan recursief deze method in
            String[] sub = Arrays.copyOfRange(properties, 1, properties.length);
            return getPropertyRecursive(value, sub);
        } else {
            return value;
        }
    }
    
    public void setFormats(List<String> formats) {
        this.formats = formats;
    }

}
