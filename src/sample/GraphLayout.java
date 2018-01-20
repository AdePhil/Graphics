package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * Created by Philip on 1/17/2018.
 */
public class GraphLayout extends Pane {
    private int graphUnit = 50;
    private double graphWidth;
    private double graphHeight;
    private   int textOffsetX = 5;
    private   int textOffsetY = 15;
    private   int strokeWidth = 1;



    private String title;

    public GraphLayout(double graphWidth, double graphHeight, int graphUnit) {
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
        this.graphUnit = graphUnit;

        drawGraph();
    }


    private void drawGraph() {

        double noOfLinesOnTheVertical = Math.floor(graphWidth / graphUnit);
        double noOfLinesOnTheHorizontal = Math.floor(graphHeight / graphUnit);


        for (int i = 0; i < noOfLinesOnTheHorizontal; i += 1) {
            Line line = new Line(i * graphUnit, graphHeight, i * graphUnit, 0);
            getChildren().add(line);
            line.setStrokeWidth(strokeWidth);
            line.setStroke(Color.GREEN);

            //label the graph on y-axis
            Text coordinateNumber = new Text(-textOffsetY, graphHeight - i * graphUnit + textOffsetX,
                    String.valueOf(i));
            this.getChildren().add(coordinateNumber);


        }
        for (int i = 0; i < noOfLinesOnTheVertical; i += 1) {
            Line line = new Line(0, graphHeight - i * graphUnit,
                    graphWidth, graphHeight - i * graphUnit);
            line.setStrokeWidth(strokeWidth);
            line.setStroke(Color.GREEN);
            getChildren().add(line);

            //label the graph on x-axis
            if (i != 0) {
                Text coordinateNumber = new Text(i * graphUnit - textOffsetX, graphHeight + textOffsetY,
                        String.valueOf(i));
                this.getChildren().add(coordinateNumber);
            }


        }

    }
    public int getGraphUnit(){
        return graphUnit;
    }

    public double getgraphHeight(){
        return  graphHeight;
    }

    public double getGraphWidth(){
        return  graphWidth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
