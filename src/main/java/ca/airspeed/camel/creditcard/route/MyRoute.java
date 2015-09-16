package ca.airspeed.camel.creditcard.route;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * @author Brian Schalme
 *
 */
@SpringBootApplication
public class MyRoute extends FatJarRouter {
	
	public static void main(String... args) {
		FatJarRouter.main(args);
	}
	
	@Override
	public void configure() throws Exception {
		from("file:/Users/bschalme/data/creditcard/in")
		.log("Got it!")
		.to("file:/Users/bschalme/data/quickbooks/in");

	}

}
