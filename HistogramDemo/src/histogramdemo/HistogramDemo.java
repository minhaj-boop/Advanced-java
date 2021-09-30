/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package histogramdemo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author HP
 */
public class HistogramDemo {

    private int[] splitRGB(int rgb) {
        int colors[] = new int[3];
        for (int i = 0; i < 3; i++) {
            colors[i] = (rgb >> (8 * i)) & 0xFF;
        }
        return colors;
    }

    private int rgbToGrayscale(int colors[]) {
        return (int) (colors[2] * 0.2126 + colors[1] * 0.7152 + colors[0] * 0.0722);
    }

    public int[] calculateHistogram(String fileName) {
        int histogram[] = new int[256];

        try {
            File file = new File(fileName);
            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();
            for (int c = 0; c < width; c++) {
                for (int r = 0; r < height; r++) {
                    int grayscale = rgbToGrayscale(splitRGB(image.getRGB(c, r)));
                    histogram[grayscale]++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HistogramDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return histogram;
    }

    class ParallelHistogram implements Callable<int[]> {

        private BufferedImage image;
        private int widthStart;
        private int widthStop;

        public ParallelHistogram(BufferedImage image, int widthStart, int widthStop) {
            this.image = image;
            this.widthStart = widthStart;
            this.widthStop = widthStop;
        }

        @Override
        public int[] call() throws Exception {
            int histogram[] = new int[256];

            int height = image.getHeight();
            for (int c = widthStart; c < widthStop; c++) {
                for (int r = 0; r < height; r++) {
                    int grayscale = rgbToGrayscale(splitRGB(image.getRGB(c, r)));
                    histogram[grayscale]++;
                }
            }
            return histogram;
        }

    }

    public int[] parallelCalculateHistogram(String fileName) {
        int histogram[] = new int[256];

        try {
            File file = new File(fileName);
            BufferedImage image = ImageIO.read(file);

            int availableProcessors = Runtime.getRuntime().availableProcessors();

            ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);

            int width = image.getWidth();
            int dw = width / availableProcessors;

            List<Future<int[]>> futureResultList = new ArrayList();

            for (int i = 0; i < availableProcessors; i++) {
                ParallelHistogram parallelHistogram = null;
                if (i != availableProcessors - 1) {
                    parallelHistogram = new ParallelHistogram(image, i * dw, (i + 1) * dw);
                } else {
                    parallelHistogram = new ParallelHistogram(image, i * dw, width);
                }

                Future<int[]> result = executorService.submit(parallelHistogram);
                futureResultList.add(result);
            }

            executorService.shutdown();

            for (int i = 0; i < futureResultList.size(); i++) {
                try {
                    int partialHistogram[] = futureResultList.get(i).get(5, TimeUnit.SECONDS);

                    printHistogram(partialHistogram, "Par" + i, 0, 20);

                    for (int h = 0; h < partialHistogram.length; h++) {
                        histogram[h] += partialHistogram[h];
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(HistogramDemo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(HistogramDemo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TimeoutException ex) {
                    Logger.getLogger(HistogramDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HistogramDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return histogram;
    }

    public void printHistogram(int histogram[], String label, int start, int end) {
        System.out.print(label + ":");
        for (int i = start; i < end; i++) {
            System.out.print(" (" + i + ":" + histogram[i] + ")");
        }
        System.out.println();
    }

    public HistogramDemo() {
        System.out.println("Started Calculating the histogram...");
        long startTime = System.currentTimeMillis();
        int histogram[] = calculateHistogram("bike.jpg");
        long stopTime = System.currentTimeMillis();
        System.out.printf("Time taken: %.3f seconds\n", (stopTime - startTime) / 1000.0);

        printHistogram(histogram, "Seq ", 0, 20);

        startTime = System.currentTimeMillis();
        histogram = parallelCalculateHistogram("bike.jpg");
        stopTime = System.currentTimeMillis();
        System.out.printf("Time taken: %.3f seconds\n", (stopTime - startTime) / 1000.0);

        printHistogram(histogram, "Par ", 0, 20);

    }

    public static void main(String[] args) {
        new HistogramDemo();
    }

}
