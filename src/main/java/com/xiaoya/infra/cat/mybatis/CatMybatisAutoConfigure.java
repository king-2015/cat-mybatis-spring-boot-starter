package com.xiaoya.infra.cat.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Description: 
 * @Author: zhuzhongji
 * @Date: 2019/4/17 13:51
 * @Param: 
 * @return: 
 */
@Configuration
@AutoConfigureAfter({MybatisAutoConfiguration.class})
@ConditionalOnBean({SqlSessionFactory.class})
public class CatMybatisAutoConfigure {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addCatInterceptor(){
        CatMybatisPlugin catMybatisPlugin = new CatMybatisPlugin();

        sqlSessionFactoryList.forEach(sqlSessionFactory ->{
            sqlSessionFactory.getConfiguration().addInterceptor(catMybatisPlugin);
        });
    }
}
