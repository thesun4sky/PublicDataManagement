package com.openGDSMobileApplicationServer.mapper;

import com.openGDSMobileApplicationServer.valueObject.CollectVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by intruder on 16. 8. 2.
 */
public interface CollectedMapper {
    /**
     *
         String name;
         String provider;
         String url;
         String ep;
         int time;
         boolean status;
         String comment;
         String keys;
     *insert into "OPENDATACOLLECT"("NAME", "PROVIDER", "URL", "EP", "TIME", "STATUS", "COMMENT", "KEYS")
     * values('TEST', 'TESTSite', 'TestUrl', 'testep', 1, true, 'comment','eys');
     */
    @Insert("INSERT INTO OPENDATACOLLECT(NAME, PROVIDER, URL, EP, TIME, STATUS, COMMENT, KEYS) " +
            "VALUES(#{name}, #{provider}, #{url}, #{ep}, #{time}, #{status}, #{comment}, #{keys})")
    void insertCollected(CollectVO collect) throws Exception;

    @Select("SELECT * FROM 'OPENDATACOLLECT'")
    List<CollectVO> findAllCollected() throws Exception;
}
