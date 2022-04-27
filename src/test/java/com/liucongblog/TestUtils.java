package com.liucongblog;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    public static PlSqlParser getParserFromFilePath(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        CharStream charStream = CharStreams.fromStream(inputStream);
        // upper tokens
        CaseChangingCharStream upper = new CaseChangingCharStream(charStream, true);
        //parse to lexer
        PlSqlLexer plSqlLexer = new PlSqlLexer(upper);
        CommonTokenStream commonTokenStream = new CommonTokenStream(plSqlLexer);
        // use token to create parser
        return new PlSqlParser(commonTokenStream);
    }
}
