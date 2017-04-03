package supercompany.androidlab3;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Oona on 23-Mar-16.
 */
public class CreatureList<T> extends AbstractList<T> implements Serializable {
    private transient ArrayList<T> list;
    private static final long serialVersionUID = 1L;
    public static String fileName = "creatureListFile.ser";

    public CreatureList() {
        list = new ArrayList<>();
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(list);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        list = (ArrayList<T>) in.readObject();
    }

    @Override
    public void clear() {
        //super.clear();
        list.clear();
    }

    @Override
    public boolean add(T object) {
        list.add((T) object);
        //return super.add(object);
        return true;
    }

    @Override
    public T get(int location) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    public void save(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this);
        os.close();
        fos.close();
    }

    public static CreatureList load(Context context) throws IOException, ClassNotFoundException {
        CreatureList creatureList = null;
        FileInputStream fis = context.openFileInput(fileName);
        ObjectInputStream is = new ObjectInputStream(fis);
        creatureList = (CreatureList) is.readObject();
        is.close();
        fis.close();
        return creatureList;
    }
}
