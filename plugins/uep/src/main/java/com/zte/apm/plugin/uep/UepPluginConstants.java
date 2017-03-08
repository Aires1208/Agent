/**
 * Copyright 2014 NAVER Corp.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zte.apm.plugin.uep;

import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.AnnotationKeyFactory;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeFactory;
import com.navercorp.pinpoint.common.trace.ServiceTypeProperty;

/**
 * @author Jongho Moon
 *
 */
public interface UepPluginConstants {
    String SCOPE_UEP = "SCOPE_UEP";

    ServiceType UEP_SERVER = ServiceTypeFactory.of(1900, "UEP SERVER", ServiceTypeProperty.RECORD_STATISTICS);
    ServiceType UEP_CLIENT = ServiceTypeFactory.of(9900, "UEP CLIENT", ServiceTypeProperty.RECORD_STATISTICS);
    ServiceType MINOS_SERVER = ServiceTypeFactory.of(9901, "MINOS SERVER", ServiceTypeProperty.INCLUDE_DESTINATION_ID);

    AnnotationKey UEP_EMB_RESULT_ANNOTATION_KEY = AnnotationKeyFactory.of(997, "UEP_EMB_RESULT");

    String META_DO_NOT_TRACE = "_UEP_DO_NOT_TRACE";
    String META_TRANSACTION_ID = "_UEP_TRANSACTION_ID";
    String META_SPAN_ID = "_UEP_SPAN_ID";
    String META_PARENT_SPAN_ID = "_UEP_PARENT_SPAN_ID";
    String META_PARENT_APPLICATION_NAME = "_UEP_PARENT_APPLICATION_NAME";
    String META_PARENT_APPLICATION_TYPE = "_UEP_PARENT_APPLICATION_TYPE";
    String META_FLAGS = "_UEP_FLAGS";

    String META_F_SERVERNAME = "_UEP_SERVERNAME";
    String META_F_CLIENTNAME = "_UEP_CLIENTNAME";
}
