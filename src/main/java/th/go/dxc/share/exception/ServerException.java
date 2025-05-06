package th.go.dxc.share.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends NestedRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1139046373255579803L;

	public ServerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServerException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {		
		return super.getMessage();
	}

}
