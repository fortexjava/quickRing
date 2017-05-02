package com.fortex.lib.connection;

import org.glassfish.grizzly.filterchain.FilterChainContext;

public abstract interface FlowControlInf
{
  public abstract boolean handleAcceptFilter(FilterChainContext paramFilterChainContext);
  
  public abstract void handleCloseFilter(FilterChainContext paramFilterChainContext);
  
  public abstract void handleRead(FilterChainContext paramFilterChainContext);
}


/* Location:              F:\Projects\quickRing\lib\FortexJavaLib_20160815.jar!\com\fortex\lib\connection\FlowControlInf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */