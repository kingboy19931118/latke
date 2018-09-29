/*
 * Copyright (c) 2009-2018, b3log.org & hacpai.com
 *
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
package org.b3log.latke.ioc.speaker;

import org.b3log.latke.Latkes;
import org.b3log.latke.ioc.BeanManager;
import org.b3log.latke.ioc.Lifecycle;
import org.b3log.latke.ioc.bean.Bean;
import org.b3log.latke.ioc.config.Configurator;
import org.b3log.latke.ioc.literal.NamedLiteral;
import org.b3log.latke.ioc.speaker.annotation.HelloLiteral;
import org.b3log.latke.ioc.speaker.annotation.MidnightLiteral;
import org.b3log.latke.ioc.speaker.annotation.MorningLiteral;
import org.b3log.latke.ioc.speaker.annotation.NightLiteral;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.4, Sep 29, 2018
 * @since 2.4.18
 */
final public class SpeakerUnitTest {

    /**
     * Bean manager.
     */
    private BeanManager beanManager;

    public static final List<Class<?>> speakerPackageClasses = Arrays.asList(MorningSpeaker.class);

    private static Speaker helloSpeaker;

    private static Speaker morningSpeaker;

    @BeforeTest
    @SuppressWarnings("unchecked")
    public void beforeTest() {
        System.out.println("before SpeakerUnitTest");

        Latkes.initRuntimeEnv();
        beanManager = BeanManager.getInstance();
        Lifecycle.startApplication(speakerPackageClasses);

        final Configurator configurator = beanManager.getConfigurator();

        // Create beans by APIs approach
        configurator.createBean(HelloSpeaker.class).qualified(new HelloLiteral());
        configurator.createBean(NightSpeaker.class).qualified(new NightLiteral());
        configurator.createBean(MidnightSpeaker.class).qualified(new MidnightLiteral());

        final Set<Annotation> helloSpeakerQualifiers = new HashSet<>();
        helloSpeakerQualifiers.add(new HelloLiteral());
        helloSpeakerQualifiers.add(new NamedLiteral("helloSpeaker"));
        final Bean<?> helloSpeakerBean = beanManager.getBean(Speaker.class, helloSpeakerQualifiers);
        helloSpeaker = (HelloSpeaker) beanManager.getReference(helloSpeakerBean);
        assertNotNull(helloSpeaker);

        configurator.validate();

        final Set<Annotation> morningSpeakerQualifiers = new HashSet<>();
        morningSpeakerQualifiers.add(new MorningLiteral());
        morningSpeakerQualifiers.add(new NamedLiteral("morningSpeaker"));
        final Bean<?> morningSpeakerBean = beanManager.getBean(Speaker.class, morningSpeakerQualifiers);
        morningSpeaker = (MorningSpeaker) beanManager.getReference(morningSpeakerBean);
        assertNotNull(morningSpeaker);
    }

    /**
     * This method will be run after the test. Shutdown Latke IoC container.
     */
    @AfterTest
    public void afterTest() {
        System.out.println("afterTest SpeakerUnitTest");
        Lifecycle.endApplication();
    }

    @Test
    public void helloSpeakerSay() {
        System.out.println("helloSpeakerSay");
        assertEquals(helloSpeaker.say(), "Hello!");
    }

    @Test
    public void morningSpeakerSay() {
        System.out.println("morningSpeakerSay");
        assertEquals(morningSpeaker.say(), "Morning!");
    }
}
