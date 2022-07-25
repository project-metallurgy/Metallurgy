package projectmetallurgy.metallurgy.multiblock;

import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

public class ClientMultiblock {
  private static final Map<MultiblockHandler.IMultiBlock, IMultiblockClientData> cache =
      new IdentityHashMap<>();

  public static IMultiblockClientData getMultiblockClientData(
      MultiblockHandler.IMultiBlock multiblock) {
    return cache.computeIfAbsent(
        multiblock,
        mt -> {
          Mutable<IMultiblockClientData> result = new MutableObject<>();
          mt.initializeClient(result::setValue);
          return Objects.requireNonNull(
              result.getValue(), "Unable to get client properties for " + mt.getUniqueName());
        });
  }
}
