package cl.goviedo.usermanagment.controllersadvice;

import java.time.LocalDateTime;

public class Violation {
    private final String mensaje;
    private final LocalDateTime timestamp;

    public Violation(String mensaje) {
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    public String getmensaje() {
        return mensaje;
    }

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
    
    
}
