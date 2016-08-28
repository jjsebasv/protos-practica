import java.io.IOException;
import java.util.Date;

/**
 * Registra mensajes
 */

public interface Log {

	/** registra un mensaje con el nivel warning */
	void warning(String msg);

	/** registra un mensaje con el nivel info */
	void info(String msg);

	/** dado un input stream consume uno a uno los mensajes */
	void read(java.io.InputStream input, final java.util.function.Consumer<Message> consumer);
	
	enum Level {
		warning,
		info,
	}

	interface Message {
		java.util.Date when();
		String getMessage();
		Level getLevel();
	}
	
	class mFile implements Message {

		private String message;
		private Level level;
		private Date when;
		
		mFile(String message, Level level, Date when) {
			this.message = message;
			this.level = level;
			this.when = when;
		}
		
		mFile(byte[] b){
			StreamReader sr = new StreamReader();
			try {
				String s = new String(b);
				String[] parts = s.split(",");
				this.message = parts[0].split("=")[1];
				this.level = Level.valueOf(parts[1].split("=")[1]);
				this.when = new Date();
				System.out.println(sr.releaser(b));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public Date when() {
			// TODO Auto-generated method stub
			return when;
		}

		@Override
		public String getMessage() {
			// TODO Auto-generated method stub
			return message;
		}

		@Override
		public Level getLevel() {
			// TODO Auto-generated method stub
			return level;
		}
		
		public String toString() {
			return "Message[message=" + message + ", level=" + level + ", when=" + when + "]";
		}
		
	}
}