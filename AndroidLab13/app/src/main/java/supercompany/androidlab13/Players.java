package supercompany.androidlab13;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Oona on 12-Apr-16.
 */
public class Players<Player> extends AbstractList<Player> {

    private ArrayList<Player> list;

    public Players() {
        list = new ArrayList<>();
    }

    @Override
    public Player get(int location) {
        return list.get(location);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(Player object) {
        list.add(object);
        return true;
    }

    @Override
    public Iterator<Player> iterator() {
        return list.iterator();
    }

    /*public void savePlayers() {
        try {
            FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "PlayersFile"));
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(list);
            os.close();
            Log.v("TAG", "File has been written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/




}
