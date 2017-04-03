package supercompany.androidlab13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by Oona on 20-Apr-16.
 */
public class MyAdapter extends BaseAdapter implements ListAdapter {

    Players<Player> playersList;
    LayoutInflater inflater;
    Context context;

    public MyAdapter(Context context, Players<Player> playersList) {
        this.context = context;
        this.playersList = playersList;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return playersList.size();
    }

    @Override
    public Object getItem(int position) {
        return playersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView = convertView;
        if(customView == null) {
            customView = this.inflater.inflate(R.layout.custom_row, null);
        }
        Player p = this.playersList.get(position);
        if(p != null) {
            TextView textView = (TextView) customView.findViewById(R.id.playerText);
            textView.setText(p.toString());
        }
        return customView;
    }
}
