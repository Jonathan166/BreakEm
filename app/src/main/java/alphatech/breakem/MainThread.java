package alphatech.breakem;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Jonathan on 1/2/2016.
 */
public class MainThread extends Thread{
    private int fps = 30;
    private double averageFps;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMS;
        long waitTime = 0;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 500/fps;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e) {
            }
            timeMS = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMS;
            try{
                this.sleep(waitTime);
            }catch(Exception e){

            }
            finally{
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == fps){
                averageFps = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.print(averageFps);
            }
        }
    }

    public void setRunning(boolean b){
        running = b;
    }
}

