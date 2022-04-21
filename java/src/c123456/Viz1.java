package c123456;
import ie.tudublin.Visual;

public class Viz1 extends Visual 
{
    
    int x, j;

    BryansVisual viz;
    float[] lerpedBuffer;
    float width, height;

    public Viz1(float width, float height, float lerpedBuffer[], BryansVisual viz)
    {
        this.height = height;
        this.width = width;
        this.viz = viz;
        this.lerpedBuffer = lerpedBuffer;
    }

    public void render()
    {

        float borderx = width * 0.2f;
        float bordery = height * 0.25f;
        float halfH = height/2;
        float halfW = width/2;
        float average = 0;
        float sum = 0;
    

        for(int i = (int)borderx ; i < width-borderx ; i ++)
        {
            sum += abs(viz.ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], viz.ab.get(i), 0.2f);
        }
        average= sum / width-borderx;
        

        for(int i = (int)borderx ; i < width-borderx ; i ++)
        {
            viz.colorMode(HSB);
            //float c = map(ab.get(i), -1, 1, 0, 255);
            float c = map(i, 0, width-borderx, 0, 255);
            viz.stroke(c, 255, 255);
            float f = lerpedBuffer[i] * bordery;
            //line(i, (halfH/2) + f + (bordery), i, (halfH/2) - f + (bordery));
            if ((halfH/2) + f + (bordery*2) < (halfH/2) - f + (bordery*2))
            {
                viz.line(i, (halfH/2) + f + (bordery*2), i, (halfH/2) + (bordery*2));  
                
            }
            if ((halfH/2) + f > (halfH/2) - f)
            {
                viz.line(i, (halfH/2) + f, i, (halfH/2));
                
            }

            
            x = (int)(sin(radians(i))*(sin(i+j)*5));
            float y = sin(i+j)*300;
            if ( i + x > borderx && i+y > bordery + 5 && i+y < height - bordery - 5)
            {
                viz.ellipse(i + x, i + y, f/2, f/2);
                //ellipse(i + x, (halfH/2) + (f*2) + (bordery), f/2, f/2);
            }
            
        }
        
        // iterate through the width of the screen - 20 on both sides so visualizer doesnt merge with side visualizers
        for(int i = (int)borderx + 20 ; i < width-borderx - 20; i ++)
        {

            //float c = map(ab.get(i), -1, 1, 0, 255);
            float c = map(i, 0, width-borderx, 0, 255);
            viz.stroke(c, 255, 255);
            float f = lerpedBuffer[i] * bordery;

            viz.line(i, (halfH/2) + (f*2) + (bordery), width - (i), (halfW/2)-(f*2) + (bordery));
        }
        /*for(int i = (int)bordery + 20 ; i < height-bordery - 20; i ++)
        {
            //float c = map(ab.get(i), -1, 1, 0, 255);
            float c = map(i, 0, width-borderx, 0, 255);
            stroke(c, 255, 255);
            float f = lerpedBuffer[i] * bordery;

            line((halfH/2) + f + (bordery), i, (halfW/2)-f + (bordery), height - (i));
            
        }*/

        // iterate through the length of the screen
        for(int i = (int)bordery; i < height -bordery; i ++)
        {
            //float c = map(ab.get(i), -1, 1, 0, 255);
            float c = map(i, 0, height -bordery, 0, 255);
            viz.stroke(c, 255, 255);
            float f = lerpedBuffer[i] * bordery;
            
            if (f + (borderx) > borderx)
            {
                viz.line(borderx, i, f + (borderx), i);
            }

            if (f + (borderx*4) < borderx*4)
            {
                viz.line(borderx*4, i, f + (borderx*4), i);
            }
            
        }
        j += 0.04;
    }
    
}