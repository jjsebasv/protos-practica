import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.function.Consumer;

public class Logger implements Log {
	
	private OutputStream os;
	InputStream is;
	
	Logger (String fileName) throws IOException {
		File logger = new File(fileName);
		logger.createNewFile();
		os = new FileOutputStream(logger, true);
		is = new FileInputStream(logger);
	}
	
	@Override
	public void warning(String msg) {
		try {
			createMessage(msg, Level.warning);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void info(String msg) {
		try {
			createMessage(msg, Level.info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createMessage (String msg, Level lvl) throws IOException {
		Message m = new mFile(msg, lvl, new Date());
		os.write(m.toString().getBytes(Charset.forName("UTF-8")));
		os.write('\n');
		os.close();
	}

	@Override
	public void read(InputStream input, Consumer<Message> consumer) {
		byte[] b = new byte[1024];
		try {
			int i = input.read();
			for(int j = 0 ; j < b.length && i != -1; i = input.read(), j++){
				b[j] = (byte) i;
				if(i == ']') {
					consumer.accept(new mFile(b));
					b = new byte[1024];
					j = 0;
				}
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
