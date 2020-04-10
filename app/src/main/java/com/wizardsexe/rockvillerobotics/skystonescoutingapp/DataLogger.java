package com.wizardsexe.rockvillerobotics.skystonescoutingapp;

/**
 * Created by Olavi Kamppari on 9/9/2015.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class DataLogger {
    private String name;
    private int row;
    private Row currentRow;
    private int column;
    private Sheet currentSheet;
    private int lastColumnNumber;
    Workbook wb;
    static String TAG = "ExelLog";

    //constructor for data logger
    public DataLogger(String fileName) {
        //set row, column and name
        this.row = 0;
        this.column = 0;
        this.name = fileName;

        //make sure storage is accessible
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e(TAG, "Storage not available or read only");
        }

    }
    public void createNewWorkbook(){
        //New Workbook
        wb = new HSSFWorkbook();
        currentSheet = wb.createSheet("Sheet1");
        currentRow = currentSheet.createRow(0);
    }
    public boolean saveDataLogger(Context context) {
        //create a file and output stream
        File file = new File(context.getExternalFilesDir(null), name);
        FileOutputStream os = null;


        //try to save file
        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);
            return true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
            return false;
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
            return false;
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    //ALL ADD FIELD METHODS ADD A CELL OF DATA TO THE RIGHT FOR DIFFERENT DATA TYPES
    public void addField(String obj){
        Cell c = currentRow.createCell(column);
        c.setCellValue(obj);
        column++;

    }
    public void addField(Double obj){
        Cell c = currentRow.createCell(column);
        c.setCellValue(obj);
        column++;
    }
    public void addField(boolean obj){
        Cell c = currentRow.createCell(column);
        if(obj){
            c.setCellValue(1);
        }else{
            c.setCellValue(0);
        }
        column++;
    }
    public void addField(Calendar obj){
        Cell c = currentRow.createCell(column);
        c.setCellValue(obj);
        column++;
    }
    public void addField(Date obj){
        Cell c = currentRow.createCell(column);
        c.setCellValue(obj);
        column++;
    }
    public void addField(RichTextString obj){
        Cell c = currentRow.createCell(column);
        c.setCellValue(obj);
        column++;
    }
    public void addField(int obj){
        Cell c = currentRow.createCell(column);
        c.setCellValue(obj);
        column++;
    }

    //new line method moves onto the next row. Each row represents 1 match for 1 robot
    public void newLine() {
        row++;
        this.currentRow = currentSheet.createRow(row);
        column=0;
    }

    //function to check storage
    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    //setup object for adding to an existing excel file
    public void resetAndNextRow(Context context){
        readExcelFile(context);

        currentSheet = wb.getSheetAt(0);
        lastColumnNumber = 0;
        currentRow = currentSheet.getRow(0);
        column = 0;
        int columnFinal = currentRow.getLastCellNum();
        if(columnFinal == - 1){
            setupSpreadsheet();
            currentRow = currentSheet.getRow(0);
        }
        row++;
        row = this.currentSheet.getLastRowNum();
        currentRow = currentSheet.createRow(row);

    }

    //setup header on spreadsheet
    private void setupSpreadsheet(){
        this.addField("Team Number");
        this.addField("Match Number");
        this.addField("Alliance Color");
        this.addField("Starting Location");
        this.addField("Auto Skystones");
        this.addField("Auto Stones");
        this.addField("Auto Placed");
        this.addField("Auto Foundation");
        this.addField("Auto Parked");
        this.addField("Teleop Delivered");
        this.addField("Teleop Placed");
        this.addField("Skyscraper Height");
        this.addField("Capstone Placed");
        this.addField("Capstone Height");
        this.addField("Foundation Out");
        this.addField("End Park");
        this.addField("Score");
        this.addField("FTA Error");
        this.addField("Additional Comments");
        this.addField("Initials");
        this.newLine();

    }

    //import existing excel file that way it can be appended to
    private void readExcelFile(Context context) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            return;
        }
        try{
            // Creating Input Stream
            File file = new File(context.getExternalFilesDir(null), name);
            FileInputStream myInput = new FileInputStream(file);
            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
           wb = new HSSFWorkbook(myFileSystem);

        }catch (Exception e){
            createNewWorkbook();
        }


    }
}