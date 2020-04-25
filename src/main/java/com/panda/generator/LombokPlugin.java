package com.panda.generator;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String author = properties.getProperty("author");
        String remarks = introspectedTable.getRemarks();

        //添加domain的import
        topLevelClass.addImportedType("lombok.Data");
//        topLevelClass.addImportedType("lombok.EqualsAndHashCode");
        topLevelClass.addImportedType("lombok.experimental.Accessors");

        //添加domain的注解
        topLevelClass.addAnnotation("@Data");
//        topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = true)");
        topLevelClass.addAnnotation("@Accessors(chain = true)");

        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        if (StringUtils.isNoneEmpty(remarks)){
            topLevelClass.addJavaDocLine(" * " + remarks);
            topLevelClass.addJavaDocLine(" *");
        }
        topLevelClass.addJavaDocLine(" * @author " + author);
        topLevelClass.addJavaDocLine(" * @date   " + date2Str(new Date()));
        topLevelClass.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //Mapper文件的注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* @date " + date2Str(new Date()));
        interfaze.addJavaDocLine("*/");
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 获取列注释
        fieldAnnotation(field, introspectedColumn.getRemarks());

        if(StringUtils.equals("Byte", field.getType().getShortName())){
            field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
        }
        return true;
    }

    public static void fieldAnnotation(Field field, String explain){
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + explain);
        field.addJavaDocLine(" */");
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
