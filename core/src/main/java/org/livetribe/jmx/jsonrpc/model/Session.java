/**
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
package org.livetribe.jmx.jsonrpc.model;

import javax.management.ObjectName;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * @author Alan D. Cabrera
 */
public class Session
{
    private int sessionId;
    private int inactivityTimeout;
    private int pollingTimeout;
    private int maxNotifications;
    private int calls;
    private Set<ObjectName> listeners = new HashSet<ObjectName>();
    @JsonIgnore Map<ObjectName, Integer> count = new HashMap<ObjectName, Integer>();
    @JsonIgnore private Future future;

    public Session()
    {
    }

    public Session(int sessionId, int inactivityTimeout, int pollingTimeout, int maxNotifications, Future future)
    {
        assert future != null;

        this.sessionId = sessionId;
        this.inactivityTimeout = inactivityTimeout;
        this.pollingTimeout = pollingTimeout;
        this.maxNotifications = maxNotifications;
        this.future = future;
    }

    public int getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(int sessionId)
    {
        this.sessionId = sessionId;
    }

    public int getInactivityTimeout()
    {
        return inactivityTimeout;
    }

    public void setInactivityTimeout(int inactivityTimeout)
    {
        this.inactivityTimeout = inactivityTimeout;
    }

    public int getPollingTimeout()
    {
        return pollingTimeout;
    }

    public void setPollingTimeout(int pollingTimeout)
    {
        this.pollingTimeout = pollingTimeout;
    }

    public int getMaxNotifications()
    {
        return maxNotifications;
    }

    public void setMaxNotifications(int maxNotifications)
    {
        this.maxNotifications = maxNotifications;
    }

    public int getCalls()
    {
        return calls;
    }

    public void setCalls(int calls)
    {
        this.calls = calls;
    }

    public Set<ObjectName> getListeners()
    {
        return listeners;
    }

    public void setListeners(Set<ObjectName> listeners)
    {
        this.listeners = listeners;

        for (ObjectName name : listeners) increment(name);
    }

    public void increment(ObjectName name)
    {
        if (count.containsKey(name))
        {
            count.put(name, count.get(name) + 1);
        }
        else
        {
            count.put(name, 1);
            listeners.add(name);
        }
    }

    public void decrement(ObjectName name)
    {
        if (count.containsKey(name))
        {
            int previous = count.put(name, count.get(name) - 1);
            if (previous == 1)
            {
                count.remove(name);
                listeners.remove(name);
            }
        }
    }

    public Future getFuture()
    {
        return future;
    }

    public void setFuture(Future future)
    {
        this.future = future;
    }
}
