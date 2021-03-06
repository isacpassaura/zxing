/*
 * Copyright (C) 2014 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link PDF417HighLevelEncoder}.
 */
public final class PDF417EncoderTestCase extends Assert {

  @Test
  public void testEncodeAuto() throws Exception {
    String encoded = PDF417HighLevelEncoder.encodeHighLevel(
        "ABCD", Compaction.AUTO, StandardCharsets.UTF_8, false);
    assertEquals("\u039f\u001A\u0385ABCD", encoded);
  }

  @Test
  public void testEncodeAutoWithSpecialChars() throws Exception {
    // Just check if this does not throw an exception
    PDF417HighLevelEncoder.encodeHighLevel(
        "1%§s ?aG$", Compaction.AUTO, StandardCharsets.UTF_8, false);
  }
  
  @Test
  public void testEncodeIso88591WithSpecialChars() throws Exception {
    // Just check if this does not throw an exception
    PDF417HighLevelEncoder.encodeHighLevel("asdfg§asd", Compaction.AUTO, StandardCharsets.ISO_8859_1, false);
  }

  @Test
  public void testEncodeText() throws Exception {
    String encoded = PDF417HighLevelEncoder.encodeHighLevel(
        "ABCD", Compaction.TEXT, StandardCharsets.UTF_8, false);
    assertEquals("Ο\u001A\u0001?", encoded);
  }

  @Test
  public void testEncodeNumeric() throws Exception {
    String encoded = PDF417HighLevelEncoder.encodeHighLevel(
        "1234", Compaction.NUMERIC, StandardCharsets.UTF_8, false);
    assertEquals("\u039f\u001A\u0386\f\u01b2", encoded);
  }

  @Test
  public void testEncodeByte() throws Exception {
    String encoded = PDF417HighLevelEncoder.encodeHighLevel(
        "abcd", Compaction.BYTE, StandardCharsets.UTF_8, false);
    assertEquals("\u039f\u001A\u0385abcd", encoded);
  }

  @Test(expected = WriterException.class)
  public void testEncodeEmptyString() throws Exception {
    PDF417HighLevelEncoder.encodeHighLevel("", Compaction.AUTO, null, false);
  }
}
