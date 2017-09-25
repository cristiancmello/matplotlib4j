package com.github.sh0nk.matplotlib4j;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class MainTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCommand() throws IOException, PythonExecutionException {
        Command command = new Command();
        command.execute("print('test')");
    }

    @Test
    public void testPlot() throws IOException, PythonExecutionException {
        Plot plt = new PlotImpl(true);
        plt.plot()
            .add(Arrays.asList(1.3, 2))
            .label("label")
            .linestyle("--");
        plt.title("Title!");
        plt.legend();
        plt.show();
    }


    @Test
    public void testPlotSin() throws IOException, PythonExecutionException {
        List<Double> x = IntStream.range(0, 100).boxed()
                .map(Integer::doubleValue)
                .map(d -> d / 15).collect(Collectors.toList());
        List<Double> y = x.stream().map(Math::sin).collect(Collectors.toList());

        Plot plt = new PlotImpl(true);
        plt.plot()
                .add(x, y)
                .label("label")
                .linestyle("--");
        plt.title("sin curve");
        plt.legend();
        plt.show();
    }

    @Test
    public void testThirdArgError() throws IOException, PythonExecutionException {
        expectedException.expect(PythonExecutionException.class);

        Plot plt = Plot.create();
        plt.plot().add(Arrays.asList(1.3, 2))
            .add(Arrays.asList(1.3, 2))
            .add(Arrays.asList(1.3, 2));
        plt.show();
    }
}
