package supercompany.androidlab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView messageField;

    private static final String TAG = "logTag";
    private CreatureList<Creature> creatureList = new CreatureList<>();
    private String listOfCreatures = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText textInputField = (EditText) findViewById(R.id.textInputField);
        final RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        final RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final Button addButton = (Button) findViewById(R.id.addButton);
        final Button clearButton = (Button) findViewById(R.id.clearButton);
        messageField = (TextView) findViewById(R.id.messageField);

        //read saved creatures
        readCreaturesFromFile();

        //set event listeners

        //when add button is clicked, check that one of the radio buttons is checked and display a message
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (!(textInputField.getText().toString().equals("") || textInputField.getText().toString().equals(null))) {
                    if (radioButton.isChecked() || radioButton2.isChecked() || radioButton3.isChecked()) {

                        //add the creature to a collection
                        addCreature();

                        //display the creatures on the message field
                        messageField.setText(listOfCreatures);
                    }
                } else {
                    messageField.setText("");
                }
            }
        });

        //when clear button is clicked, clear the message field and the radio buttons
        clearButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                textInputField.setText("");
                radioGroup.clearCheck();

                //clear message field
                messageField.setText("");

                //clear collection
                creatureList.clear();

                //testing
                Iterator iterator = creatureList.iterator();
                String listOfCreatures2 = "";
                while(iterator.hasNext()) {
                    Creature c = (Creature) iterator.next();
                    listOfCreatures2 += c.getCreatureName() + "\n";
                }
                Log.d(TAG, "creatures after clearing: " + listOfCreatures2);
            }
        });
    }

    //create a new creature, add it into a creature list and save the added creatures' names to a string
    public void addCreature() {
        listOfCreatures = "";
        Log.d(TAG, "addCreature called");
        int creatureButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton creatureButton = (RadioButton) findViewById(creatureButtonId);
        String creatureName = creatureButton.getText().toString();

        Creature creature = new Creature(creatureName);

        //adding the new creature into a collection
        creatureList.add(creature);

        //looping through the collection and adding each creature's name to listOfCreatures
        Iterator iterator = creatureList.iterator();
        while(iterator.hasNext()) {
            Creature c = (Creature) iterator.next();
            listOfCreatures += c.getCreatureName() + "\n";
        }
    }

    //read creature list from file and add the creatures to listOfCreatures
    //display the creatures in the message field
    public void readCreaturesFromFile() {
        try {
            CreatureList readCreatures = CreatureList.load(this);
            Iterator iterator = readCreatures.iterator();
            while(iterator.hasNext()) {
                Creature c = (Creature) iterator.next();

                //add each read creature to the collection
                creatureList.add(c);
                listOfCreatures += c.getCreatureName() + "\n";
                Log.d(TAG, c.getCreatureName());
            }

            //display creatures on message field
            messageField.setText(listOfCreatures);

            Log.d(TAG, "done reading");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart called");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause called");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart called");
        super.onRestart();
    }

    @Override
    protected void onStop() {

        try {
            creatureList.save(this);
            Log.d(TAG, "saved");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "onStop called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy called");
        super.onDestroy();
    }
}
