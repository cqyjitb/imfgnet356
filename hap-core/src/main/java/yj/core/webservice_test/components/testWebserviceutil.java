package yj.core.webservice_test.components;


import yj.core.webservice_test.dto.WReturnData;
import yj.core.webservice_test.sender.YJMethodImpl;
import yj.core.webservice_test.sender.YJMethodImplService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class testWebserviceutil {
    private static final QName SERVICE_NAME = new QName("http://Impl.service.YJ.com/", "YJMethodImplService");
    public  testWebserviceutil(){

    }

    public String Confirmation(){
        URL wsdlURL = YJMethodImplService.WSDL_LOCATION;
        YJMethodImplService ss = new YJMethodImplService(wsdlURL,SERVICE_NAME);
        YJMethodImpl port = ss.getYJMethodImplPort();


        List<WReturnData> list = new ArrayList<>();
        list = port.queryOtherWeight("");
        if (list.size() > 0){
            return "S";
        }else{
            return "E";
        }

    }
}
