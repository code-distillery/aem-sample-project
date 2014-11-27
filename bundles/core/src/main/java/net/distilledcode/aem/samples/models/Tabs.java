package net.distilledcode.aem.samples.models;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = Resource.class)
public class Tabs {

    private static final Logger LOG = LoggerFactory.getLogger(Tabs.class);

    private final Resource resource;

    public Tabs(final Resource resource) {
        this.resource = resource;
    }

    public Iterable<Tab> getTabs() {
        final Iterable<Resource> children = resource.getChildren();
        return Iterables.transform(children, Tab.FROM_RESOURCE_FUNCTION);
    }

    public static class Tab {

        private static final Function<Resource, Tab> FROM_RESOURCE_FUNCTION = new Function<Resource, Tab>() {
            @Override
            public Tab apply(final Resource resource) {
                return new Tab(resource);
            }
        };

        private final ValueMap properties;

        private Tab(final Resource resource) {
            this.properties = ResourceUtil.getValueMap(resource);
        }

        public String getTitle() {
            return properties.get("jcr:title", "[title missing]");
        }

        public String getText() {
            return properties.get("jcr:description", "[text missing]");
        }
    }
}
