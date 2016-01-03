package alphatech.breakem;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Jonathan on 1/2/2016.
 */
public class Background {
    private Bitmap image;
    private int x;
    private int y;
    private int dx;

    public Background(Bitmap pic){
        image = pic;
        dx = GamePanel.MOVESPEED;
    }

    public void update(){
        x += dx;
        if(x < -GamePanel.WIDTH){
            x = 0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
        if(x < 0){
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }
}
