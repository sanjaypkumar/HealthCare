package in.project.sanjay.exception;

public class AppointmentNotFoundExecption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AppointmentNotFoundExecption() {
		super();
	}
	
	public AppointmentNotFoundExecption(String message) {
		super(message);
	}

}
