package com.liucongblog.gui;

import com.liucongblog.PlSqlParser;
import com.liucongblog.TestUtils;
import org.antlr.v4.gui.TreeViewer;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class AstGuiTest {
    @Test
    public void test_examples_aggregate01() throws IOException {
        PlSqlParser plSqlParser = TestUtils.getParserFromFilePath("./examples/aggregate01.sql");
        PlSqlParser.Sql_scriptContext sql_scriptContext=plSqlParser.sql_script();
        TreeViewer viewer = new TreeViewer(Arrays.asList(plSqlParser.getRuleNames()),sql_scriptContext);
        viewer.open();
        System.out.println(sql_scriptContext.toStringTree(plSqlParser));
    }



    @Test
    public void test_alter_database() throws IOException {
        PlSqlParser plSqlParser = TestUtils.getParserFromFilePath("./examples/alter_database.sql");
        PlSqlParser.Sql_scriptContext sql_scriptContext=plSqlParser.sql_script();
        TreeViewer viewer = new TreeViewer(Arrays.asList(plSqlParser.getRuleNames()),sql_scriptContext);
        viewer.open();
        System.out.println(sql_scriptContext.toStringTree(plSqlParser));
    }
}
