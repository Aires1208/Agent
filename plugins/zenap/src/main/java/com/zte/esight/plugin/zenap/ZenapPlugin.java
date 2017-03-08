package com.zte.esight.plugin.zenap;

import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;
import com.zte.esight.plugin.zenap.fm.FmPlugin;

/**
 * Created by 10116285 on 16-7-21.
 */
public class ZenapPlugin implements ProfilerPlugin, TransformTemplateAware {
    private TransformTemplate transformTemplate;
    
    @Override
    public void setup(ProfilerPluginSetupContext context) {
        setupFmPlugin(context);
    }

    private void setupFmPlugin(ProfilerPluginSetupContext context) {
        new FmPlugin(transformTemplate).setup(context);
    }

    @Override
    public void setTransformTemplate(TransformTemplate transformTemplate) {
        this.transformTemplate = transformTemplate;
    }
}
