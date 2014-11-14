package net.distilledcode.aem.samples.core;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component(immediate = true)
@Service(VersionLookupService.class)
@SuppressWarnings("unused")
public class VersionLookupService {

    private static final Logger LOG = LoggerFactory.getLogger(VersionLookupService.class);

    private static final String VERSION_FILE = "/apps/aem-sample-project/version.properties";

    private static final String PN_VERSION = "version";

    private static final String PN_TIMESTAMP = "timestamp";

    @Reference
    @SuppressWarnings("unused")
    private ResourceResolverFactory resolverFactory;

    private String version;

    private String timestamp;

    public String getBuildVersion() {
        return version;
    }

    public String getBuildTimestamp() {
        return timestamp;
    }

    @Activate @SuppressWarnings("unused")
    private void activate() throws LoginException, IOException {
        ResourceResolver resolver = null;
        try {
            resolver = resolverFactory.getAdministrativeResourceResolver(null);
            final Resource resource = resolver.getResource(VERSION_FILE);
            if (resource != null) {
                final Properties props = new Properties();
                props.load(resource.adaptTo(InputStream.class));
                version = props.getProperty(PN_VERSION);
                timestamp = props.getProperty(PN_TIMESTAMP);
            }
        } catch (LoginException e) {
            LOG.error("Could not activate VersionLookupService: ", e);
            throw e;
        } catch (IOException e) {
            LOG.error("Could not activate VersionLookupService: ", e);
            throw e;
        }finally {
            if (resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
    }

    @Deactivate @SuppressWarnings("unused")
    private void deactivate() {
        version = null;
        timestamp = null;
    }
}
