/*
 * #{copyright}#
 */

package com.hand.hap.system.mapper;

import com.hand.hap.system.dto.DocSequence;

/**
 * @author wuyichu
 */
public interface DocSequenceMapper {

    DocSequence lockDocSequence(DocSequence docSequence);

    int insert(DocSequence docSequence);

    int update(DocSequence docSequence);
}