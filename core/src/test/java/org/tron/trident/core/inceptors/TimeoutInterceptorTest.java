package org.linda.trident.core.inceptors;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import io.grpc.ClientInterceptor;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.linda.trident.core.ApiWrapper;
import org.linda.trident.core.Constant;
import org.linda.trident.core.interceptor.TimeoutInterceptor;
import org.linda.trident.core.key.KeyPair;
import org.linda.trident.proto.Response.BlockExtention;

public class TimeoutInterceptorTest {

  @Test
  void testTimeoutInterceptor() {

    List<ClientInterceptor> clientInterceptorList = new ArrayList<>();

    clientInterceptorList.add(new TimeoutInterceptor(1));  // 1ms timeout


    ApiWrapper client = new ApiWrapper(
        Constant.FULLNODE_NILE,
        Constant.FULLNODE_NILE_SOLIDITY,
        KeyPair.generate().toPrivateKey(),
        clientInterceptorList
    );

    try {
      client.getBlock(false);
      fail("Except DEADLINE_EXCEEDED Exception");

    } catch (Exception e) {
      assertTrue(e.getMessage().contains("DEADLINE_EXCEEDED"));
      assertTrue(e instanceof io.grpc.StatusRuntimeException);
    } finally {
      client.close();
    }
  }

  @Test
  void testClientInterceptorWithShortTimeOut() {

    List<ClientInterceptor> clientInterceptorList = new ArrayList<>();


    ApiWrapper client = new ApiWrapper(
        Constant.FULLNODE_NILE,
        Constant.FULLNODE_NILE_SOLIDITY,
        KeyPair.generate().toPrivateKey(),
        clientInterceptorList,
        1
    );

    try {
      BlockExtention blockExtention = client.getBlock(false);
      fail("Except DEADLINE_EXCEEDED Exception");

    } catch (Exception e) {
      assertTrue(e.getMessage().contains("DEADLINE_EXCEEDED"));
      assertTrue(e instanceof io.grpc.StatusRuntimeException);
    } finally {
      client.close();
    }
  }

  @Test
  void testClientInterceptorWithLongTimeOut() {

    ApiWrapper clientDefault = new ApiWrapper(
        Constant.FULLNODE_NILE,
        Constant.FULLNODE_NILE_SOLIDITY,
        KeyPair.generate().toPrivateKey(),
        10_000 //10s
    );

    for (int i = 0; i < 2; i++) {
      assertDoesNotThrow(() -> {
        clientDefault.getBlock(false);
        sleep(10_000L);
      });
    }
  }
}
