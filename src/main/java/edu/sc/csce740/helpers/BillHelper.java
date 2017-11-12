package edu.sc.csce740.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.exceptions.BillGenerationException;
import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.Fees;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

//TODO: Reset files in mod directory before tests

public class BillHelper {
    private static String feeFile = "resources/data/fees.json";
    private boolean feesLoaded;
    private Fees fees;

    public BillHelper() {
        fees = new Fees();
        try {
            readFees();
            feesLoaded = true;
        } catch (Exception e) {
            feesLoaded = false;
        }
    }

    private void readFees()
            throws IOException {
        //Gets the object that the JSON should match.
        Type feeType = new TypeToken<Fees>(){}.getType();
        //Create the GSON object.
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //Setup the File for IO.
        File file;
        file = new File(feeFile);

        //Read the JSON file into a string and parse the JSON from that String into a Fees object
        fees = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), feeType);

        //If fees is null here, the file was empty. Create an empty Fee instead.
        if(fees == null) {
            fees = new Fees();
        }
    }

    protected Fees getFees() {
        return fees;
    }

    public Bill generateBill()
            throws BillGenerationException {
        Bill returnBill = new Bill();

        if( !feesLoaded ) {
            throw new BillGenerationException();
        }

        return returnBill;
    }


}
