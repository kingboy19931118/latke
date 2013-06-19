/*
 * Copyright (c) 2009, 2010, 2011, 2012, 2013, B3log Team
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
package org.b3log.latke.ioc;


import org.b3log.latke.ioc.bean.LatkeBean;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;
import org.b3log.latke.ioc.config.Configurator;


/**
 * Latke bean manager.
 *
 * @author <a href="mailto:DL88250@gmail.com">Liang Ding</a>
 * @version 1.0.0.1, Apr 2, 2010
 */
public interface LatkeBeanManager extends BeanManager {

    /**
     * Gets the bean configurator.
     * 
     * @return bean configurator
     */
    Configurator getConfigurator();

    /**
     * Gets a bean with the specified bean type and qualifiers.
     * 
     * @param beanType the specified bean type
     * @param qualifiers the specified qualifiers
     * @return bean
     */
    LatkeBean<?> getBean(final Type beanType, final Set<Annotation> qualifiers);

    /**
     * Gets a bean with the specified bean class.
     * 
     * @param beanClass the specified bean class
     * @return bean
     */
    LatkeBean<?> getBean(final Class<?> beanClass);

    /**
     * Gets a bean with the specified name.
     * 
     * @param name the specified name
     * @return bean
     */
    LatkeBean<?> getBean(final String name);

    /**
     * Gets a reference of the specified bean.
     * 
     * @param bean the specified bean
     * @return reference
     */
    Object getReference(final LatkeBean<?> bean);

    /**
     * Gets a reference of the specified bean and creational context.
     * 
     * @param bean the specified bean
     * @param creationalContext the specified creational context
     * @return reference
     */
    Object getReference(final LatkeBean<?> bean, final CreationalContext<?> creationalContext);

    /**
     * Adds the specified bean.
     * 
     * @param bean the specified bean
     */
    void addBean(final LatkeBean<?> bean);

    /**
     * Gets all beans.
     * 
     * @return beans
     */
    Set<LatkeBean<?>> getBeans();

    /**
     * Adds the specified context.
     * 
     * @param context the specified context
     */
    void addContext(final Context context);

    /**
     * Clears all contexts.
     */
    void clearContexts();
}
