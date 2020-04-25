package com.panda.generator;

public class MyJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {

    public MyJavaTypeResolverDefaultImpl(){
        super();
        super.typeMap.put(Types.TINYINT,new org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT",new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
