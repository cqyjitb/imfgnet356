package com.hand.hap.attachment.service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.hand.hap.attachment.dto.SysFile;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**附件上传
 * @author jinqin.ma@hand-china.com
 *
 */

public interface IAttachmentProvider {
	String getAttachListHtml(String sourceType, String sourceKey, Locale locale, String contextPath) throws Exception;

	String getAttachHtml(String sourceType, String sourceKey, Locale locale, String contextPath, boolean enableRemove,
                         boolean enableUpload) throws IOException, TemplateException;

	void setConfiguration(Configuration configuration);
}
