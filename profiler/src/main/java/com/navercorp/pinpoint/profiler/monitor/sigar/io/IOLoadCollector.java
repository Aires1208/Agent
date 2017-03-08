package com.navercorp.pinpoint.profiler.monitor.sigar.io;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TIOLoad;

/**
 * Created by 10116285 on 16-7-19.
 */
public interface IOLoadCollector extends AgentStatCollector<TIOLoad> {
}
