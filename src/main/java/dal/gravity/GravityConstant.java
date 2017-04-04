package dal.gravity;

public class GravityConstant implements GravityModel{
	
	public static final double EARTH_GRAVITY = 9.81;

    private double gravity;

    public GravityConstant(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public double getGravitationalField() {
        return gravity;
    }

}
