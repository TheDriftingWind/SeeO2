package charleszhu.qunnipiac.edu.seeo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        String url = "http://aqicn.org/map/usa/#@g/28.294/-99.4482/4z";
        WebView viewer = (WebView)findViewById(R.id.webView);
        viewer.getSettings().setJavaScriptEnabled(true);
        viewer.loadUrl(url);

    }
}
