<%@include file="/apps/aem-sample-project/global.jspf"%><%
%><sling:adaptTo var="tabs" adaptable="${resource}" adaptTo="net.distilledcode.aem.samples.models.Tabs"/><%--
    Note: this example still lacks proper html encoding of the output
--%><ul>
    <c:forEach var="tab" items="${tabs.tabs}">
        <li>
            <h2>${tab.title}</h2>
            <div>${tab.text}</div>
        </li>
    </c:forEach>
</ul>