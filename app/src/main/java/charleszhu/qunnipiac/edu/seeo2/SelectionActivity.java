package charleszhu.qunnipiac.edu.seeo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }

    public void onSelect(View v){
        switch (v.getId()){
            case R.id.drivingButton:
                Intent i = new Intent(this, DrivingActivity.class);
                startActivity(i);
                break;
            case R.id.houseButton:
                Intent j = new Intent(this, HouseholdActivity.class);
                startActivity(j);
                break;
        }
    }
}
