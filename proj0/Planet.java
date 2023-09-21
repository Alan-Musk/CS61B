public class Planet {
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
    // calculate the distance of chose planet
}
