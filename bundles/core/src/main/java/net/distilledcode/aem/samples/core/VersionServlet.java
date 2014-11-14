package net.distilledcode.aem.samples.core;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet to render the version information using the VersionLookupService.
 * The servlet can be called at /apps/aem-sample-project/version.txt.
 */
@SlingServlet(
        resourceTypes = "aem-sample-project/version",
        methods = "GET",
        extensions = "txt"
)
public class VersionServlet extends SlingSafeMethodsServlet {

    @Reference
    @SuppressWarnings("unused")
    private VersionLookupService versionLookup;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        final PrintWriter writer = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        writer.print("version: ");
        writer.println(versionLookup.getBuildVersion());
        writer.print("timestamp: ");
        writer.println(versionLookup.getBuildTimestamp());
    }
}
