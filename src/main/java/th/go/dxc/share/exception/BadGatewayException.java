package th.go.dxc.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends ServerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1965110590124941306L;

	public BadGatewayException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BadGatewayException(String msg) {
		super(msg);
	}

	
}
