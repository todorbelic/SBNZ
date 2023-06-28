package com.bank.sbnz.config;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {
    private static final String drlFile = "rules/fraud.drl";
    private static final String drlFile2 = "rules/credit_approval.drl";


    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile2));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession kieSession() {
        KieContainer kieContainer = kieContainer();
        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();

        KieBaseConfiguration kieBaseConfig = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfig.setOption(EventProcessingOption.STREAM); // Enable event stream processing

        KieBase kieBase = kieContainer.newKieBase(kieBaseConfig);
        KieSession kieSession = kieBase.newKieSession(config, null);

        return kieSession;
    }

    @Bean // Specify the bean name
    public EntryPoint myEventStream(KieSession kieSession) {
        return kieSession.getEntryPoint("MyEventStream"); // Specify the entry point name
    }

}