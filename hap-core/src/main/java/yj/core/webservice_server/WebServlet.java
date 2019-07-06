package yj.core.webservice_server;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import javax.servlet.ServletConfig;

/**
 * Created by 917110140 on 2018/9/6.
 */
public class WebServlet extends CXFNonSpringServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void loadBus(ServletConfig sc) {
        super.loadBus(sc);
        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);

        HelloWorldServerImp helloWorld = new HelloWorldServerImp();//实现类

       ServerFactoryBean serverFactoryBean2 = new ServerFactoryBean(); //server工厂
        serverFactoryBean2.setServiceClass(IHelloWorldServer.class);// 接口类
        serverFactoryBean2.setAddress("/helloWorld"); //服务请求路径
        serverFactoryBean2.setServiceBean(helloWorld);
        serverFactoryBean2.create();

        //发布的箱号查询及同步接口
        ServerFactoryBean serverFactoryBean = new ServerFactoryBean(); //server工厂
        QueryXhcardImp queryXhcardImp = new QueryXhcardImp();
        serverFactoryBean.setServiceClass(IQueryXhcard.class);// 接口类
        serverFactoryBean.setAddress("/QueryXhcard"); //服务请求路径
        serverFactoryBean.setServiceBean(queryXhcardImp);
        serverFactoryBean.create();

        //发布的migo接口
        ServerFactoryBean serverFactoryBean1 = new ServerFactoryBean(); //server工厂
        CallMigoImpl callMigoImp = new CallMigoImpl();
        serverFactoryBean1.setServiceClass(ICallMigo.class);
        serverFactoryBean1.setAddress("/CallMigo"); //服务请求路径
        serverFactoryBean1.setServiceBean(callMigoImp);
        serverFactoryBean1.create();

        //发布的migo接口
        ServerFactoryBean serverFactoryBean3 = new ServerFactoryBean(); //server工厂
        JjbgImpl jjbgimp = new JjbgImpl();
        serverFactoryBean3.setServiceClass(IJjbg.class);
        serverFactoryBean3.setAddress("/Jjbg"); //服务请求路径
        serverFactoryBean3.setServiceBean(jjbgimp);
        serverFactoryBean3.create();

        //发布的创建托盘码接口
        ServerFactoryBean serverFactoryBean4 = new ServerFactoryBean(); //server工厂
        CreateTpImpl createTp = new CreateTpImpl();
        serverFactoryBean4.setServiceClass(ICreateTp.class);
        serverFactoryBean4.setAddress("/CreateTp"); //服务请求路径
        serverFactoryBean4.setServiceBean(createTp);
        serverFactoryBean4.create();

        //发布的机加上线围堵接口
        ServerFactoryBean serverFactoryBean5 = new ServerFactoryBean(); //server工厂
        WeiduImpl weidu = new WeiduImpl();
        serverFactoryBean5.setServiceClass(IWeidu.class);
        serverFactoryBean5.setAddress("/Weidu"); //服务请求路径
        serverFactoryBean5.setServiceBean(weidu);
        serverFactoryBean5.create();

        //发布的查询托盘状态接口
        ServerFactoryBean serverFactoryBean6 = new ServerFactoryBean(); //server工厂
        ReadTpImpl readTp = new ReadTpImpl();
        serverFactoryBean6.setServiceClass(IReadTp.class);
        serverFactoryBean6.setAddress("/ReadTp"); //服务请求路径
        serverFactoryBean6.setServiceBean(readTp);
        serverFactoryBean6.create();

        //发布的查询托盘状态接口
        ServerFactoryBean serverFactoryBean7 = new ServerFactoryBean(); //server工厂
        SyncXhcardImpl syncXhcard = new SyncXhcardImpl();
        serverFactoryBean7.setServiceClass(IsyncXhcard.class);
        serverFactoryBean7.setAddress("/SyncXhcard"); //服务请求路径
        serverFactoryBean7.setServiceBean(syncXhcard);
        serverFactoryBean7.create();

        //发布外协采购订单同步接口
        ServerFactoryBean serverFactoryBean8 = new ServerFactoryBean(); //server工厂
        SyncOutsrgrfeImpl SyncOutsrgrfe = new SyncOutsrgrfeImpl();
        serverFactoryBean8.setServiceClass(IsyncOutsrgrfe.class);
        serverFactoryBean8.setAddress("/SyncOutsrgrfe"); //服务请求路径
        serverFactoryBean8.setServiceBean(SyncOutsrgrfe);
        serverFactoryBean8.create();

        //发布外协发料单打印同步接口
        ServerFactoryBean serverFactoryBean9 = new ServerFactoryBean(); //server工厂
        SyncOutsrgissueHeadImpl syncOutsrgissueHead = new SyncOutsrgissueHeadImpl();
        serverFactoryBean9.setServiceClass(IsyncOutsrgissueHead.class);
        serverFactoryBean9.setAddress("/SyncOutsrgissueHead"); //服务请求路径
        serverFactoryBean9.setServiceBean(syncOutsrgissueHead);
        serverFactoryBean9.create();

        //发布外协收货单打印同步接口
        ServerFactoryBean serverFactoryBean10 = new ServerFactoryBean(); //server工厂
        SyncOutsrgreceiptHeadImpl syncOutsrgreceiptHead = new SyncOutsrgreceiptHeadImpl();
        serverFactoryBean10.setServiceClass(IsyncOutsrgreceiptHead.class);
        serverFactoryBean10.setAddress("/SyncoutsrgreceiptHead"); //服务请求路径
        serverFactoryBean10.setServiceBean(syncOutsrgreceiptHead);
        serverFactoryBean10.create();

        //发布sap查询outsrgrfe 数据接口
        ServerFactoryBean serverFactoryBean11 = new ServerFactoryBean(); //server工厂
        QueryOutsrgrfeImpl queryOutsrgrfe = new QueryOutsrgrfeImpl();
        serverFactoryBean11.setServiceClass(IqueryOutsrgrfe.class);
        serverFactoryBean11.setAddress("/QueryOutsrgrfe"); //服务请求路径
        serverFactoryBean11.setServiceBean(queryOutsrgrfe);
        serverFactoryBean11.create();

        //通过报工uuid查询机加报工 数据接口
        ServerFactoryBean serverFactoryBean12 = new ServerFactoryBean(); //server工厂
        QueryJJbglogimpl queryJJbglogimpl = new QueryJJbglogimpl();
        serverFactoryBean12.setServiceClass(IQueryJJbglog.class);
        serverFactoryBean12.setAddress("/QueryJJbg"); //服务请求路径
        serverFactoryBean12.setServiceBean(queryJJbglogimpl);
        serverFactoryBean12.create();

    }
}
