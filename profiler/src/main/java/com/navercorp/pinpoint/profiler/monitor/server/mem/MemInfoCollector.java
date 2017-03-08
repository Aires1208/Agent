package com.navercorp.pinpoint.profiler.monitor.server.mem;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TMemInfo;

public interface MemInfoCollector extends AgentStatCollector<TMemInfo> {

}
