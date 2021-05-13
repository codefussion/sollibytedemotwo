package com.solidarity.utility;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Component;

import com.solidarity.model.Data;

@Component
public class GenerateLineChartImpl implements GenerateLineChart {

	@Override
	public void drawLinecChart(String path, List<Data> data) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < data.size(); i++) {
			Data oneData = data.get(i);
			dataset.addValue(oneData.getScore(), "Performance", oneData.getTerm());
		}

		String chartTitle = "Performance Graph";
		String categoryAxisLabel = "Term";
		String valueAxisLabel = "Scores";

		JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);
		CategoryPlot plot = chart.getCategoryPlot();
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		plot.setBackgroundPaint(new Color(70, 165, 251));
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);


		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/lineChart.jpeg"), chart, 400, 300);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
