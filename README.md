# Trident - LINDA Java SDK

## Overview

Trident is a lightweight Java SDK for interacting with the LINDA blockchain. It provides a simple and efficient way to integrate LINDA functionality into your Java applications.

## Features

- Complete implementation of LINDA's gRPC interfaces
- Smart contract deployment and interaction
- Wallet key management and address utilities
- Transaction building and signing
- LRC10/LRC20/LRC721 token support

## Adding Trident to your build

Trident-java is compiled with java version 1.8 and gradle 7.6.

Latest version can be found on [Maven Central](https://mvnrepository.com/artifact/io.github.lindaprotocol/trident).

### Gradle

```groovy
implementation("io.github.lindaprotocol:trident:<version>") // Check latest version from Maven Central
```

### Maven

Add repo setting:

```xml
<dependency>
  <groupId>io.github.lindaprotocol</groupId>
  <artifactId>trident</artifactId>
  <version>[version]</version>
</dependency>
```


### Using local build

You can use locally built packages by follow steps(eg. 0.9.2):

1. Copy the compiled jar file to your project's `libs` directory
2. Add the following to your project's `build.gradle`:
```groovy
dependencies {
    implementation files('libs/trident-0.9.2.jar')
    implementation "com.google.guava:guava:33.0.0-jre"
    implementation "io.grpc:grpc-netty-shaded:1.60.0"
    implementation "io.grpc:grpc-netty:1.60.0"
    implementation "io.grpc:grpc-okhttp:1.60.0"
    implementation "io.grpc:grpc-protobuf:1.60.0"
    implementation "io.grpc:grpc-stub:1.60.0"
    implementation "com.google.protobuf:protobuf-java-util:3.25.5"
    implementation "org.bouncycastle:bcprov-jdk18on:1.78.1"
    implementation "io.vertx:vertx-core:4.5.10"
    implementation "io.netty:netty-all:4.1.118.Final"
    implementation "com.alibaba.fastjson2:fastjson2:2.0.55"
}
```

## Quick Start

**Initialize client**
```java
// Initialize with LindaGrid mainnet 
ApiWrapper client = ApiWrapper.ofMainnet("private_key", "api_key"); //api_key from LindaGrid

//Or Shasta test net 
ApiWrapper client = ApiWrapper.ofShasta("private key");

// Or nile testnet
ApiWrapper client = ApiWrapper.ofNile("private_key");

//Initialize with special grpc endpoint
ApiWrapper client = new ApiWrapper("grpc endpoint", "solidity grpc endpoint", "private_key");

// Send LIND
TransactionExtention transactionExtention = client.transfer("fromAddress", "toAddress", 100_000_000L); //100LIND
// Sign
Transaction signedTxn = client.signTransaction(transactionExtention);
// Broadcast
String txId = client.broadcastTransaction(signedTxn);
System.out.println("txId is " + txId);
```

## Documentation

- [Official Documentation](https://Lindaprotocol.github.io/trident/)


## Build instructions
Trident includes integration tests for running on the Nile testnet. If you want to run test cases involving write operations on the blockchain, such as transfer or deploy contract and so on, please follow the steps:

1. Uncomment the Disabled function in the unit test cases.
```
   //@Disabled("add private key to enable this case")
```
2. Set the linda.private-key and linda.tokenId in the test configuration file in the core directory [here](core/src/test/resources/application-test.properties).


``` 
linda.private-key=xxx
linda.tokenId=1000587
```

**Note:** The account should have at least 1000 LIND, 100 USDT, and 1000 LRC10 token on the Nile testnet. you can get testCoin from [nileex.io](https://nileex.io/join/getJoinPage).

## Contribution

We're very glad and appreciate to have contributions from the community.

Refer to our [contributing guide ](CONTRIBUTING.md)for more information.

## Integrity Check

Starting from version 0.9.2, releases are published to Maven repository and signed with the gpg key:

```
pub: 3149 FCA5 6377 2D11 2624 9C36 CC3F 8CEA 7B0C 74D6
uid: buildtrident@linda.network
```