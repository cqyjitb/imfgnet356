package com.hand.hap.core.web.view;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * 
 * @author njq.niu@hand-china.com
 *
 */
public interface ScreenHandler {

    public static final String TAG_SCREEN = "screen";

    public void processDocument(XmlPullParser xpp) throws XmlPullParserException, IOException;

    public XMap getRoot();
}