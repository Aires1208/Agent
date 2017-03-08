package com.navercorp.pinpoint.profiler.monitor.sigar.mem;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TMemLoad;

/**
 * Created by 10116285 on 16-7-29.
 */
public interface MemLoadCollector extends AgentStatCollector<TMemLoad> {

}
