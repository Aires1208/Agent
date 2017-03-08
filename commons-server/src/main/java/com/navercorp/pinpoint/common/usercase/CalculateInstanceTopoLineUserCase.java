package com.navercorp.pinpoint.common.usercase;

import com.navercorp.pinpoint.common.bo.SpanBo;
import com.navercorp.pinpoint.common.bo.SpanEventBo;
import com.navercorp.pinpoint.common.service.ServiceTypeRegistryService;
import com.navercorp.pinpoint.common.topo.domain.TopoLine;
import com.navercorp.pinpoint.common.topo.domain.XLink;
import com.navercorp.pinpoint.common.topo.domain.XNode;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.util.TopoServiceUtils;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.navercorp.pinpoint.common.util.TopoServiceUtils.buildVNode;
import static com.navercorp.pinpoint.common.util.TopoServiceUtils.isCacheNode;
import static com.navercorp.pinpoint.common.util.TopoServiceUtils.isEmptyString;

public class CalculateInstanceTopoLineUserCase {
    private List<SpanBo> spanBos;

    private ServiceTypeRegistryService registryService;

    public CalculateInstanceTopoLineUserCase(List<SpanBo> spanBos, ServiceTypeRegistryService registryService) {
        this.registryService = registryService;
        this.spanBos = spanBos;
    }

    public TopoLine execute() {
        List<XNode> xNodes = buildNodeInfo(spanBos);

        List<XLink> xLink = buildLinkInfo(spanBos);

        return new TopoLine(xNodes, xLink);

    }

    private List<XLink> buildLinkInfo(List<SpanBo> spanBos) {
        List<XLink> xlinks = newArrayList();
        for (SpanBo span : spanBos) {
            if (span.isRoot() || span.getParentSpanId() == -1) {
                xlinks.add(new XLink(ServiceType.USER.getName(), span.getAgentId(), 0L, span.getErrCode(), 1L));
            }

            xlinks.addAll(buildLink(span, spanBos));
        }

        return xlinks;
    }

    private List<XLink> buildLink(SpanBo span, List<SpanBo> spans) {
        List<XLink> links = newArrayList();
        Map<Long, SpanBo> spanMap = TopoServiceUtils.buildSpanMap(spans);

        if (span.getSpanEventBoList() == null) {
            return links;
        }

        for (SpanEventBo spanEvent : span.getSpanEventBoList()) {
            ServiceType spanEventType = registryService.findServiceType(spanEvent.getServiceType());
            long spanId = spanEvent.getNextSpanId();
            if (spanMap.get(spanId) != null) {
                SpanBo next = spanMap.get(spanId);

                long elapsed = !spanEvent.isAsync() ? spanEvent.getEndElapsed() - next.getElapsed()
                        : next.getStartTime() - span.getStartTime() - spanEvent.getStartElapsed();

                if (!isCacheNode(spanEventType.getCode())) {
                    XLink xlink = new XLink(span.getAgentId(), next.getAgentId(),
                            elapsed > 0 ? elapsed : 0, next.getErrCode(), 1L);
                    links.add(xlink);
                } else {
                    String cacheName = isEmptyString(spanEvent.getDestinationId()) ? spanEventType.getName()
                            : spanEvent.getDestinationId();

                    XLink toCache = new XLink(span.getAgentId(), cacheName,
                            (elapsed > 0 ? elapsed : 0) / 2, 0, 1L);

                    XLink fromCache = new XLink(cacheName, next.getAgentId(),
                            (elapsed > 0 ? elapsed : 0) / 2, next.getErrCode(), 1L);

                    links.add(toCache);
                    links.add(fromCache);
                }
            } else if (spanMap.get(spanId) == null
                    && !isEmptyString(spanEvent.getDestinationId())
                    && spanEventType.isRecordStatistics()) {
                XLink xlink = new XLink(span.getAgentId(),
                        spanEvent.getDestinationId(),
                        0L, 0L, 1L);
                links.add(xlink);
            }
        }

        return TopoServiceUtils.distinctLink(links);
    }

    private List<XNode> buildNodeInfo(List<SpanBo> spanBos) {
        List<XNode> xnodes = newArrayList();
        for (SpanBo span : spanBos) {
            if (span.isRoot() || span.getParentSpanId() == -1) {
                xnodes.add(new XNode(ServiceType.USER.getName(), ServiceType.USER.getCode(), 0L, 0L, 1L));
            }

            xnodes.addAll(buildVNode(span, spanBos, registryService));

            xnodes.add(new XNode(span.getAgentId(), span.getServiceType(), span.getElapsed(), span.getErrCode(), 1L));
        }
        return TopoServiceUtils.distinctNode(xnodes);
    }
}
