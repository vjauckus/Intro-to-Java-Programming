public class Blocks
{
    public static void main(String[] args)
    {
        Picture pic = new Picture("eiffel-tower.jpg");
        int[][] pixels = pic.getGrayLevels();
        
        for (int i = 0; i < pixels.length; i = i + 2)
        {
            for (int j = 0; j < pixels[0].length; j = j + 2)
            {
                int avg = (pixels[i][j] + pixels[i][j + 1]
                           + pixels[i + 1][j] + pixels[i + 1][j + 1]) / 4;
                pixels[i][j] = avg;
                pixels[i + 1][j] = avg;
                pixels[i][j + 1] = avg;
                pixels[i + 1][j + 1] = avg;
            }
        }
        
        //print Picture in color
        pic.draw();
        //all 4 Neighbour pixel have got the same average value from Array int[][]pixels
        Picture pic2 = new Picture(pixels);
        pic2.translate(pic.getWidth() + 10, 0);
        
        //print Picture in Gray/Black color
        pic2.draw();
    }
}
