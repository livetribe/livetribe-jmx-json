/**
 *
 * Copyright 2011 (C) The original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.livetribe.jmx.rest.web.domain.jaxb.jmx;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
@XmlRootElement(name = "MBeans")
public class MBeanShortJaxBeans
{
    @XmlElement(name = "MBean")
    public Set<MBeanShortJaxBean> mbeans = new HashSet<MBeanShortJaxBean>();

    public MBeanShortJaxBeans()
    {
    }

    public MBeanShortJaxBeans(Set<MBeanShortJaxBean> mBeans)
    {
        this.mbeans = mBeans;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("MBeansJaxBean [objectName=");
        builder.append(mbeans);
        builder.append("]");
        return builder.toString();
    }

}