package lcc.graphtest;

import android.app.Activity;
import android.os.Bundle;




public class GraphingActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graphing);
        GraphView view = (GraphView)findViewById(R.id.graph_view);
        double [] xPoints = {0,0.267,0.50,0.701,0.901,1.101,1.301,1.502,1.702};
        double [] yPoints = {0,0.10,0.2,0.3,0.4,0.5,0.6,0.7,0.8};
        DataSeries data = new DataSeries(xPoints, yPoints, 9);
        view.setData(data);
        view.invalidate();





    }


}
