package Model;

public interface Process {
	
	public void next();
	public void start ();
	public boolean isFinished ();
	public void compute();

}
