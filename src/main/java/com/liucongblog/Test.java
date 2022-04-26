package com.liucongblog;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.*;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("./examples/aggregate01.sql");
        CharStream charStream = CharStreams.fromStream(inputStream);
        // upper tokens
        CaseChangingCharStream upper = new CaseChangingCharStream(charStream, true);
        //parse to lexer
        PlSqlLexer plSqlLexer = new PlSqlLexer(upper);
        CommonTokenStream commonTokenStream = new CommonTokenStream(plSqlLexer);
        // use token to create parser
        PlSqlParser plSqlParser = new PlSqlParser(commonTokenStream);
        PlSqlParser.Sql_scriptContext sql_scriptContext = plSqlParser.sql_script();
        //show AST in console
        System.out.println(sql_scriptContext.toStringTree(plSqlParser));
        //show AST in GUI
        TreeViewer viewer = new TreeViewer(Arrays.asList(plSqlParser.getRuleNames()), sql_scriptContext);
        viewer.open();
    }
}
