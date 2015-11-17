package ca.airspeed.camel.creditcard;

import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ca.airspeed.camel.creditcard.processor.BmoCsvProcessor;

/**
 * 
 * @author Brian Schalme
 *
 */
@SpringBootApplication
public class Application extends FatJarRouter {

	@Autowired
	private BmoCsvProcessor bmoCsvProcessor;

	public static void main(String... args) {
		FatJarRouter.main(args);
	}

	@Override
	public void configure() throws Exception {
		from("file://src/test/resources?noop=true&fileName=TestBmo.csv")
		    .process(bmoCsvProcessor)
		    .log("Got it!")
		    .split(body())
		    .marshal().json(JsonLibrary.Jackson, true)
			.to("file://target/data/quickbooks/in?fileName=${file:onlyname.noext}-${header.CamelSplitIndex}.${file:ext}");

	}

}
