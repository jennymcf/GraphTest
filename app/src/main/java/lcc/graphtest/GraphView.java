package lcc.graphtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;


/**
 * Draws an XY scatter plot on a SurfaceView.
 * <p>Company: written for Leeward Community College</p>
 * @author Jennifer R. McFatridge
 * @version 1.0
 */
public class GraphView extends SurfaceView {
    private Graph graph;


    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.DKGRAY);
        gridPaint.setTextSize(30);
        if(graph == null){
            init();
        }
        Line[] verticalLines = graph.getVerticalGridlines();
        String[] verticalLabels = graph.getVerticalLabels();
        for(int i = 0; i< verticalLines.length; i++){
            canvas.drawLine(verticalLines[i].startX,verticalLines[i].startY,verticalLines[i].endX,verticalLines[i].endY, gridPaint );
            canvas.drawText(verticalLabels[i], verticalLines[i].startX, graph.getOrigin().y + 30, gridPaint);
        }


        Line[] horizontalLines = graph.getHorizontalGridlines();
        String[] horizontalLabels = graph.getHorizontalLabels();
        for(int i = 0; i< horizontalLines.length; i++){
            canvas.drawLine(horizontalLines[i].startX,horizontalLines[i].startY,horizontalLines[i].endX,horizontalLines[i].endY, gridPaint );
            canvas.drawText(horizontalLabels[i], graph.getOrigin().x - 30, horizontalLines[i].startY, gridPaint);
        }

        Paint plotPaint = new Paint();
        plotPaint.setColor(Color.BLUE);
        Point[] dataPoints = graph.getPoints();
        for(int i = 0; i< dataPoints.length; i++){
            canvas.drawCircle(dataPoints[i].x  , dataPoints[i].y , 10, plotPaint);
        }
        Paint fitPaint = new Paint();
        fitPaint.setColor(Color.GREEN);
        Point[] fitPoints = graph.getFitPoints();
        for(int i = 1; i< fitPoints.length; i++){
            canvas.drawLine(fitPoints[i-1].x, fitPoints[i-1].y, fitPoints[i].x, fitPoints[i].y, fitPaint);
        }
    }

    private void init(){
        Rect drawingArea = new Rect(getLeft()+getPaddingLeft(), getTop()+getPaddingTop(), getRight()-getPaddingRight(), getBottom()-getPaddingBottom());
        double [] xPoints = {0, 1, 2, 3, 4, -2};
        double [] yPoints = {11, 14, 21, 26, 29, 0};
        DataSeries data = new DataSeries(xPoints, yPoints, 6);
        graph = new Graph(data, drawingArea);
        graph.setFitType(CurveFit.LINEAR);
    }
}
