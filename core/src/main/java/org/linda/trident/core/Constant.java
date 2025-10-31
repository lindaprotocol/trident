package org.linda.trident.core;

public final class Constant {

  //LindaGrid gRPC services, maintained by official team
  public static final String LINDAGRID_MAIN_NET = "grpc.lindagrid.io:50051";
  public static final String LINDAGRID_MAIN_NET_SOLIDITY = "grpc.lindagrid.io:50052";

  public static final String LINDAGRID_SHASTA = "grpc.shasta.lindagrid.io:50051";
  public static final String LINDAGRID_SHASTA_SOLIDITY = "grpc.shasta.lindagrid.io:50052";

  //Public Fullnode, maintained by official team
  public static final String FULLNODE_NILE = "grpc.nile.lindagrid.io:50051";
  public static final String FULLNODE_NILE_SOLIDITY = "grpc.nile.lindagrid.io:50061";

  public static final long TRANSACTION_DEFAULT_EXPIRATION_TIME = 60 * 1_000L; //60 seconds

  public static final String LIND_SYMBOL = "_";

}