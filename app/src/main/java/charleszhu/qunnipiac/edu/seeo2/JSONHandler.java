package charleszhu.qunnipiac.edu.seeo2;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Created by Charles on 4/22/2017.
 */

public class JSONHandler {

    public JSONHandler(){

    }

    public String getData(String JSONString) throws JSONException {
        JSONObject jo = new JSONObject(JSONString);
        JSONObject jo0 = jo.getJSONObject("decisions");
        JSONObject jo1 = jo0.getJSONObject("carbon");
        return jo1.getString("description");

    }

}
