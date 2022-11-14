package platform.education.commonResource.web.common.shiro.code;

import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.util.MultiDomainResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public class CrRedirectView {

    //TODO - complete JavaDoc

    /**
     * The default encoding scheme: UTF-8
     */
    public static final String DEFAULT_ENCODING_SCHEME = "UTF-8";

    private String url;

    private boolean contextRelative = false;

    private boolean http10Compatible = true;

    private String encodingScheme = DEFAULT_ENCODING_SCHEME;

    /**
     * Constructor for use as a bean.
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public CrRedirectView() {
    }

    /**
     * Create a new RedirectView with the given URL.
     * <p>The given URL will be considered as relative to the web server,
     * not as relative to the current ServletContext.
     *
     * @param url the URL to redirect to
     * @see #CrRedirectView(String, boolean)
     */
    public CrRedirectView(String url) {
        setUrl(url);
    }

    /**
     * Create a new RedirectView with the given URL.
     *
     * @param url             the URL to redirect to
     * @param contextRelative whether to interpret the given URL as
     *                        relative to the current ServletContext
     */
    public CrRedirectView(String url, boolean contextRelative) {
        this(url);
        this.contextRelative = contextRelative;
    }

    /**
     * Create a new RedirectView with the given URL.
     *
     * @param url              the URL to redirect to
     * @param contextRelative  whether to interpret the given URL as
     *                         relative to the current ServletContext
     * @param http10Compatible whether to stay compatible with HTTP 1.0 clients
     */
    public CrRedirectView(String url, boolean contextRelative, boolean http10Compatible) {
        this(url);
        this.contextRelative = contextRelative;
        this.http10Compatible = http10Compatible;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Set whether to interpret a given URL that starts with a slash ("/")
     * as relative to the current ServletContext, i.e. as relative to the
     * web application root.
     * <p/>
     * Default is "false": A URL that starts with a slash will be interpreted
     * as absolute, i.e. taken as-is. If true, the context path will be
     * prepended to the URL in such a case.
     *
     * @param contextRelative whether to interpret a given URL that starts with a slash ("/")
     *                        as relative to the current ServletContext, i.e. as relative to the
     *                        web application root.
     * @see HttpServletRequest#getContextPath
     */
    public void setContextRelative(boolean contextRelative) {
        this.contextRelative = contextRelative;
    }

    /**
     * Set whether to stay compatible with HTTP 1.0 clients.
     * <p>In the default implementation, this will enforce HTTP status code 302
     * in any case, i.e. delegate to <code>HttpServletResponse.sendRedirect</code>.
     * Turning this off will send HTTP status code 303, which is the correct
     * code for HTTP 1.1 clients, but not understood by HTTP 1.0 clients.
     * <p>Many HTTP 1.1 clients treat 302 just like 303, not making any
     * difference. However, some clients depend on 303 when redirecting
     * after a POST request; turn this flag off in such a scenario.
     *
     * @param http10Compatible whether to stay compatible with HTTP 1.0 clients.
     * @see HttpServletResponse#sendRedirect
     */
    public void setHttp10Compatible(boolean http10Compatible) {
        this.http10Compatible = http10Compatible;
    }

    /**
     * Set the encoding scheme for this view. Default is UTF-8.
     *
     * @param encodingScheme the encoding scheme for this view. Default is UTF-8.
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public void setEncodingScheme(String encodingScheme) {
        this.encodingScheme = encodingScheme;
    }


    /**
     * Convert model to request parameters and redirect to the given URL.
     *
     * @param model    the model to convert
     * @param request  the incoming HttpServletRequest
     * @param response the outgoing HttpServletResponse
     * @throws IOException if there is a problem issuing the redirect
     * @see #appendQueryProperties
     * @see #sendRedirect
     */
    public final void renderMergedOutputModel(
            Map model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Prepare name URL.
        StringBuilder targetUrl = new StringBuilder();
        if (this.contextRelative && getUrl().startsWith("/")) {
            // Do not apply context path to relative URLs.
            targetUrl.append(request.getContextPath());
        }
        targetUrl.append(getUrl());
        //change the following method to accept a StringBuilder instead of a StringBuilder for Shiro 2.x:
        appendQueryProperties(targetUrl, model, this.encodingScheme);

        sendRedirect(request, response, targetUrl.toString(), this.http10Compatible);
    }

    /**
     * Append query properties to the redirect URL.
     * Stringifies, URL-encodes and formats model attributes as query properties.
     *
     * @param targetUrl      the StringBuffer to append the properties to
     * @param model          Map that contains model attributes
     * @param encodingScheme the encoding scheme to use
     * @throws UnsupportedEncodingException if string encoding failed
     * @see #urlEncode
     * @see #queryProperties
     * @see #urlEncode(String, String)
     */
    protected void appendQueryProperties(StringBuilder targetUrl, Map model, String encodingScheme)
            throws UnsupportedEncodingException {

        // Extract anchor fragment, if any.
        // The following code does not use JDK 1.4's StringBuffer.indexOf(String)
        // method to retain JDK 1.3 compatibility.
        String fragment = null;
        int anchorIndex = targetUrl.toString().indexOf('#');
        if (anchorIndex > -1) {
            fragment = targetUrl.substring(anchorIndex);
            targetUrl.delete(anchorIndex, targetUrl.length());
        }

        // If there aren't already some parameters, we need a "?".
        boolean first = (getUrl().indexOf('?') < 0);
        Map queryProps = queryProperties(model);

        if (queryProps != null) {
            for (Object o : queryProps.entrySet()) {
                if (first) {
                    targetUrl.append('?');
                    first = false;
                } else {
                    targetUrl.append('&');
                }
                Map.Entry entry = (Map.Entry) o;
                String encodedKey = urlEncode(entry.getKey().toString(), encodingScheme);
                String encodedValue =
                        (entry.getValue() != null ? urlEncode(entry.getValue().toString(), encodingScheme) : "");
                targetUrl.append(encodedKey).append('=').append(encodedValue);
            }
        }

        // Append anchor fragment, if any, to end of URL.
        if (fragment != null) {
            targetUrl.append(fragment);
        }
    }

    /**
     * URL-encode the given input String with the given encoding scheme, using
     * {@link URLEncoder#encode(String, String) URLEncoder.encode(input, enc)}.
     *
     * @param input          the unencoded input String
     * @param encodingScheme the encoding scheme
     * @return the encoded output String
     * @throws UnsupportedEncodingException if thrown by the JDK URLEncoder
     * @see URLEncoder#encode(String, String)
     * @see URLEncoder#encode(String)
     */
    protected String urlEncode(String input, String encodingScheme) throws UnsupportedEncodingException {
        return URLEncoder.encode(input, encodingScheme);
    }

    /**
     * Determine name-value pairs for query strings, which will be stringified,
     * URL-encoded and formatted by appendQueryProperties.
     * <p/>
     * This implementation returns all model elements as-is.
     *
     * @param model the model elements for which to determine name-value pairs.
     * @return the name-value pairs for query strings.
     * @see #appendQueryProperties
     */
    protected Map queryProperties(Map model) {
        return model;
    }

    /**
     * Send a redirect back to the HTTP client
     *
     * @param request          current HTTP request (allows for reacting to request method)
     * @param response         current HTTP response (for sending response headers)
     * @param targetUrl        the name URL to redirect to
     * @param http10Compatible whether to stay compatible with HTTP 1.0 clients
     * @throws IOException if thrown by response methods
     */
    @SuppressWarnings({"UnusedDeclaration"})
    protected void sendRedirect(HttpServletRequest request, HttpServletResponse response,
                                String targetUrl, boolean http10Compatible) throws IOException {
        String basePath = MultiDomainResolver.resolver(request.getServerName(), SysContants.SZXY_BASE_PATH);

        if (http10Compatible) {
            // Always send status code 302.
            response.sendRedirect(response.encodeRedirectURL((basePath != null? basePath : "") + targetUrl));
        } else {
            // Correct HTTP status code is 303, in particular for POST requests.
            response.setStatus(303);
            response.setHeader("Location", response.encodeRedirectURL((basePath != null? basePath : "") + targetUrl));
        }
    }

}