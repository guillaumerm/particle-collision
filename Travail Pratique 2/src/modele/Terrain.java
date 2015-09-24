/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author 1141678
 */
public class Terrain 
{
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public Terrain(double minX, double maxX, double minY, double maxY) throws TerrainException
    {
        if (validePos(minX) && validePos(minY))
        {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
        }
        else 
        {
            throw new TerrainException();
        }
    }
    
    private boolean validePos(double pos)
    {
        return(pos > -1);
    }  
    
    public void setMinX(double minX) {
        if(validePos(minX)){
            this.minX = minX;
        }
        
    }

    public void setMaxX(double maxX) {
        if(validePos(maxX)){
            this.maxX = maxX;
        }
    }

    public void setMinY(double minY) {
        if(validePos(minY)){
            this.minY = minY; 
        }
       
    }

    public void setMaxY(double maxY) {
        if(validePos(maxY)){
            this.maxY = maxY;
        }
        
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }
    
    
    
    
}
