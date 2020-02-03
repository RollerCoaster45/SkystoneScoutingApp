package com.wizardsexe.rockvillerobotics.skystonescoutingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.os.Vibrator;

public class MainActivity extends AppCompatActivity {
    TextView AllianceLabel, BlueAllianceLabel, RedAllianceLabel, AutonomousLabel, SkystoneStartLabel,
            FoundationStartLabel, AutoSkystoneDeliveredLabel, AutoStoneDeliveredLabel, AutoStonePlacedLabel,
            AutoSkystoneDeliveredNumber, AutoStoneDeliveredNumber, AutoStonePlacedNumber, TeleOpLabel,
            StoneDeliveredLabel, StoneDeliveredNumber, StonePlacedLabel, StonePlacedNumber, SkyscraperLabel,
            SkyscraperNumber, EndGameLabel, CapstoneHeightLabel, CapstoneHeightNumber, ScoreLabel, ScoreNumber;

    Button AddAutoSkystone, AddAutoStone, SubAutoSkystone, SubAutoStone, AddAutoPlaced, SubAutoPlaced,
            AddTeleopDelivered, SubTeleopDelivered, AddTeleopPlaced, SubTeleopPlaced, AddTeleopHeight, SubTeleopHeight,
            AddCapstoneHeight, SubCapstoneHeight, Reset, Submit;

    EditText SheetName, TeamNumber, MatchNumber, AdditionalComments, Initials;

    CheckBox FoundationMoved, AutoPark, CapstonePlaced, FoundationMovedOut, EndPark, FTAError;

    Switch AllianceSwitch, AutoStartSwitch;

    DataLogger data;

    int score = 0;
    int autoStoneDelivered = 0;
    int autoSkystoneDelivered = 0;
    int autoStonePlaced = 0;
    int teleopStoneDelivered = 0;
    int teleopStonePlaced = 0;
    int skyscraperHeight = 0;
    int capstoneHeight = 0;

    Vibrator v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get instance of Vibrator from current Context
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



        AllianceLabel = (TextView) findViewById(R.id.AllianceLabel);
        BlueAllianceLabel = (TextView) findViewById(R.id.BlueAllianceLabel);
        RedAllianceLabel = (TextView) findViewById(R.id.RedAllianceLabel);
        AutonomousLabel = (TextView) findViewById(R.id.AutonomousLabel);
        SkystoneStartLabel = (TextView) findViewById(R.id.SkystoneStartLabel);
        FoundationStartLabel = (TextView) findViewById(R.id.FoundationStartLabel);
        AutoSkystoneDeliveredLabel = (TextView) findViewById(R.id.AutoSkystoneDeliveredLabel);
        AutoStoneDeliveredLabel = (TextView) findViewById(R.id.AutoStoneDeliveredLabel);
        AutoStonePlacedLabel = (TextView) findViewById(R.id.AutoStonePlacedLabel);
        AutoSkystoneDeliveredNumber = (TextView) findViewById(R.id.AutoSkystoneDeliveredNumber);
        AutoStoneDeliveredNumber = (TextView) findViewById(R.id.AutoStoneDeliveredNumber);
        AutoStonePlacedNumber = (TextView) findViewById(R.id.AutoStonePlacedNumber);

        TeleOpLabel = (TextView) findViewById(R.id.TeleOpLabel);
        StoneDeliveredLabel = (TextView) findViewById(R.id.StoneDeliveredLabel);
        StoneDeliveredNumber = (TextView) findViewById(R.id.StonesDeliveredNumber);
        StonePlacedLabel = (TextView) findViewById(R.id.StonePlacedLabel);
        StonePlacedNumber = (TextView) findViewById(R.id.StonesPlacedNumber);
        SkyscraperLabel = (TextView) findViewById(R.id.SkyscraperHeightLabel);
        SkyscraperNumber = (TextView) findViewById(R.id.SkyscraperHeightNumber);

        EndGameLabel = (TextView) findViewById(R.id.EndGameLabel);
        CapstoneHeightLabel = (TextView) findViewById(R.id.CapstoneHeightLabel);
        CapstoneHeightNumber = (TextView) findViewById(R.id.CapstoneHeightNumber);
        ScoreLabel = (TextView) findViewById(R.id.ScoreLabel);
        ScoreNumber = (TextView) findViewById(R.id.ScoreNumber);

        AddAutoSkystone = (Button) findViewById(R.id.AddAutoSkystonesDelivered);
        SubAutoSkystone = (Button) findViewById(R.id.RemoveAutoSkystoneDelivered);
        AddAutoStone = (Button) findViewById(R.id.AddAutoStonesDelivered);
        SubAutoStone = (Button) findViewById(R.id.RemoveAutoStoneDelivered);
        AddAutoPlaced = (Button) findViewById(R.id.AddAutoStonePlaced);
        SubAutoPlaced = (Button) findViewById(R.id.RemoveAutoStonePlaced);
        AddTeleopDelivered = (Button) findViewById(R.id.AddStonesDelivered);
        SubTeleopDelivered = (Button) findViewById(R.id.RemoveStonesDelivered);
        AddTeleopPlaced = (Button) findViewById(R.id.AddStonesPlaced);
        SubTeleopPlaced = (Button) findViewById(R.id.RemoveStonesPlaced);
        AddTeleopHeight = (Button) findViewById(R.id.AddSkyscraperHeight);
        SubTeleopHeight = (Button) findViewById(R.id.RemoveSkyscraperHeight);
        AddCapstoneHeight = (Button) findViewById(R.id.AddCapstoneHeight);
        SubCapstoneHeight = (Button) findViewById(R.id.RemoveCapstoneHeight);
        Submit = (Button) findViewById(R.id.SubmitButton);
        Reset = (Button) findViewById(R.id.ResetButton);

        SheetName = (EditText) findViewById(R.id.sheetName);
        TeamNumber = (EditText) findViewById(R.id.teamNumber);
        MatchNumber = (EditText) findViewById(R.id.matchNumber);
        AdditionalComments = (EditText) findViewById(R.id.AdditionalComments);
        Initials = (EditText) findViewById(R.id.Initials);

        FoundationMoved = (CheckBox) findViewById(R.id.FoundationMoved);
        CapstonePlaced = (CheckBox) findViewById(R.id.CapstonePlaced);
        AutoPark = (CheckBox) findViewById(R.id.AutoParked);
        FoundationMovedOut = (CheckBox) findViewById(R.id.FoundationMovedOut);
        EndPark = (CheckBox) findViewById(R.id.EndPark);
        FTAError = (CheckBox) findViewById(R.id.FTAError);

        AllianceSwitch = (Switch) findViewById(R.id.AllianceSwitch);
        AutoStartSwitch = (Switch) findViewById(R.id.StartingSwitch);

        int width = (getResources().getDisplayMetrics().widthPixels)-32;

        ViewGroup.LayoutParams layout;
        View[] quarterWidgets = {BlueAllianceLabel, RedAllianceLabel, SkystoneStartLabel,
                FoundationStartLabel, AutoSkystoneDeliveredLabel, AutoStoneDeliveredLabel, AutoStonePlacedLabel,
                AutoSkystoneDeliveredNumber, AutoStoneDeliveredNumber, AutoStonePlacedNumber, StoneDeliveredLabel, StoneDeliveredNumber, StonePlacedLabel, StonePlacedNumber, SkyscraperLabel,
                SkyscraperNumber,CapstoneHeightLabel, CapstoneHeightNumber, AddAutoSkystone, AddAutoStone, SubAutoSkystone, SubAutoStone, AddAutoPlaced, SubAutoPlaced,
                AddTeleopDelivered, SubTeleopDelivered, AddTeleopPlaced, SubTeleopPlaced, AddTeleopHeight, SubTeleopHeight,
                AddCapstoneHeight, SubCapstoneHeight, AllianceSwitch, AutoStartSwitch};

        View[] halfWidgets = {Submit, Reset, ScoreLabel, ScoreNumber};

        View[] fullWidgets = {FoundationMoved, AutoPark, CapstonePlaced, FoundationMovedOut, EndPark, FTAError, SheetName, TeamNumber, MatchNumber, AdditionalComments, Initials,
                AllianceLabel, AutonomousLabel, TeleOpLabel, EndGameLabel};

        for(View widget:quarterWidgets){
            layout = widget.getLayoutParams();
            layout.width = width/4;
            widget.setLayoutParams(layout);
        }for(View widget:halfWidgets){
            layout = widget.getLayoutParams();
            layout.width = width/2;
            widget.setLayoutParams(layout);

        }
        for(View widget:fullWidgets){
            layout = widget.getLayoutParams();
            layout.width = width;
            widget.setLayoutParams(layout);
        }

    }
    public void update(){
        score=0;
        score+=autoSkystoneDelivered*10;
        AutoSkystoneDeliveredNumber.setText(""+autoSkystoneDelivered);

        score+=autoStoneDelivered*2;
        AutoStoneDeliveredNumber.setText(""+autoStoneDelivered);

        score+=autoStonePlaced*4;
        AutoStonePlacedNumber.setText(""+autoStonePlaced);

        if (AutoPark.isChecked()) {
            score+=5;
        }
        if(FoundationMoved.isChecked()){
            score+=10;
        }

        score+=teleopStoneDelivered*1;
        StoneDeliveredNumber.setText(""+teleopStoneDelivered);

        score+=teleopStonePlaced*1;
        StonePlacedNumber.setText(""+teleopStonePlaced);

        score+=skyscraperHeight*2;
        SkyscraperNumber.setText(""+skyscraperHeight);

        if(CapstonePlaced.isChecked()){
            score+=5;
        }

        score+=capstoneHeight*1;
        CapstoneHeightNumber.setText(""+capstoneHeight);

        if(FoundationMovedOut.isChecked()){
            score+=15;
        }

        if(EndPark.isChecked()){
            score+=5;
        }

        ScoreNumber.setText(""+score);


    }

    //AUTO FUNCTIONS
    public void subAutoSkystone(View view){
        if(autoSkystoneDelivered>0){
            autoSkystoneDelivered--;
        }
        // Vibrate for 400 milliseconds
        v.vibrate(100);
        update();
    }
    public void addAutoSkystone(View view){
        if(autoSkystoneDelivered<2&&autoSkystoneDelivered+autoStoneDelivered<6){
            autoSkystoneDelivered++;
        }

        v.vibrate(100);
        update();
    }
    public void subAutoStone(View view){
        if(autoStoneDelivered>0){
            autoStoneDelivered--;
        }
        // Vibrate for 400 milliseconds
        v.vibrate(100);
        update();
    }
    public void addAutoStone(View view){
        if(autoStoneDelivered+autoSkystoneDelivered<6){
            autoStoneDelivered++;
        }

        v.vibrate(100);
        update();
    }
    public void subAutoPlaced(View view){
        if(autoStonePlaced>0){
            autoStonePlaced--;
            teleopStonePlaced--;
        }
        // Vibrate for 400 milliseconds
        v.vibrate(100);
        update();
    }
    public void addAutoPlaced(View view){
        if(autoStonePlaced<autoStoneDelivered+autoSkystoneDelivered){
            autoStonePlaced++;
            teleopStonePlaced++;
        }

        v.vibrate(100);
        update();
    }
    public void updateClick(View view){
        v.vibrate(100);
        update();
    }

    //TELEOP FUNCTIONS
    public void subTeleopDelivered(View view){
        if(teleopStoneDelivered>0){
            teleopStoneDelivered--;
        }

        v.vibrate(100);
        update();
    }
    public void addTeleopDelivered(View view){
        teleopStoneDelivered++;
        v.vibrate(100);
        update();
    }
    public void subStonesPlaced(View view){
        teleopStonePlaced--;
        v.vibrate(100);
        update();
    }
    public void addStonesPlaced(View view){
        teleopStonePlaced++;
        v.vibrate(100);
        update();
    }
    public void subSkyscraperHeight(View view){
        if(skyscraperHeight<0){
            skyscraperHeight--;
        }
        v.vibrate(100);
        update();
    }
    public void addSkyscraperHeight(View view){
        skyscraperHeight++;
        v.vibrate(100);
        update();
    }


    //ENDGAME FUNCTIONS
    public void subCapstoneHeight(View view){
        if(capstoneHeight>0){
            capstoneHeight--;
        }
        v.vibrate(100);
        update();
    }
    public void addCapstoneHeight(View view){
        if(CapstonePlaced.isChecked()){
            capstoneHeight++;
        }
        v.vibrate(100);
        update();
    }


    public void resetButtonClick(View view){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you would like to reset?");
        builder1.setCancelable(true);
        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder1.setPositiveButton(
                "Reset",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        reset();
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void reset(){
        score = 0;
        autoStoneDelivered = 0;
        autoSkystoneDelivered = 0;
        autoStonePlaced = 0;
        teleopStoneDelivered = 0;
        teleopStonePlaced = 0;
        skyscraperHeight = 0;
        capstoneHeight = 0;
        TeamNumber.setText("");
        MatchNumber.setText("");
        AutoSkystoneDeliveredNumber.setText(""+0);
        AutoStoneDeliveredNumber.setText(""+0);
        AutoStonePlacedNumber.setText(""+0);
        FoundationMoved.setChecked(false);
        AutoPark.setChecked(false);
        StoneDeliveredNumber.setText(""+0);
        StonePlacedNumber.setText(""+0);
        SkyscraperNumber.setText(""+0);
        CapstonePlaced.setChecked(false);
        CapstoneHeightNumber.setText(""+0);
        FoundationMovedOut.setChecked(false);
        EndPark.setChecked(false);
        FTAError.setChecked(false);
        AdditionalComments.setText("");
    }

    public void submit(View view) {
        if (TeamNumber.getText().toString().trim().equals("")) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No Team Number");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if (MatchNumber.getText().toString().trim().equals("")) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No match Number");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if (SheetName.getText().toString().trim().equals("")) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No Sheet Name");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Are you sure you would like to submit?");
            builder1.setCancelable(true);
            builder1.setNegativeButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder1.setPositiveButton(
                    "Submit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dataSubmit();
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public void dataSubmit(){
        data = new DataLogger(SheetName.getText().toString() + ".xls");
        data.resetAndNextRow(this);
        data.addField(Integer.parseInt(TeamNumber.getText().toString()));
        data.addField(Integer.parseInt(MatchNumber.getText().toString()));
        if (AllianceSwitch.isChecked()) {
            data.addField("Blue");
        } else {
            data.addField("Red");
        }
        if(AutoStartSwitch.isChecked()){
            data.addField("Foundation");
        }else{
            data.addField("Skystone");
        }
        data.addField(autoSkystoneDelivered);
        data.addField(autoStoneDelivered);
        data.addField(autoStonePlaced);
        data.addField(FoundationMoved.isChecked());
        data.addField(AutoPark.isChecked());
        data.addField(teleopStoneDelivered);
        data.addField(teleopStonePlaced);
        data.addField(skyscraperHeight);
        data.addField(CapstonePlaced.isChecked());
        data.addField(capstoneHeight);
        data.addField(FoundationMovedOut.isChecked());
        data.addField(EndPark.isChecked());
        data.addField(score);
        data.addField(FTAError.isChecked());
        data.addField(AdditionalComments.getText().toString());
        data.addField(Initials.getText().toString());
        data.newLine();
        data.saveDataLogger(this);
        reset();
    }



}
