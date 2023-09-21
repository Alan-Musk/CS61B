public class NBody {
    public static double readRadius(String s)
    {
        In in=new In(s);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String s)
    {
        In in=new In(s);
        int nums=in.readInt();
        Planet[] planets=new Planet[nums];
        // clear something don't need
        in.readDouble();
        for(int i=0;i<planets.length;i++)
        {
            double xxPos=in.readDouble();
            double yyPos=in.readDouble();
            double xxVel=in.readDouble();
            double yyVel=in.readDouble();
            double mass=in.readDouble();
            String imgFileName=in.readString();
            planets[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T=Double.valueOf(args[0]);
        double dt=Double.valueOf(args[1]);
        String filename=args[2];
        // get the universe radius
        double radius=readRadius(filename);
        // get the planets
        Planet[] planets=readPlanets(filename);

        for(int time=0;time<=T;time+=dt)
        {
            StdDraw.enableDoubleBuffering();
            // Create an xForces array and yForces array
            double[] xForces=new double[5];
            double[] yForces=new double[5];
            // Calculate the net x and y forces for each planet
            for(int i=0;i<planets.length;i++)
            {
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            // Call update on each of the planets
            for(int i=0;i<planets.length;i++)
            {
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            // Draw the background again
            StdDraw.setScale(-radius,radius);
            StdDraw.picture(0,0,"images/starfield.jpg");

            //Draw all of the planets
            for(Planet p:planets)
            {
                p.draw();
            }
            // show the offscreen buffer
            StdDraw.show();

            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
