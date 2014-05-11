package me.buroa.vb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import me.buroa.model.Authentication;
import me.buroa.utils.JsoupUtil;
import me.buroa.utils.MD5Util;

/**
 * The vbulletin site.
 * @version 4.2.0
 * @author Buroa
 */
public abstract class VBulletin {

	/**
	 * The cookies this vbulletin forum contains.
	 */
	private final Map<String, String> cookies = new HashMap<String, String>();

	/**
	 * The domain that contains a vbulletin forum.
	 */
	private final String domain;

	/**
	 * The security token used for postion, shouting, etc.
	 */
	private String securityToken = "";

	/**
	 * If true, we are currently a session.
	 */
	private boolean session = false;

	private String userId;

	/**
	 * The authentication for this forum.
	 */
	private Authentication authentication;

	/**
	 * Initializes a new vbulletin class.
	 * @param domain The domain that contains a vbulletin forum.
	 */
	public VBulletin(String domain) {
		this.domain = domain;
		checkAccess();
	}

	/**
	 * Checks the access to the specified forum.
	 */
	private void checkAccess() {
		if (!domain.endsWith("forum.php"))
			throw new IllegalArgumentException("You must have forum.php as a ending url.");
	}

	/**
	 * Gets a url document using cookies.
	 * @param url The url we are get/posting.
	 * @param post True if we are posting.
	 * @return The url document.
	 * @throws IOException if an exception occurs.
	 */
	public Document document(String url) throws IOException {
		return document(url, false);
	}

	/**
	 * Gets a url document using cookies.
	 * @param url The url we are get/posting.
	 * @param post True if we are posting.
	 * @return The url document.
	 * @throws IOException if an exception occurs.
	 */
	public Document document(String url, boolean post) throws IOException {
		return document(url, false, null);
	}

	/**
	 * Gets a url document using cookies.
	 * @param url The url we are get/posting.
	 * @param post True if we are posting.
	 * @return The url document.
	 * @throws IOException if an exception occurs.
	 */
	public Document document(String url, boolean post, Map<String, String> postmap) throws IOException {
		final Connection con = JsoupUtil.getConnection(url);
		con.cookies(cookies);
		if (postmap == null)
			postmap = new HashMap<String, String>();
		con.data(postmap);
		final Document document = post ? con.post() : con.get();
		cookies.putAll(con.response().cookies());
		initKeys(document);
		return document;
	}

	/**
	 * Gets the authentication of this forum.
	 * @return The authentication of this forum.
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Gets the domain.
	 * @return The domain that contains a vbulletin forum.
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Retrieves the security token.
	 * @return The security token used for posting, shouting, etc.
	 */
	public String getSecurityToken() {
		return securityToken;
	}

	/**
	 * Initializes the keys needed to post, shout, etc.
	 */
	private void initKeys(Document document) {
		if (session) {
			try {
				if (document == null)
					document = document(domain);
				final Elements elements = document.select("input[name=securitytoken]");
				if (elements.size() >= 1)
					securityToken = elements.first().attr("value");
				userId = cookies.get("bb_userid");
			} catch (IOException e) {
				// should not happen!
			}
		}
	}

	/**
	 * Checks if this forum is currently a session.
	 * @return {@code true} if we are authenticated as a session, {@code false} if otherwise.
	 */
	public boolean isSession() {
		return session;
	}

	/**
	 * Logs the username and password into the forum.
	 * @param authentication The authentication credentials supplied.
	 * @return {@code true} if the authentication credentials were accepted, {@code false} if otherwise.
	 */
	public boolean login(Authentication authentication) {
		if (session)
			return true;
		this.authentication = authentication;
		final String loginurl = domain.replace("forum.php", "login.php");

		// setup the post data.
		final Map<String, String> data = new HashMap<String, String>();
		data.put("vb_login_username", authentication.getUsername());
		data.put("vb_login_password", "");
		data.put("vb_login_password_hint", "Password");
		data.put("cookieuser", "1");
		data.put("s", "");
		data.put("securirytoken", ""); // we don't have one yet
		data.put("do", "login");

		// hash the password.
		final String md5password = MD5Util.hash(authentication.getPassword());
		data.put("vb_login_md5password", md5password);
		data.put("vb_login_md5password_utf", md5password);

		// post
		try {
			document(loginurl, true, data);
		} catch (IOException e) {
			return false;
		}

		// fetch cookies
		session = cookies.containsKey("vbseo_loggedin");
		initKeys(null);
		return session;
	}

	/**
	 * Post to a topic.
	 * @param topic The topic to post too.
	 * @param text The text to be seen by other members.
	 */
	public boolean post(int topic, String text) {
		if (!session)
			return false;
		final String posturl = domain.replace("forum.php", "newreply.php");

		// setup the post data.
		final Map<String, String> data = new HashMap<String, String>();
		data.put("title", "");
		data.put("message_backup", text);
		data.put("message", text);
		data.put("wysiwyg", "1");
		data.put("s", "");
		data.put("securitytoken", securityToken);
		data.put("do", "postreply");
		data.put("t", Integer.toString(topic));
		data.put("p", "");
		data.put("specifiedpost", "0");
		data.put("posthash", "0");
		data.put("poststarttime", "0");
		data.put("loggedinuser", userId);
		data.put("multiquoteempty", "only");
		data.put("sbutton", "Submit Reply");
		data.put("signature", "1");
		data.put("parseurl", "1");
		data.put("vbseo_retrtitle", "1");
		data.put("vbseo_is_retrtitle", "1");
		data.put("subscribe", "0");
		data.put("emailupdate", "0");

		// now post
		try {
			document(posturl, true, data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
