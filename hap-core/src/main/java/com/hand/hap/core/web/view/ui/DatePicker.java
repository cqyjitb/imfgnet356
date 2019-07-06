package com.hand.hap.core.web.view.ui;

import com.hand.hap.core.web.view.UITag;
import com.hand.hap.core.web.view.XMap;

/**
 * 
 * 日期选择器.
 * 
 * @author hailin.xu@hand-china.com
 * @author njq.niu@hand-china.com
 * 
 */
@UITag
public class DatePicker extends DateField {

    public static DatePicker createInstance() {
        XMap view = new XMap(DEFAULT_TAG_PREFIX, DEFAULT_NAME_SPACE, "datePicker");
        DatePicker datePicker = new DatePicker();
        datePicker.initPrototype(view);
        return datePicker;
    }
}
