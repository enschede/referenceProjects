package nl.marcenschede.springtest.stax.camt053;

import nl.marcenschede.springtest.stax.camt053.xsd.GroupHeader42;
import nl.marcenschede.springtest.stax.camt053.xsd.ReportEntry2;

/**
 * Created by marc on 12/05/15.
 */
public class CamtItem {
    
    private GroupHeader42 groupHeader42;
    private ReportEntry2 reportEntry2;

    public CamtItem(GroupHeader42 groupHeader42, ReportEntry2 reportEntry2) {
        this.groupHeader42 = groupHeader42;
        this.reportEntry2 = reportEntry2;
    }

    public GroupHeader42 getGroupHeader42() {
        return groupHeader42;
    }

    public ReportEntry2 getReportEntry2() {
        return reportEntry2;
    }
}
