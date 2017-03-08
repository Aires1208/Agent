package com.navercorp.pinpoint.common.util;

import com.navercorp.pinpoint.common.bo.SpanBo;
import com.navercorp.pinpoint.common.bo.SpanEventBo;
import com.navercorp.pinpoint.common.service.ServiceTypeRegistryService;
import com.navercorp.pinpoint.common.topo.domain.XLink;
import com.navercorp.pinpoint.common.topo.domain.XNode;
import com.navercorp.pinpoint.common.trace.ServiceType;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

/**
 * Created by root on 16-9-13.
 */
public class TopoServiceUtils {
    public static Map<Long, SpanBo> buildSpanMap(List<SpanBo> spanBos) {
        Map<Long, SpanBo> spanBoMap = newHashMap();
        for (SpanBo spanBo : spanBos) {
            spanBoMap.put(spanBo.getSpanId(), spanBo);
        }
        return spanBoMap;
    }

    public static List<XNode> buildVNode(SpanBo span, List<SpanBo> spans, ServiceTypeRegistryService registryService) {
        List<XNode> xnodes = newArrayList();

        if (getDestinationList(span).isEmpty()) {
            return xnodes;
        }

        Set<Long> spanIds = newHashSet();
        for (SpanBo spanBo : spans) {
            spanIds.add(spanBo.getSpanId());
        }

        for (SpanEventBo spanEvent : span.getSpanEventBoList()) {
            ServiceType spanEventType = registryService.findServiceType(spanEvent.getServiceType());
            if ((!spanIds.contains(spanEvent.getNextSpanId())
                    && spanEventType.isRecordStatistics())
                    || (spanIds.contains(spanEvent.getNextSpanId())
                    && isCacheNode(spanEvent.getServiceType()))) {
                String nodeName = isEmptyString(spanEvent.getDestinationId()) ? spanEventType.getName() : spanEvent.getDestinationId();
                xnodes.add(new XNode(nodeName, spanEventType.getCode(), 0L, 0L, 0L));
            }
        }

        return xnodes;
    }

    public static boolean isCacheNode(short code) {
        return 8 == code / 1000;
    }

    public static boolean isEmptyString(String destinationId) {
        return null == destinationId || "".equals(destinationId);
    }

    private static List<String> getDestinationList(SpanBo span) {
        List<String> destinations = newArrayList();

        if (isEmpty(span.getSpanEventBoList())) {
            return destinations;
        }

        for (SpanEventBo spenEventBo : span.getSpanEventBoList()) {
            if (!isEmptyString(spenEventBo.getDestinationId())) {
                destinations.add(spenEventBo.getDestinationId());
            }
        }

        return destinations;
    }

    private static boolean isEmpty(List<?> list) {
        return null == list || list.isEmpty();
    }

    public static List<XLink> distinctLink(List<XLink> xlinks) {
        Map<String, XLink> linkMap = newHashMap();

        for (XLink xLink : xlinks) {
            XLink tmpLink = linkMap.get(xLink.getFrom() + xLink.getTo());
            if (tmpLink != null) {
                tmpLink.setCalls(tmpLink.getCalls() + 1L);
                tmpLink.setErrors(tmpLink.getErrors() + xLink.getErrors());
                tmpLink.setResponseTime(tmpLink.getResponseTime() + xLink.getResponseTime());

                linkMap.put(xLink.getFrom() + xLink.getTo(), tmpLink);
            } else {
                linkMap.put(xLink.getFrom() + xLink.getTo(), xLink);
            }
        }

        List<XLink> xLinkList = newArrayList();
        for (Map.Entry<String, XLink> entry : linkMap.entrySet()) {
            xLinkList.add(entry.getValue());
        }

        return xLinkList;
    }

    public static List<XNode> distinctNode(List<XNode> xnodes) {
        Map<String, XNode> nodeMap = newHashMap();
        for (XNode xNode : xnodes) {
            XNode tmpNode = nodeMap.get(xNode.getName());
            if (tmpNode != null) {
                tmpNode.setCalls(tmpNode.getCalls() + 1L);
                tmpNode.setErrors(tmpNode.getErrors() + xNode.getErrors());
                tmpNode.setResponseTime(tmpNode.getResponseTime() + xNode.getResponseTime());

                nodeMap.put(tmpNode.getName(), tmpNode);
            } else {
                nodeMap.put(xNode.getName(), xNode);
            }
        }

        List<XNode> xNodes = newArrayList();
        for (Map.Entry<String, XNode> entry : nodeMap.entrySet()) {
            xNodes.add(entry.getValue());
        }

        return xNodes;
    }
}
