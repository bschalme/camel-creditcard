package ca.airspeed.camel.creditcard.processor;

import static org.apache.commons.collections4.MapUtils.getString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.joda.time.LocalDateTime;
import org.junit.Test;

public class BmoCsvProcessorUnitTest extends CamelTestSupport {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;

	@Test
	public void testBmoCsvFile() throws Exception {
		File testBmoCsvFile = new File("src/test/resources/TestBmo.csv");

		template.sendBody(testBmoCsvFile);

		List<Exchange> exchanges = resultEndpoint.getExchanges();
		assertThat("Number of exchanges;", exchanges, hasSize(equalTo(1)));
		Exchange exchange = exchanges.get(0);
		Message message = exchange.getIn();
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> items = message.getBody(List.class);
		assertThat("Number of credit card items;", items, hasSize(equalTo(2)));
		Map<String, ?> item = items.get(0);
		assertThat("TransactionDate;", (Date) item.get("txnDate"), equalTo(new LocalDateTime(2015, 8, 5, 0, 0).toDate()));
		assertThat("Transaction Amount;", (BigDecimal) item.get("txnAmount"), equalTo(new BigDecimal("6.78")));
		assertThat("Description;", getString(item,"description"), equalTo("SUPERLAB 905-5879135 ON"));
	}

	@Override
	protected RouteBuilder createRouteBuilder() {
		final BmoCsvProcessor bmoCsvProcessor = new BmoCsvProcessor();
		return new RouteBuilder() {
			public void configure() {
				from("direct:start").process(bmoCsvProcessor).to("mock:result");
			}
		};
	}
}
