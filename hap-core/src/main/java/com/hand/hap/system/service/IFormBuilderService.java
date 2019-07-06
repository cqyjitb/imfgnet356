package com.hand.hap.system.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.dto.Form;

import java.util.List;

public interface IFormBuilderService extends IBaseService<Form>, ProxySelf<IFormBuilderService>{

    List<Form> batchUpdate(IRequest iRequest, @StdWho List<Form> forms);

    int batchDelete(List<Form> forms);

}