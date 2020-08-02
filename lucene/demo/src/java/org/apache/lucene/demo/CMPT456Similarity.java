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

// ref: https://jar-download.com/artifacts/org.apache.lucene/lucene-core/5.5.1/source-code/org/apache/lucene/search/similarities/ClassicSimilarity.java

package org.apache.lucene.search.similarities;


import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.SmallFloat;
import org.apache.lucene.search.similarities.ClassicSimilarity;


public class CMPT456Similarity extends ClassicSimilarity {

    public CMPT456Similarity() {}

    @Override
    public float tf(float freq) {
        return (float) (Math.sqrt(1 + freq));
    }

    @Override
    public float idf(long docFreq, long docCount) {
        return (float) ( 1.0 + Math.log( (double)(docCount + 2) / (double)(docFreq + 2) ) );
    }
}