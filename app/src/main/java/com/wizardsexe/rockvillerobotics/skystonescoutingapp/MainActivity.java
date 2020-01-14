package com.wizardsexe.rockvillerobotics.skystonescoutingapp;

import android.app.AlertDialog;
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

public class MainActivity extends AppCompatActivity {
    TextView AllianceLabel, BlueAllianceLabel, RedAllianceLabel, AutonomousLabel, CraterStartLabel, DepotStartLabel,
    SamplingLabel, TeleOpLabel, SilverLabelCargo, SilverCargoNumber, GoldCargoLabel, GoldCargoNumber, SilverDepotLabel,
    SilverDepotNumber, GoldDepotLabel, GoldDepotNumber, EndGameLabel, EndPositionLabel, ScoreLabel, ScoreNumber, SamplingLocationLabel;

    Button RemoveSilverCargo, AddSilverCargo, RemoveGoldCargo, AddGoldCargo, RemoveSilverDepot, AddSilverDepot,
    RemoveGoldDepot, AddGoldDepot, Submit, Reset;

    EditText SheetName, TeamNumber, MatchNumber, HangTime, AdditionalComments, Initials;

    CheckBox AutoLand, AutoTeamMarker, AutoPark, FTAError;

    Spinner SamplingSpinner, EndGameSpinner, SamplingLocationSpinner;

    Switch AllianceSwitch, AutoStartSwitch;

    DataLogger data;

    int score = 0;
    int silverCargoMinerals = 0;
    int goldCargoMinerals = 0;
    int silverDepotMinerals = 0;
    int goldDepotMinerals = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AllianceLabel = (TextView) findViewById(R.id.AllianceLabel);
        BlueAllianceLabel = (TextView) findViewById(R.id.BlueAllianceLabel);
        RedAllianceLabel = (TextView) findViewById(R.id.RedAllianceLabel);
        AutonomousLabel = (TextView) findViewById(R.id.AutonomousLabel);
        CraterStartLabel = (TextView) findViewById(R.id.CraterStartLabel);
        DepotStartLabel = (TextView) findViewById(R.id.DepotStartLabel);
        SamplingLabel = (TextView) findViewById(R.id.SamplingLabel);

        TeleOpLabel = (TextView) findViewById(R.id.TeleOpLabel);
        SilverLabelCargo = (TextView) findViewById(R.id.SilverLabelCargo);
        SilverCargoNumber = (TextView) findViewById(R.id.SilverCargoNumber);
        GoldCargoLabel = (TextView) findViewById(R.id.GoldCargoLabel);
        GoldCargoNumber = (TextView) findViewById(R.id.GoldCargoNumber);
        SilverDepotLabel = (TextView) findViewById(R.id.SilverDepotLabel);
        SilverDepotNumber = (TextView) findViewById(R.id.SilverDepotNumber);
        GoldDepotLabel = (TextView) findViewById(R.id.GoldDepotLabel);
        GoldDepotNumber = (TextView) findViewById(R.id.GoldDepotNumber);
        EndGameLabel = (TextView) findViewById(R.id.EndGameLabel);
        EndPositionLabel = (TextView) findViewById(R.id.EndPositionLabel);
        ScoreLabel = (TextView) findViewById(R.id.ScoreLabel);
        ScoreNumber = (TextView) findViewById(R.id.ScoreNumber);
        SamplingLocationLabel = (TextView) findViewById(R.id.SamplingLocationLabel);

        RemoveSilverCargo = (Button) findViewById(R.id.RemoveSilverCargo);
        AddSilverCargo = (Button) findViewById(R.id.AddSilverCargo);
        RemoveGoldCargo = (Button) findViewById(R.id.RemoveGoldCargo);
        AddGoldCargo = (Button) findViewById(R.id.AddGoldCargo);
        RemoveSilverDepot = (Button) findViewById(R.id.RemoveSilverDepot);
        AddSilverDepot = (Button) findViewById(R.id.AddSilverDepot);
        RemoveGoldDepot = (Button) findViewById(R.id.RemoveGoldDepot);
        AddGoldDepot = (Button) findViewById(R.id.AddGoldDepot);
        Submit = (Button) findViewById(R.id.SubmitButton);
        Reset = (Button) findViewById(R.id.ResetButton);

        SheetName = (EditText) findViewById(R.id.sheetName);
        TeamNumber = (EditText) findViewById(R.id.teamNumber);
        MatchNumber = (EditText) findViewById(R.id.matchNumber);
        HangTime = (EditText) findViewById(R.id.HangTime);
        AdditionalComments = (EditText) findViewById(R.id.AdditionalComments);
        Initials = (EditText) findViewById(R.id.Initials);

        AutoLand = (CheckBox) findViewById(R.id.AutoLand);
        AutoTeamMarker = (CheckBox) findViewById(R.id.AutoTeamMarker);
        AutoPark = (CheckBox) findViewById(R.id.AutoPark);
        FTAError = (CheckBox) findViewById(R.id.FTAError);

        SamplingSpinner = (Spinner) findViewById(R.id.SamplingSpinner);
        ArrayAdapter<CharSequence> samplingAdapter = ArrayAdapter.createFromResource(this,
                R.array.SamplingSpinnerItems, android.R.layout.simple_spinner_item);
        samplingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SamplingSpinner.setAdapter(samplingAdapter);
        SamplingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EndGameSpinner = (Spinner) findViewById(R.id.EndGameSpinner);
        ArrayAdapter<CharSequence> endGameAdapter = ArrayAdapter.createFromResource(this,
                R.array.EndGameSpinner, android.R.layout.simple_spinner_item);
        endGameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EndGameSpinner.setAdapter(endGameAdapter);
        EndGameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SamplingLocationSpinner = (Spinner) findViewById(R.id.LocationSpinner);
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this,
                R.array.SamplingLocation, android.R.layout.simple_spinner_item);
        endGameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SamplingLocationSpinner.setAdapter(locationAdapter);
        SamplingLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        AllianceSwitch = (Switch) findViewById(R.id.AllianceSwitch);
        AutoStartSwitch = (Switch) findViewById(R.id.StartingSwitch);







        int width = (getResources().getDisplayMetrics().widthPixels)-32;

        ViewGroup.LayoutParams layout;
        View[] quarterWidgets = {BlueAllianceLabel, RedAllianceLabel, CraterStartLabel, DepotStartLabel, SilverLabelCargo, SilverCargoNumber, GoldCargoLabel,
                GoldCargoNumber, SilverDepotLabel, SilverDepotNumber, GoldDepotLabel, GoldDepotNumber, RemoveSilverCargo, RemoveGoldCargo, RemoveSilverDepot, RemoveGoldDepot, AddSilverCargo,
                AddGoldCargo, AddSilverDepot, AddGoldDepot, AllianceSwitch, AutoStartSwitch};

        View[] halfWidgets = {Submit, Reset, ScoreLabel, ScoreNumber, SamplingLocationSpinner, SamplingLocationLabel};

        View[] fullWidgets = {SheetName, TeamNumber, MatchNumber, AutonomousLabel, AllianceLabel, AutoLand, AutoTeamMarker, AutoPark, SamplingLabel, SamplingSpinner,
        TeleOpLabel, EndGameLabel, EndPositionLabel, EndGameSpinner, HangTime, FTAError, AdditionalComments, Initials};

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


    public void submit(View view) {
        if(TeamNumber.getText().toString().trim().equals("")){
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
        }else if(MatchNumber.getText().toString().trim().equals("")){
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
        }else if(SheetName.getText().toString().trim().equals("")){
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
        }else if((EndGameSpinner.getSelectedItem().equals("Hung")&&HangTime.getText().toString().equals(""))||(!EndGameSpinner.getSelectedItem().equals("Hung")&&!HangTime.getText().toString().equals(""))){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Can't have a hang without time or time without hang");
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
        }else if((EndGameSpinner.getSelectedItem().equals("hung"))&&(Integer.parseInt(HangTime.getText().toString())>30)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Hang seconds too large");
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
        }else{
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
            data.addField("red");
        }
        if(AutoStartSwitch.isChecked()){
            data.addField("Depot");
        }else{
            data.addField("Crater");
        }
        data.addField(AutoLand.isChecked());
        data.addField(AutoTeamMarker.isChecked());
        data.addField(AutoPark.isChecked());
        data.addField((String) SamplingSpinner.getSelectedItem());
        data.addField((String) SamplingLocationSpinner.getSelectedItem());
        data.addField(silverCargoMinerals);
        data.addField(goldCargoMinerals);
        data.addField(silverDepotMinerals);
        data.addField(goldDepotMinerals);
        data.addField((String) EndGameSpinner.getSelectedItem());
        if(EndGameSpinner.getSelectedItem().equals("hung")){
            data.addField(HangTime.getText().toString());
        }else{
            data.addField(0);
        }

        data.addField(score);
        data.addField(FTAError.isChecked());
        data.addField(AdditionalComments.getText().toString());
        data.addField(Initials.getText().toString());
        data.newLine();
        data.saveDataLogger(this);
        reset();

    }

    public void update(){
        SilverCargoNumber.setText(""+silverCargoMinerals);
        GoldCargoNumber.setText(""+goldCargoMinerals);
        SilverDepotNumber.setText(""+silverDepotMinerals);
        GoldDepotNumber.setText(""+goldDepotMinerals);
        this.calculateScore();
        ScoreNumber.setText(""+score);
    }

    public void reset(){
        TeamNumber.setText("");
        MatchNumber.setText("");
        AutoLand.setChecked(false);
        AutoTeamMarker.setChecked(false);
        AutoPark.setChecked(false);
        SamplingSpinner.setSelection(0);
        SamplingLocationSpinner.setSelection(0);
        silverCargoMinerals = 0;
        goldCargoMinerals = 0;
        silverDepotMinerals = 0;
        goldDepotMinerals = 0;
        EndGameSpinner.setSelection(0);
        HangTime.setText("");
        FTAError.setChecked(false);
        score = 0;
        AdditionalComments.setText("");
        update();
    }

    public void addSilverCargo(View view){
        silverCargoMinerals++;
        update();
    }
    public void subSilverCargo(View view){
        if(silverCargoMinerals>0) {
            silverCargoMinerals--;
            update();
        }
    }
    public void addGoldCargo(View view){
        goldCargoMinerals++;
        update();
    }
    public void subGoldCargo(View view){
        if(goldCargoMinerals>0){
            goldCargoMinerals--;
            update();
        }
    }
    public void addSilverDepot(View view){
        silverDepotMinerals++;
        update();
    }
    public void subSilverDepot(View view){
        if(silverDepotMinerals>0){
            silverDepotMinerals--;
            update();
        }
    }
    public void addGoldDepot(View view){
        goldDepotMinerals++;
        update();
    }
    public void subGoldDepot(View view){
        if(goldDepotMinerals>0){
            goldDepotMinerals--;
            update();
        }
    }
    public void updateClick(View view){
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
    public void calculateScore(){
        score=0;
        if(AutoLand.isChecked()){
            score+=30;
        }
        if(AutoTeamMarker.isChecked()){
            score+=15;
        }
        if(AutoPark.isChecked()){
            score+=10;
        }
        score+=(goldCargoMinerals+silverCargoMinerals)*5;
        score+=(goldDepotMinerals+silverDepotMinerals)*2;
        if(SamplingSpinner.getSelectedItem().equals("1 Knocked Correctly")){
            score+=25;
        }else if(SamplingSpinner.getSelectedItem().equals("2 Knocked Correctly")){
            score += 50;
        }
        if(EndGameSpinner.getSelectedItem().equals("Partially Parked")){
            score+=10;
        }else if(EndGameSpinner.getSelectedItem().equals("Fully Parked")){
            score+= 25;
        }else if(EndGameSpinner.getSelectedItem().equals("Hung")){
            score+= 50;
        }
    }
}
