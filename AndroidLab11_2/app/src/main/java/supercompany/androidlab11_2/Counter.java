package supercompany.androidlab11_2;

/**
 * Created by Oona on 12-Apr-16.
 */
public class Counter {

    private int counterNumber;
    private double percentage;

    public Counter() {
        counterNumber = 0;
        percentage = 0;
    }

    //calculates the progress as a percentage
    public void countProgress(int givenNumber) {
        counterNumber = givenNumber;
        while(counterNumber > 0) {
            percentage = counterNumber / givenNumber;
            //print out percentage in percentageDone field
        }
    }

}
