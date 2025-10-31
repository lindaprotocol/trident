package org.linda.trident.core.contract;

import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
import org.linda.trident.abi.TypeEncoder;
import org.linda.trident.abi.datatypes.Type;
import org.linda.trident.core.exceptions.ContractCreateException;
import org.linda.trident.proto.Common.SmartContract.ABI.Entry;
import org.linda.trident.proto.Common.SmartContract.ABI.Entry.Param;

public class ContractConstructor {

  private Entry rawConstructor;

  private List<String> paramTypes;

  private boolean payable;

  private ByteString bytecode = null;

  public ContractConstructor(Entry raw) {
    this.rawConstructor = raw;
    this.paramTypes = new ArrayList<String>();

    for (Param p : raw.getInputsList()) {
      paramTypes.add(p.getType());
    }

    this.payable = raw.getPayable();
  }

  public Entry getRawConstructor() {
    return this.rawConstructor;
  }

  public List<String> getParamTypes() {
    return this.paramTypes;
  }

  public boolean getPayable() {
    return this.payable;
  }

  public ByteString getBytecode() {
    return this.bytecode;
  }

  public void encodeParameter(List<Type<?>> params) throws ContractCreateException {
    if (params.size() != paramTypes.size()) {
      throw new ContractCreateException("Parameter amount doesn't match.");
    }
    StringBuilder builder = new StringBuilder();
    for (Type<?> p : params) {
      builder.append(TypeEncoder.encode(p));
    }
    this.bytecode = ByteString.copyFrom(Hex.decode(builder.toString()));
  }
}
