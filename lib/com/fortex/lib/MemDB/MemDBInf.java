package com.fortex.lib.MemDB;

import java.util.Map;

public abstract interface MemDBInf
{
  public abstract boolean removeMap(String paramString);
  
  public abstract <K, V> Map<K, V> getMap(String paramString);
}


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\MemDB\MemDBInf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */