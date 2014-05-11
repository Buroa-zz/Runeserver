package me.buroa.utils;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * A utility for utilizing Jsoup.
 * @author Buroa
 */
public final class JsoupUtil {

	/**
	 * The jsoup browser agent.
	 */
	private static final String BROWSER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36";

	/**
	 * Gets a new connection.
	 * @param url The url to connect to.
	 * @return The url connection.
	 * @throws IOException if an exception occurs.
	 */
	public static Connection getConnection(String url) {
		return Jsoup.connect(url).userAgent(BROWSER_AGENT);
	}

	/**
	 * Gets the document on the url.
	 * @param url The url.
	 * @return The document.
	 * @throws IOException Exception within the url.
	 */
	public static Document getDocument(String url) {
		Document document = null;
		try {
			document = Jsoup.connect(url).userAgent(BROWSER_AGENT).get();
		} catch (IOException e) {
			document = new Document("null");
		}
		return document;
	}

	/**
	 * Default constructor to prevent instantation.
	 */
	private JsoupUtil() {

	}

}
