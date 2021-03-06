package threads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Threads

public class Foo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r = () -> System.out.println("hola mundo");
		
		// Le doy un runable y lo ejecuta -- Es una abstraccion y nos ayuda
		Executor e = (command) -> command.run();
		
		/*
		 *  Este va creando nuevos threads (hilos) y los va corriendo
		 *  No tengo que esperar que termine para volver a ejecutar.
		 *  Lo bueno es que tuve que overridear solo una cosa.
		 *  No esta limitado en la cantidad de hilos
		 */
		Executor e2 = (command) -> new Thread(command).start();
		
		/*
		 * Aca lo limito a que no pueda tener vivos mas de un thread
		 */
		Executor e3 = Executors.newFixedThreadPool(2);
		
		e.execute(r);
		e2.execute(r);
		e3.execute(r);
	}

}
