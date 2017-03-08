package com.navercorp.pinpoint.profiler.monitor.sigar.net;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TNetLoad;

/**
 * Created by 10116285 on 16-7-19.
 */
public interface NetLoadCollector extends AgentStatCollector<TNetLoad> {
}
