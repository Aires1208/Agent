package com.navercorp.pinpoint.common.usercase;

import com.navercorp.pinpoint.common.bo.SpanBo;
import com.navercorp.pinpoint.common.bo.SpanEventBo;
import com.navercorp.pinpoint.common.service.DefaultServiceTypeRegistryService;
import com.navercorp.pinpoint.common.service.ServiceTypeRegistryService;
import com.navercorp.pinpoint.common.topo.domain.*;
import com.navercorp.pinpoint.common.trace.ServiceType;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;


public class CalculateTopoLineUserCaseTest {

    private static int ERROR_FLAG = 1;

    ServiceTypeRegistryService registryService = new DefaultServiceTypeRegistryService();

    private List<SpanBo> getInputSpans() {

        SpanEventBo spanEvent1 = new SpanEventBoBuilder()
                .NextSpanId(2).Destination("EMS_main").endElapsed(1300).build();
        SpanBo spanBo1 = new SpanBoBuilder()
                .SpanId(1L)
                .Service("EMS_client")
                .ServiceType(ServiceType.TEST.getCode())
                .Elapsed(2000)
                .SpanEvent(spanEvent1)
                .build();

        SpanEventBo spanEvent2 = new SpanEventBoBuilder()
                .NextSpanId(3).Destination("Minos_oracle").ServiceType(ServiceType.UNKNOWN_DB.getCode()).build();
        SpanEventBo spanEvent3 = new SpanEventBoBuilder()
                .NextSpanId(9).Destination("Minos_oracle").ServiceType(ServiceType.UNKNOWN_DB.getCode()).build();
        SpanEventBo spanEvent4 = new SpanEventBoBuilder()
                .NextSpanId(-1).Destination("DB").ServiceType(ServiceType.UNKNOWN_DB_EXECUTE_QUERY.getCode()).build();
        SpanBo spanBo2 = new SpanBoBuilder()
                .ParentSpanId(1L)
                .SpanId(2)
                .Service("EMS_main")
                .ServiceType(ServiceType.TEST.getCode())
                .Elapsed(1000)
                .ErrorCode(ERROR_FLAG)
                .SpanEvent(spanEvent2)
                .SpanEvent(spanEvent3)
                .SpanEvent(spanEvent4)
                .build();

        return newArrayList(spanBo1, spanBo2);
    }

    @Test
    public void should_return_expected_nodes_when_input_custom_spans() throws Exception {
        //given
        Set<XNode> exceptedNodes = newHashSet(Node("USER")
                , Node("EMS_client")
                , Node("EMS_main")
//                ,Node("Minos_oracle")
                , Node("DB"));

        List<SpanBo> spanBos = getInputSpans();

        //when
        TopoLine topoLine = new CalculateTopoLineUserCase(spanBos, registryService).execute();
        List<XNode> xNodes = topoLine.getXNodes();

        //then
        assertThat(newHashSet(topoLine.getXNodes()), is(exceptedNodes));
    }

    @Test
    public void should_return_expected_links_when_input_custom_spans() throws Exception {
        //given
        Set<XLink> exceptedLinks = newHashSet(Link("USER", "EMS_client")
                , Link("EMS_client", "EMS_main")
//                ,Link("EMS_main","Minos_oracle")
                , Link("EMS_main", "DB"));

        List<SpanBo> spanBos = getInputSpans();

        //when
        TopoLine topoLine = new CalculateTopoLineUserCase(spanBos, registryService).execute();

        //then
        assertThat(newHashSet(topoLine.getXLinks()), is(exceptedLinks));
    }

    @Test
    public void should_return_expected_nodes_meTrics_when_input_custom_spans() throws Exception {
        //given
        XNode xNode1 = new XNodeBuilder().Name("USER").ServiceType(ServiceType.USER.getCode()).build();
        XNode xNode2 = new XNodeBuilder().Name("EMS_client").ServiceType(ServiceType.TEST.getCode()).Calls(1).Response(2000).build();
        XNode xNode3 = new XNodeBuilder().Name("EMS_main").ServiceType(ServiceType.TEST.getCode()).Calls(1).Errors(1).Response(1000).build();
//        XNode xNode4 = new XNodeBuilder().Name("Minos_oracle").ServiceType(ServiceType.UNKNOWN_DB.getCode()).Calls(2).build();
        XNode xNode5 = new XNodeBuilder().Name("DB").ServiceType(ServiceType.UNKNOWN_DB_EXECUTE_QUERY.getCode()).Calls(0).build();

        List<XNode> exceptedNodes = newArrayList(xNode1, xNode2, xNode3/*,xNode4*/, xNode5);

        List<SpanBo> spanBos = getInputSpans();

        //when
        TopoLine topoLine = new CalculateTopoLineUserCase(spanBos, registryService).execute();

        //then
        AssertNodeMetricsEquals(topoLine.getXNodes(), exceptedNodes);
    }

    @Test
    public void should_return_expected_links_metrics_when_input_custom_spans() throws Exception {
        //given
        XLink xLink1 = new XLinkBuilder().Link("USER", "EMS_client").Calls(1).build();
        XLink xLink2 = new XLinkBuilder().Link("EMS_client", "EMS_main").Calls(1).Errors(1).Response(300).build();
//        XLink xLink3 = new XLinkBuilder().Link("EMS_main","Minos_oracle").Calls(2).build();
        XLink xLink4 = new XLinkBuilder().Link("EMS_main", "DB").Calls(1).build();

        List<XLink> exceptedLinks = newArrayList(xLink1, xLink2/*,xLink3*/, xLink4);

        List<SpanBo> spanBos = getInputSpans();

        //when
        TopoLine topoLine = new CalculateTopoLineUserCase(spanBos, registryService).execute();

        //then
        AssertLinkMetricsEquals(topoLine.getXLinks(), exceptedLinks);
    }

    @Test
    public void should_add_cacheNode_when_input_given_spans() throws Exception {
        List<SpanBo> spanBos = getCacheSpans();

        //when
        TopoLine topoLine = new CalculateTopoLineUserCase(spanBos, registryService).execute();

        System.out.println(topoLine);
    }

    private List<SpanBo> getCacheSpans() {
        SpanEventBo eventBo1 = new SpanEventBoBuilder().Destination("kafka")
                .NextSpanId(345L)
                .ServiceType((short) 8350).build();

        SpanBo span1 = new SpanBoBuilder().ParentSpanId(-1L)
                .AgentId("agent1")
                .Service("app_svc1")
                .ServiceType(ServiceType.SPRING.getCode())
                .SpanId(123L)
                .Elapsed(23)
                .ErrorCode(0)
                .SpanEvent(eventBo1).build();

        SpanBo spanBo2 = new SpanBoBuilder().SpanId(345L)
                .ParentSpanId(123L)
                .AgentId("agent2")
                .Service("app_svc2")
                .ServiceType((short) 8350)
                .Elapsed(2222)
                .ErrorCode(1).build();
        return newArrayList(span1, spanBo2);
    }

    private XNode Node(String nodeName) {
        return new XNode(nodeName);
    }

    private XLink Link(String from, String to) {
        return new XLink(from, to);
    }

    private void AssertNodeMetricsEquals(List<XNode> srcNodes, List<XNode> destNodes) {

        Map<XNode, XNode> xNodeMap = newHashMap();
        for (XNode xNode : srcNodes) {
            xNodeMap.put(xNode, xNode);
        }

        for (XNode xNode : destNodes) {
            XNode curXNode = xNodeMap.get(xNode);
            assertThat(curXNode.getCalls(), is(xNode.getCalls()));
            assertThat(curXNode.getErrors(), is(xNode.getErrors()));
            assertThat(curXNode.getResponseTime(), is(xNode.getResponseTime()));
        }

        assertEquals(srcNodes.size(), destNodes.size());
    }

    private void AssertLinkMetricsEquals(List<XLink> srcLinks, List<XLink> destLinks) {

        Map<XLink, XLink> xLinkMap = newHashMap();
        for (XLink xLink : srcLinks) {
            xLinkMap.put(xLink, xLink);
        }

        for (XLink xLink : destLinks) {
            XLink curXLink = xLinkMap.get(xLink);
            assertThat(curXLink.getCalls(), is(xLink.getCalls()));
            assertThat(curXLink.getErrors(), is(xLink.getErrors()));
            assertThat(curXLink.getResponseTime(), is(xLink.getResponseTime()));
        }

        assertEquals(srcLinks.size(), destLinks.size());
    }

}