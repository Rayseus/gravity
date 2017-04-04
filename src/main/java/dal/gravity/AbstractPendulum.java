package dal.gravity;

/**
 * Represents a pendulum
 */
public abstract class AbstractPendulum {


    /* instance variables - string length, point mass, angular displacement
     * at t=0, constant for local gravitational field in m/s^2 (e.g., 9.81 on Earth)
     */
    private double stringLength, pointMass;
    protected double theta0;
    protected GravityModel gravityModel;

    /**
     * Creates a new Pendulum instance using
     * inLength: the string length (>0)
     * inMass: the point mass (>0)
     * inTheta0: angular displacement at t=0 (0<=theta0)
     * inG: gravitational field value to use
     */
    public AbstractPendulum (double inLength, double inMass, double inTheta0, GravityModel gravityModel) {
        if (validStringLength (inLength)) stringLength = inLength;
        else throw new IllegalArgumentException ("invalid string length: " + inLength);
        if (validPointMass(inMass)) pointMass = inMass;
        else throw new IllegalArgumentException ("invalid point mass: " + inMass);
        if (validDisplacement (inTheta0)) theta0 = inTheta0;
        else throw new IllegalArgumentException 
        ("invalid angular displacement: " + inTheta0);
        if (validGC (gravityModel.getGravitationalField())) this.gravityModel = gravityModel;
        else throw new IllegalArgumentException ("invalid local gravitational field: " + gravityModel.getGravitationalField());
    }

    private boolean validDisplacement (double val) { return (val >= 0); }
    private boolean validPointMass (double val) { return (val > 0); }
    private boolean validStringLength (double val) { return (val > 0); }
    private boolean validGC (double val) { return (val >= 0); }

    public double getMaxAngularDisplacement () { return theta0; }

    public double getPointMass () { return pointMass; }

    public double getStringLength () { return stringLength; }

    public double getGravitationalField () { return gravityModel.getGravitationalField(); }

    public void setGravity(GravityModel gravityModel){
    	this.gravityModel = gravityModel;
    }
}

