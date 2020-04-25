package com.panda.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

public class MyJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {

    public MyJavaTypeResolverDefaultImpl(){
        super();
        super.typeMap.put(Types.TINYINT,new org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT",new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
