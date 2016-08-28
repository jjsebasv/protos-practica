import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.*;

public class StreamReader {

	char[] dst = new char[1024];
	
	public char[] releaser(byte[] stream) throws IOException {
		InputStream is = new ByteArrayInputStream(stream);
		for(int i = 0; i < stream.length && i < dst.length; i++) {
			Character.toChars(is.read(), dst, i);
		}
		
		return dst;
	}
	
}
