package supercompany.androidlab13;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Oona on 18-Apr-16.
 */
public class PlayersXmlParser {

    Players<Player> players;
    private URL url;
    private static final String nameSpace = null;

    public PlayersXmlParser() {
        players = new Players<>();
    }

    //set up the connection and create a new parser, parse the xml
    public Players<Player> handleXml(String url) throws IOException, XmlPullParserException {

        this.url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is, null);
        parser.nextTag();
        is.close();

        players = parseXml(parser);
        return players;
    }

    //check the xml for "player" tags
    public Players<Player> parseXml(XmlPullParser parser) throws XmlPullParserException, IOException {
        Players<Player> myPlayers = new Players<>();

        while(parser.next() != XmlPullParser.END_TAG) {
            if(parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            //Log.d("TAG", "name: " + name);
            if(name.equals("player")) {
                myPlayers.add(readPlayer(parser));
            } else {
                skip(parser);
            }
        }
        return myPlayers;
    }

    //parse the "player" tags further; check for "id" and "name" tags
    public Player readPlayer(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, nameSpace, "player");
        String playerId = null;
        String playerName = null;

        //as long as the next parsing event is not an end tag
        while(parser.next() != XmlPullParser.END_TAG) {

            //continue if the event isn't a start tag (aka it's the text between the tags)
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            //get the name of the tag
            String tagName = parser.getName();

            //if tag name is "id"
            if(tagName.equals("id")) {
                parser.require(XmlPullParser.START_TAG, nameSpace, "id");
                if (parser.next() == XmlPullParser.TEXT) {
                    playerId = parser.getText();
                    //Log.d("TAG", "player id: " + playerId);
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, nameSpace, "id");

            //if tag name is "name"
            } else if(tagName.equals("name")) {
                parser.require(XmlPullParser.START_TAG, nameSpace, "name");
                if (parser.next() == XmlPullParser.TEXT) {
                    playerName = parser.getText();
                    //Log.d("TAG", "player name: " + playerName);
                    parser.nextTag();
                }
                parser.require(XmlPullParser.END_TAG, nameSpace, "name");
            } else {
                skip(parser);
            }
        }
        return new Player(playerId, playerName);
    }

    //skip unwanted tags
    public void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
