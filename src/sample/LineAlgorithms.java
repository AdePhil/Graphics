package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Created by Philip on 1/17/2018.
 */
public class LineAlgorithms {

    private static int  graphPointRadius = 5;
    private static Color graphPointColor = Color.BLUE;

    public LineAlgorithms() {
    }

    public static GraphLayout drawHorizontalLine(double x1, double x2, double y) {
        GraphLayout graphLayout = new GraphLayout(500,500, 50);
        int graphUnit = graphLayout.getGraphUnit();
        graphLayout.setTitle("An horizontal line between points"+"( "+x1+", "+y+" )"+" ( "+x2+", "+y+" )");

        if (x2 < x1) return null;


        while (x1 <= x2) {
            setPixel(graphLayout,x1 * graphUnit,
                    graphLayout.getgraphHeight() - y * graphUnit, graphPointRadius);
            x1 ++;
        }
        return graphLayout;
    }

    public static GraphLayout drawVerticalLine(double x, double y1, double y2) {
        GraphLayout graphLayout = new GraphLayout(500,500, 50);
        int graphUnit = graphLayout.getGraphUnit();
        graphLayout.setTitle("An vertical line between points"+"( "+x+", "+y1+" )"+" ( "+x+", "+y2+" )");

        if (y2 < y1) return null;

        while (y1 <= y2) {

            setPixel(graphLayout,x * graphUnit,
                    graphLayout.getgraphHeight() - y1 * graphUnit, graphPointRadius);
            y1++;
        }

        return graphLayout;

    }

    public static GraphLayout drawDiagonalLineWithUnitPositiveTangent
            (double x1, double y1, double x2, double y2) {

        GraphLayout graphLayout = new GraphLayout(500,500, 50);
        int graphUnit = graphLayout.getGraphUnit();
        graphLayout.setTitle("A daigonal line where m = 1 between points"+
                "( "+x1+", "+y1+" )"+" ( "+x2+", "+y2+" )");

        if ((y2 < y1) && (x2 < x1)) return null;

        while (x1 <= x2) {

            setPixel(graphLayout,x1 * graphUnit,
                    graphLayout.getgraphHeight() - y1 * graphUnit, graphPointRadius);
            x1++;
            y1++;
        }


        return graphLayout;
    }

    public static GraphLayout drawDiagonalLineWithNagativeUnitTangent(double x1, double y1, double x2, double y2) {

        GraphLayout graphLayout = new GraphLayout(500,500, 50);
        int graphUnit = graphLayout.getGraphUnit();
        graphLayout.setTitle("A daigonal line where m = -1 between points"+
                "( "+x1+", "+y1+" )"+" ( "+x2+", "+y2+" )");

        if ((y2 < y1) && (x2 < x1)) return null;

        while (x1 <= x2) {


            setPixel(graphLayout,x1 * graphUnit,
                    graphLayout.getgraphHeight() - y1 * graphUnit, graphPointRadius);

            x1++;
            y1--;
        }


        return graphLayout;
    }



    public static GraphLayout directLine(double x1, double y1, double x2, double y2){

        GraphLayout graphLayout = new GraphLayout(500,500, 50);
        int graphUnit = graphLayout.getGraphUnit();
        graphLayout.setTitle("Using the direct line algorithm with points"+
                "( "+x1+", "+y1+" )"+" ( "+x2+", "+y2+" )");
        if(x2 < x1) return null;

        double m = slope(x1,y1,x2,y2);
        double b = interceptOnYAxis(x1,y1,m);
        double x = x1;
        double y = y1;

        while(x <= x2){

            setPixel(graphLayout,x * graphUnit,
                    graphLayout.getgraphHeight() - y * graphUnit, graphPointRadius);


            x++;
            y = Math.round(m * x + b);
        }



        return graphLayout;
    }

    public static GraphLayout simpleDDA(double x1, double y1, double x2, double y2){

        GraphLayout graphLayout = new GraphLayout(500,500, 50);
        int graphUnit = graphLayout.getGraphUnit();
        graphLayout.setTitle("Using simple differential analyzer with points"+
                "( "+x1+", "+y1+" )"+" ( "+x2+", "+y2+" )");

        if (x1 == x2) return LineAlgorithms.drawVerticalLine(x1,y1,y2);
        if(y1 == y2) return LineAlgorithms.drawHorizontalLine(x1,x2,y1);

        double m = slope(x1,y1,x2,y2);
        if((Math.abs(m) < 1 && x1 > x2) || Math.abs(m) > 1 && y1 > y2){

            double[] xValues = swap(x1,x2);
            double [] yValues = swap(y1,y2);

            x1 = xValues[0]; x2 = xValues[1]; y1 = yValues[0]; y2 = yValues[1];
        }


        setPixel(graphLayout,x1 * graphUnit,
                graphLayout.getgraphHeight() - y1 * graphUnit, graphPointRadius);

        if(Math.abs(m) < 1) {
            double x = x1++;
            double y = y1;


            while (x <= x2 - 1) {

                setPixel(graphLayout,x * graphUnit,
                        graphLayout.getgraphHeight() - Math.round(y) * graphUnit, graphPointRadius);
                y = y + m;
                x++;

            }
        }

        if(Math.abs(m) > 1) {
            m = 1/m;
            double x = x1;
            double y = y1++;


            while (y <= y2 - 1) {

                setPixel(graphLayout,Math.round(x) * graphUnit,
                        graphLayout.getgraphHeight() - y * graphUnit, graphPointRadius);
                x = x + m;
                y++;

            }
        }



        setPixel(graphLayout,x2 * graphUnit,
                graphLayout.getgraphHeight() - y2 * graphUnit, graphPointRadius);



        return graphLayout;
    }



    private static double slope(double x1, double y1, double x2, double y2) {
        return (y2 - y1) / (x2 - x1);
    }

    private static double interceptOnYAxis(double x, double y, double m) {
        return y - m * x;
    }

    private static void  setPixel(GraphLayout graphLayout,double xCenter,double yCenter, int radius){
        Circle circle = new Circle(xCenter,
                yCenter, graphPointRadius);
        circle.setFill(graphPointColor);
        graphLayout.getChildren().add(circle);
    }

    private static double [] swap(double a, double b){
        double temp = a;
         a = b;
         b = temp;
         return new double[]{a,b};
    }

}
