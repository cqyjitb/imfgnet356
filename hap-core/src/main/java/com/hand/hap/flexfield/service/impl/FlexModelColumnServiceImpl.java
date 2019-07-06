package com.hand.hap.flexfield.service.impl;

import com.hand.hap.flexfield.dto.FlexModel;
import com.hand.hap.flexfield.mapper.FlexModelColumnMapper;
import com.hand.hap.flexfield.mapper.FlexModelMapper;
import com.hand.hap.generator.dto.DBColumn;
import com.hand.hap.generator.service.impl.DBUtil;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.hand.hap.flexfield.dto.FlexModelColumn;
import com.hand.hap.flexfield.service.IFlexModelColumnService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlexModelColumnServiceImpl extends BaseServiceImpl<FlexModelColumn> implements IFlexModelColumnService {

    @Autowired
    @Qualifier("sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public List<String> getTableColumn(String tableName) {
        List<String> columnList = new ArrayList<>();
        SqlSession sqlSession = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            connection = DBUtil.getConnectionBySqlSession(sqlSession);
            resultSet = DBUtil.getTableColumnInfo(tableName, connection.getMetaData());
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                if ("OBJECT_VERSION_NUMBER".equalsIgnoreCase(columnName) || "REQUEST_ID".equalsIgnoreCase(columnName)
                        || "PROGRAM_ID".equalsIgnoreCase(columnName) || "CREATED_BY".equalsIgnoreCase(columnName)
                        || "CREATION_DATE".equalsIgnoreCase(columnName) || "LAST_UPDATED_BY".equalsIgnoreCase(columnName)
                        || "LAST_UPDATE_DATE".equalsIgnoreCase(columnName) || "LAST_UPDATE_LOGIN".equalsIgnoreCase(columnName)
                        ) {
                    continue;
                }
                columnList.add(columnName);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                DBUtil.closeResultSet(resultSet);
                DBUtil.closeConnection(connection);
                DBUtil.closeSqlSession(sqlSession);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return columnList;
    }

}