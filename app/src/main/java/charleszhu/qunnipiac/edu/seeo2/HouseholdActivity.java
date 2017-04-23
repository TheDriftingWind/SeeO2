package charleszhu.qunnipiac.edu.seeo2;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HouseholdActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private JSONHandler handler = new JSONHandler();
    private DetailFragment details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);
    }

    public void onResults(View v) {
        EditText electric = (EditText) findViewById(R.id.electricField);
        EditText gas = (EditText) findViewById(R.id.gasField);
        EditText bath = (EditText) findViewById(R.id.bathField);

        String inputElectric = electric.getText().toString();
        String inputGas = gas.getText().toString();
        String inputBath = bath.getText().toString();

        new FetchDataClass().execute(inputElectric, inputGas, inputBath);
    }

    private class FetchDataClass extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            //retriving the carbon weight
            String result;

            try {
                URL url = new URL("http://impact.brighterplanet.com/residences.json?" +
                        "key=5a927d96eca397b6659a3c361ce32254&monthly_electricity_cost="
                        + strings[0] + "&monthly_natural_gas_cost=" + strings[1]
                        +"&bathrooms=" + strings[2]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(in));
                // call getBufferString to get the string from the buffer.

                String quoteJsonString = getBufferStringFromBuffer(reader).toString();

                //retrieve the string data for each piece of information needed

                result = handler.getData(quoteJsonString);


            } catch (Exception e) {
                Log.e(LOG_TAG, "Error" + e.getMessage());
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Error" + e.getMessage());
                        return null;
                    }
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d(LOG_TAG, result);
                View fragmentContainer = findViewById(R.id.fragment_container);
                if (fragmentContainer != null) {
                    details = new DetailFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();

                    details.setInfoBundle(result);

                    ft.replace(R.id.fragment_container, details);
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            }
        }

        private StringBuffer getBufferStringFromBuffer(BufferedReader br) throws Exception {
            StringBuffer buffer = new StringBuffer();

            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line + '\n');
            }

            if (buffer.length() == 0)
                return null;

            return buffer;
        }
    }
}
