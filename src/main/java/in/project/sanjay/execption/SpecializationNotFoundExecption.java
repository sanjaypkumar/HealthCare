package in.project.sanjay.execption;

public class SpecializationNotFoundExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SpecializationNotFoundExecption() {
		super();
	}
	
	public SpecializationNotFoundExecption(String message) {
		super(message);
	}

}
