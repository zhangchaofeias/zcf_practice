package g7.zcf.mpchart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import g7.zcf.R;

public class MPChartActivity extends Activity {
    private LineChart lineChart;
    private List<String> xLineData = new ArrayList<String>();
    private List<ILineDataSet> yLineData = new ArrayList<ILineDataSet>();
    private List<Entry> yValueData = new ArrayList<Entry>();
    private List<Entry> yValueData2 = new ArrayList<Entry>();

    private LineData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpchart);

        lineChart = (LineChart) findViewById(R.id.lineChart);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        lineChart.setDescription("测试 linechart");
        for (int i = 1; i < 8; i++) {
            xLineData.add(i + "");
        }

        for (int i=1; i<8; i++){
            yValueData.add(new Entry(i,i));
        }

        yValueData2.add(new Entry(19,0));
        yValueData2.add(new Entry(10,1));
        yValueData2.add(new Entry(1,2));
        yValueData2.add(new Entry(4,3));
        yValueData2.add(new Entry(2,4));
        yValueData2.add(new Entry(20,5));
        yValueData2.add(new Entry(50,6));

        LineDataSet  dataset1 = new LineDataSet(yValueData, "y轴数据1");
        LineDataSet  dataSet2 = new LineDataSet(yValueData2, "y轴数据2");
        dataset1.setColor(Color.BLUE);
        dataSet2.setColor(Color.GREEN);

        yLineData.add(dataset1);
        yLineData.add(dataSet2);

        data = new LineData(xLineData, dataset1);

        lineChart.setData(data);
    }
}
