import java.awt.*;

public class Planet {
    static final double G=6.67e-11;
    public double xxPos;   // current x position
    public double yyPos;   //current y position
    public double xxVel;   //current velocity in the x direction
    public double yyVel;   // current velocity in the y direction
    public double mass;    // mass
    public String imgFileName; // names for images

    public Planet(double xP,double yP,double xV,double yV,double m,String img)
    {
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p)
    {
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    // calculate the distance of current planet and chose planet
    // Return R
    private double calcDistance(Planet p)
    {
        return Math.sqrt((Math.pow((this.xxPos-p.xxPos),2)+Math.pow((this.yyPos-p.yyPos),2)));
    }
    // Return F
    private double calcForceExertedBy(Planet p)
    {
        return (G*this.mass*p.mass)/Math.pow(this.calcDistance(p),2);
    }
    // Return Fx
    private double calcForceExertedByX(Planet p)
    {
        return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/(this.calcDistance(p));
    }
    // Return Fy
    private double calcForceExertedByY(Planet p)
    {
        return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/(this.calcDistance(p));
    }
    // Return all forces for x direction
    public double calcNetForceExertedByX(Planet [] planets)
    {
        double sumForcesByX=0;
        for(Planet p:planets)
        {
            if(!this.equals(p))
            {
                sumForcesByX+=this.calcForceExertedByX(p);
            }
        }
        return sumForcesByX;
    }
    // Return all forces for y direction
    public double calcNetForceExertedByY(Planet [] planets)
    {
        double sumForcesByY=0;
        for(Planet p:planets)
        {
            if(!this.equals(p))
            {
                sumForcesByY+=this.calcForceExertedByY(p);
            }
        }
        return sumForcesByY;
    }
    // Calculate the movement of the Planet
    // 1.Calculate the acceleration using the provided x and y forces.
    public void update(double dt,double fX,double fY)
    {
        this.xxVel+=fX/this.mass*dt;
        this.yyVel+=fY/this.mass*dt;
        this.xxPos+=dt*this.xxVel;
        this.yyPos+=dt*this.yyVel;
    }
    // Draw
    public void draw()
    {
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
