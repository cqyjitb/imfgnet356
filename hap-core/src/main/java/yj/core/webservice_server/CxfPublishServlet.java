package yj.core.webservice_server;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

/**
 * Created by 917110140 on 2018/9/5.
 */
public class CxfPublishServlet extends CXFNonSpringServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void loadBus(ServletConfig sc) {
        super.loadBus(sc);
        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);
        Endpoint.publish("/QueryXhcard",new QueryXhcardImp());
    }
}
