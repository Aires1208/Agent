package com.navercorp.pinpoint.profiler.monitor.server.net;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TMemInfo;
import com.navercorp.pinpoint.thrift.dto.TNets;

public interface NetsCollector extends AgentStatCollector<TNets> {

}
