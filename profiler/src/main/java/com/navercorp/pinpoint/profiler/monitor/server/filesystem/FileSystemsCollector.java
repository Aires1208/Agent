package com.navercorp.pinpoint.profiler.monitor.server.filesystem;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TFileSystems;

public interface FileSystemsCollector extends AgentStatCollector<TFileSystems> {

}
