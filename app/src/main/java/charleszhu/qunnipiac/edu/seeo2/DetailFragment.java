package charleszhu.qunnipiac.edu.seeo2;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    String Bundle;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        //set the text upon start
        if (view != null) {
            String checkString = Bundle;
            checkString = checkString.replace("kg", "");
            Double checkInt = Double.parseDouble(checkString);

            if(checkInt >= 3700){
                view.setBackgroundColor(Color.RED);
            } else {
                view.setBackgroundColor(Color.GREEN);
            }

            TextView footprint = (TextView) view.findViewById(R.id.footprint);
            footprint.setText("Your footprint: " + Bundle);

        }
    }

    public void setInfoBundle(String bundle){
        this.Bundle = bundle;
    }

}
