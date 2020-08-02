/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.lucene.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.benchmark.byTask.feeds.DemoHTMLParser;
import org​.apache.lucene.benchmark.byTask.feeds.DocData;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.CMPT456Similarity;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;

public class SimpleMetrics {

    private SimpleMetrics() {}

    public static void main(String[] args) throws Exception {
        System.out.println("-----Enter Simple Metrics-----");

        while(true) {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
            Analyzer analyzer = new StandardAnalyzer();
            QueryParser parser = new QueryParser("contents", analyzer);
            
            System.out.println("please enter a term which you want to the two metrics or press enter to quit: ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String line = in.readLine();

            if (line.length() == 0) 
                break;

            line = line.trim();
            Query query = parser.parse(line);

            Term term = new Term("contents", query.toString("contents"));
            System.out.println("The searching Term is: " + query.toString("contents"));

            System.out.println("The total documents contain the term (Document Frequency): " + reader.docFreq(term));
            System.out.println("The total number of occurrences of the term across all" + 
                    "documents (Term Frequency): " + reader.totalTermFreq(term));
        }
    }

    // private static void TFdoc(IndexReader reader, Term term) throws IOException {
    //     System.out.println("The total documents contain the term (Document Frequency): " + reader.docFreq(term));
    //     System.out.println("The total number of occurrences of the term across all" + 
    //             "documents (Term Frequency): " + reader.totalTermFreq(term));
    // }
}