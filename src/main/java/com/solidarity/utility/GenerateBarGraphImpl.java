package com.solidarity.utility;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Component;

import com.solidarity.model.Data;

@Component
public class GenerateBarGraphImpl implements GenerateBarGraph {

	@Override
	public void drawBarGraph(String path, List<Data> data) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < data.size(); i++) {
			Data oneData = data.get(i);
			dataset.addValue(oneData.getScore(), "Performance", oneData.getTerm());
		}
		
		
		 JFreeChart chart=ChartFactory.createBarChart(  
			        "Performance Graph", //Chart Title  
			        "Term", // Category axis  
			        "Score", // Value axis  
			        dataset,  
			        PlotOrientation.VERTICAL,  
			        true,true,false  
			       );  
		 
		 String fontName = "Lucida Sans";
		 StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
		 theme.setTitlePaint( Color.decode( "#4572a7" ) );
		    theme.setExtraLargeFont( new Font(fontName,Font.PLAIN, 16) ); //title
		    theme.setLargeFont( new Font(fontName,Font.BOLD, 15)); //axis-title
		    theme.setRegularFont( new Font(fontName,Font.PLAIN, 11));
		    theme.setRangeGridlinePaint( Color.decode("#C0C0C0"));
		    theme.setPlotBackgroundPaint( Color.white );
		    theme.setChartBackgroundPaint( Color.white );
		    theme.setGridBandPaint( Color.red );
		    theme.setAxisOffset( new RectangleInsets(0,0,0,0) );
		    theme.setBarPainter(new StandardBarPainter());
		    theme.setAxisLabelPaint( Color.decode("#666666")  );
		    theme.apply( chart );
		    chart.getCategoryPlot().setOutlineVisible( false );
		    chart.getCategoryPlot().getRangeAxis().setAxisLineVisible( false );
		    chart.getCategoryPlot().getRangeAxis().setTickMarksVisible( false );
		    chart.getCategoryPlot().setRangeGridlineStroke( new BasicStroke() );
		    chart.getCategoryPlot().getRangeAxis().setTickLabelPaint( Color.decode("#666666") );
		    chart.getCategoryPlot().getDomainAxis().setTickLabelPaint( Color.decode("#666666") );
		    chart.setTextAntiAlias( true );
		    chart.setAntiAlias( true );
		    chart.getCategoryPlot().getRenderer().setSeriesPaint( 0, Color.decode( "#4572a7" ));
		    BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
		    rend.setShadowVisible( true );
		    rend.setShadowXOffset( 2 );
		    rend.setShadowYOffset( 0 );
		    rend.setShadowPaint( Color.decode( "#C0C0C0"));
		    rend.setMaximumBarWidth( 0.1);

		 
		
		 try {
				ChartUtils.saveChartAsJPEG(new File(path+"/barGraph.jpeg"), chart, 400, 300);
			} catch (IOException e) {

				e.printStackTrace();
			} 
	}

}
