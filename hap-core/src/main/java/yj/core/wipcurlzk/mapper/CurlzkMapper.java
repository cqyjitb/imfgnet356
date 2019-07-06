package yj.core.wipcurlzk.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipcurlzk.dto.Curlzk;

import java.util.List;

public interface CurlzkMapper extends Mapper<Curlzk> {
    Curlzk selectById(@Param("line_id") Long line_id, @Param("classgrp") String classgrp);
    int updateZxhbar(Curlzk dto);
    Curlzk selectById2(Long line_id);
    List<Curlzk> selectAllLinesforZxhbar(Long line_id);
    List<Curlzk> selectByZpgdbar(@Param("zpgdbar") String zpgdbar);
    List<Curlzk> selectAllLinesForKanbByErdat(@Param("erdat") String erdat);
}