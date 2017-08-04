import java.util.Arrays;
public class PictureManipulationDemo
{
    public static void main(String[] args)
    {
       final int SPACER = 10;
        Picture pic = new Picture("renoir1.jpg");
        //System.out.println("Picture's height and width before: "+pic.getHeight()+" "+pic.getWidth());
       //PictureUtil.pictureDraw(pic);
       pic.draw();
       //a version of the given Picture in gray scale and flipped left to right
       Picture flipped = PictureUtil.grayAndFlipLeftToRight(pic);
       flipped.translate(pic.getWidth() + SPACER, 0);
       flipped.draw();
        //a version of the given Picture in gray scale and rotated 90 degrees clockwise
        //lays on its right side. The first row will become the last column
        Picture rotated =  PictureUtil.grayAndRotate90(pic);
        rotated.translate(2 * pic.getWidth() + 2 * SPACER, 0);
        rotated.draw();
       
    }


}
