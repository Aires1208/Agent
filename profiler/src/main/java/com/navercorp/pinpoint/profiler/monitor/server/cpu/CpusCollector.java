package com.navercorp.pinpoint.profiler.monitor.server.cpu;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TCpus;

public interface CpusCollector extends AgentStatCollector<TCpus> {

}
