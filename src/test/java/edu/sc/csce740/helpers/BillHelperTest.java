package edu.sc.csce740.helpers;

import edu.sc.csce740.defines.ClassStatus;
import edu.sc.csce740.defines.Constants;
import edu.sc.csce740.defines.TransactionType;
import edu.sc.csce740.exceptions.BillGenerationException;
import edu.sc.csce740.exceptions.BillRetrievalException;
import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.Date;
import edu.sc.csce740.model.Fees;
import edu.sc.csce740.model.Student;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.Transaction;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BillHelperTest {
    private static final double delta = 0.0001;

    @Test
    public void testReadFees() {
        BillHelper billHelper = new BillHelper();
        Fees fees = billHelper.getFees();
        Assert.assertEquals(3, fees.getResidentUnderGradTuition().size());
        Assert.assertEquals(14, fees.getNonResidentUnderGradTuition().size());
        Assert.assertEquals(3, fees.getActiveDutyTuition().size());
        Assert.assertEquals(3, fees.getResidentGraduateTuition().size());
        Assert.assertEquals(4, fees.getNonResidentGraduateTuition().size());
        Assert.assertEquals(17, fees.getGeneralFees().size());
        Assert.assertEquals(18, fees.getCollegeFees().size());
    }
    
    @Test
    public void testReadFeesEmptyFile() {
        // set up
        String feeFile = BillHelper.getFeeFile();
        File file = new File(feeFile + ".empty");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // should never happen
            e.printStackTrace();
        }
        
        BillHelper.setFeeFile(feeFile + ".empty");
        
        BillHelper helper = new BillHelper();
        
        // tear down
        file.delete();
        BillHelper.setFeeFile(feeFile);
    }
    
    @Test
    public void testReadFeesMissingFile() {
        // set up
        String feeFile = BillHelper.getFeeFile();
        BillHelper.setFeeFile("WRONG!!!!");
        
        BillHelper helper = new BillHelper();
        
        // tear down
        BillHelper.setFeeFile(feeFile);
    }

    @Test
    public void testGenerateBill()
            throws BillGenerationException, IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();

        BillHelper billHelper = new BillHelper();
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_FRESHMAN));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.AAS_SOPHOMORE));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.AAS_JUNIOR));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_SENIOR));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_MASTERS));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_PHD));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.AAS_PHD));
    }

    @Test
    public void testGenerateAASCollegeFees()
            throws BillGenerationException, IOException {
        BillHelper billHelper = new BillHelper();

        Course[] courses = {
            new Course("Placeholder Text", "ART EDUCATION 101", 4, false),
            new Course("Placeholder Text", "ART HISTORY 105", 4, false),
            new Course("Placeholder Text", "DANCE 102", 4, false),
            new Course("Placeholder Text", "GEOLOGY 735", 4, false),
            new Course("Placeholder Text", "MARINE SCIENCE 460", 4, false),
            new Course("Placeholder Text", "MARINE SCIENCE 101", 4, false),
            new Course("Placeholder Text", "MEDIA ART 101", 4, false),
            new Course("Placeholder Text", "STUDIO ART 101", 4, false)
        };
        double[] fees = {
            40.0,
            40.0,
            75.0,
            75.0,
            300.0,
            105.0,
            100.0,
            100.0
        };  // = 835.0

        for (int i = 0; i < courses.length; ++i) {
            StudentRecord stuRec = new StudentRecord();
            stuRec.setClassStatus(ClassStatus.JUNIOR);
            stuRec.addCourse(courses[i]);

            Bill bill = new Bill();
            bill = billHelper.generateCollegeCharges(stuRec, bill);
            List<Transaction> transactions = bill.getTransactions();

            Assert.assertEquals(1, transactions.size());
            Assert.assertEquals(fees[i],
                    transactions.get(0).getAmount().doubleValue(), delta);
        }
    }
    
    private StudentRecord createRecord(String stuId, Date transactionDate, String note) {
        StudentRecord rec = new StudentRecord();
        
        Student stu = new Student(stuId, "Jane", "Doe", "", "", "", "", "", "");
        rec.setStudent(stu);
        
        List<Course> courses = new ArrayList<Course>() {{
            new Course("", "CSCE 740", 3, false);
        }};
        rec.setCourses(courses);
        
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(TransactionType.CHARGE, transactionDate, new BigDecimal(1.00), note));
        rec.setTransactions(transactions);
        
        return rec;
    }
    
    /**
     * This method tests the correct path through bill retrieval for bill in relevant range.
     * 
     * @throws IOException
     * @throws BillRetrievalException
     */
    @Test
    public void testRetrieveBill() throws IOException, BillRetrievalException {
        BillHelper billHelper = new BillHelper();
        
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        
        StudentRecord stuRec = createRecord("jdoe", new Date(12, 15, 2017), "Bill Found");
        
        Date start = new Date(12, 14, 2017);
        Date end = new Date(12, 16, 2017);
    	
        Bill bill = billHelper.retrieveBill(stuRec, start, end);
        assertEquals(1, bill.getTransactions().size());
        assertEquals("Bill Found", bill.getTransactions().get(0).getNote());
    }
    
    /**
     * This method tests the correct path through bill retrieval for bill outside relevant range.
     * 
     * @throws IOException
     * @throws BillRetrievalException
     */
    @Test
    public void testRetrieveBillOutOfRange() throws IOException, BillRetrievalException {
        BillHelper billHelper = new BillHelper();
        
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        
        StudentRecord stuRec = createRecord("jdoe", new Date(1, 15, 2017), "Bill Found");
        
        Date start = new Date(12, 14, 2017);
        Date end = new Date(12, 16, 2017);
        
        Bill bill = billHelper.retrieveBill(stuRec, start, end);
        assertEquals(0, bill.getTransactions().size());
    }
    
    /**
     * This method tests the incorrect path through bill retrieval with missing student record
     * 
     * @throws IOException
     * @throws BillRetrievalException
     */
    @Test
    public void testRetrieveBillNoStudent() throws IOException, BillRetrievalException {
        BillHelper billHelper = new BillHelper();
        
        Date start = new Date(1, 15, 2017);
        Date end = new Date(12, 16, 2017);
        
        Bill bill = null;
        try {
            bill = billHelper.retrieveBill(null, start, end);
            assertFalse(true);
        }
        catch (BillRetrievalException e) {
           assertTrue(true);
        }
        
    }
}
