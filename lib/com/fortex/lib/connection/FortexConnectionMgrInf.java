package com.fortex.lib.connection;

public abstract interface FortexConnectionMgrInf<msgType>
{
  public abstract int processFortexConnectionMsg(FortexConnection paramFortexConnection, msgType parammsgType);
  
  public abstract void onClosed(FortexConnection paramFortexConnection);
}


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FortexConnectionMgrInf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */