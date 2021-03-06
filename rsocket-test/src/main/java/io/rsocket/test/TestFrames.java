/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rsocket.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.rsocket.Payload;
import io.rsocket.frame.*;
import io.rsocket.util.DefaultPayload;
import io.rsocket.util.EmptyPayload;

/** Test instances of all frame types. */
public final class TestFrames {
  private static final ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;
  private static final Payload emptyPayload = DefaultPayload.create(Unpooled.EMPTY_BUFFER);

  private TestFrames() {}

  /** @return {@link ByteBuf} representing test instance of Cancel frame */
  public static ByteBuf createTestCancelFrame() {
    return CancelFrameCodec.encode(allocator, 1);
  }

  /** @return {@link ByteBuf} representing test instance of Error frame */
  public static ByteBuf createTestErrorFrame() {
    return ErrorFrameCodec.encode(allocator, 1, new RuntimeException());
  }

  /** @return {@link ByteBuf} representing test instance of Extension frame */
  public static ByteBuf createTestExtensionFrame() {
    return ExtensionFrameCodec.encode(
        allocator, 1, 1, Unpooled.EMPTY_BUFFER, Unpooled.EMPTY_BUFFER);
  }

  /** @return {@link ByteBuf} representing test instance of Keep-Alive frame */
  public static ByteBuf createTestKeepaliveFrame() {
    return KeepAliveFrameCodec.encode(allocator, false, 1, Unpooled.EMPTY_BUFFER);
  }

  /** @return {@link ByteBuf} representing test instance of Lease frame */
  public static ByteBuf createTestLeaseFrame() {
    return LeaseFrameCodec.encode(allocator, 1, 1, null);
  }

  /** @return {@link ByteBuf} representing test instance of Metadata-Push frame */
  public static ByteBuf createTestMetadataPushFrame() {
    return MetadataPushFrameCodec.encode(allocator, Unpooled.EMPTY_BUFFER);
  }

  /** @return {@link ByteBuf} representing test instance of Payload frame */
  public static ByteBuf createTestPayloadFrame() {
    return PayloadFrameCodec.encode(allocator, 1, false, true, false, null, Unpooled.EMPTY_BUFFER);
  }

  /** @return {@link ByteBuf} representing test instance of Request-Channel frame */
  public static ByteBuf createTestRequestChannelFrame() {
    return RequestChannelFrameCodec.encode(
        allocator, 1, false, false, 1, null, Unpooled.EMPTY_BUFFER);
  }

  /** @return {@link ByteBuf} representing test instance of Fire-and-Forget frame */
  public static ByteBuf createTestRequestFireAndForgetFrame() {
    return RequestFireAndForgetFrameCodec.encode(allocator, 1, false, null, Unpooled.EMPTY_BUFFER);
  }

  /** @return {@link ByteBuf} representing test instance of Request-N frame */
  public static ByteBuf createTestRequestNFrame() {
    return RequestNFrameCodec.encode(allocator, 1, 1);
  }

  /** @return {@link ByteBuf} representing test instance of Request-Response frame */
  public static ByteBuf createTestRequestResponseFrame() {
    return RequestResponseFrameCodec.encodeReleasingPayload(allocator, 1, emptyPayload);
  }

  /** @return {@link ByteBuf} representing test instance of Request-Stream frame */
  public static ByteBuf createTestRequestStreamFrame() {
    return RequestStreamFrameCodec.encodeReleasingPayload(allocator, 1, 1L, emptyPayload);
  }

  /** @return {@link ByteBuf} representing test instance of Setup frame */
  public static ByteBuf createTestSetupFrame() {
    return SetupFrameCodec.encode(
        allocator,
        false,
        1,
        1,
        Unpooled.EMPTY_BUFFER,
        "metadataType",
        "dataType",
        EmptyPayload.INSTANCE);
  }
}
