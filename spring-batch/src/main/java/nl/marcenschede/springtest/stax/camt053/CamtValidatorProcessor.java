package nl.marcenschede.springtest.stax.camt053;

import nl.marcenschede.springtest.stax.camt053.xsd.CashBalance3;
import nl.marcenschede.springtest.stax.camt053.xsd.ReportEntry2;
import nl.marcenschede.springtest.stax.camt053.xsd.TotalTransactions2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 * Created by marc on 25/05/15.
 */
public class CamtValidatorProcessor implements ItemProcessor<Object, Object> {

    private final static Logger LOGGER = Logger.getLogger(CamtValidatorProcessor.class.getCanonicalName());
    
    private StepExecution stepExecution;
    private ExecutionContext executionContext;
    private BigDecimal sumOfAmt;
    
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getExecutionContext();
        executionContext.put("SumOfAmt", BigDecimal.ZERO);
    }   
    
    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {
        this.stepExecution = chunkContext.getStepContext().getStepExecution();
        this.executionContext = this.stepExecution.getExecutionContext();
        this.sumOfAmt = (BigDecimal)executionContext.get("SumOfAmt");
    }
    
    @Override
    public Object process(Object itemFromReader) throws Exception {
        System.out.printf("itemFromReader.class = [%s]", itemFromReader.getClass().getName());

        if(itemFromReader instanceof CashBalance3) {
            processCashBalans3((CashBalance3)itemFromReader);
        }
        
        if(itemFromReader instanceof TotalTransactions2) {
            TotalTransactions2 totalTransactions2 = (TotalTransactions2)itemFromReader;
            // Do nothing, never seen this element in real life            
        }

        if(itemFromReader instanceof ReportEntry2) {
            processEntry((ReportEntry2)itemFromReader);
            return null;
        }

        return null;
    }

    @AfterChunk
    public void afterChunk() {
        executionContext.put("SumOfAmt", sumOfAmt);
    }

    @AfterStep
    public ExitStatus afterStep() {
        boolean isValidResult = isValidCheck();
        
        return isValidResult ? ExitStatus.COMPLETED : ExitStatus.FAILED;
    }

    /**
     * Validate amount is file by checking beginamount + sum(transactionamount) == endamount.
     * 
     * @return amount checking is valid
     */
    private boolean isValidCheck() {
        BigDecimal prcdAmt = (BigDecimal)executionContext.get("PRCD");
        BigDecimal clbdAmt = (BigDecimal)executionContext.get("CLBD");
        BigDecimal sumOfAmt = (BigDecimal)executionContext.get("SumOfAmt");
        
        return clbdAmt.equals(prcdAmt.add(sumOfAmt));
    }
    
    private void processCashBalans3(CashBalance3 cashBalance3) {
        BigDecimal amount = cashBalance3.getAmt().getValue();
        amount = cashBalance3.getCdtDbtInd().value().equalsIgnoreCase("CRDT") ? amount : amount.negate();

        String type = cashBalance3.getTp().getCdOrPrtry().getCd().value();

        switch (type) {
            case "PRCD":
                executionContext.put("PRCD", amount);
                break;
            case "CLBD":
                executionContext.put("CLBD", amount);
                break;
        }
        
    }

    private void processEntry(ReportEntry2 reportEntry2) {
        BigDecimal amount = reportEntry2.getAmt().getValue();
        amount = reportEntry2.getCdtDbtInd().value().equalsIgnoreCase("CRDT") ? amount : amount.negate();
        
        sumOfAmt = sumOfAmt.add(amount);
    }

}
