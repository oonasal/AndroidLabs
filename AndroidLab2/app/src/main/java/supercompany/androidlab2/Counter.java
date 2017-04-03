package supercompany.androidlab2;

/**
 * Created by Oona on 20-Mar-16.
 */
public class Counter {

    public int incrementByOne(int number) {
        int n = number + 1;
        return n;
    }

    public int decrementByOne(int number) {
        if((number - 1) >= 0) {
            int n = number - 1;
            return n;
        }
        return 0;
    }

    public int incrementByTen(int number) {
        int n = number + 10;
        return n;
    }

    public int decrementByTen(int number) {
        if((number - 10) >= 0) {
            int n = number - 10;
            return n;
        }
        return 0;
    }
}
