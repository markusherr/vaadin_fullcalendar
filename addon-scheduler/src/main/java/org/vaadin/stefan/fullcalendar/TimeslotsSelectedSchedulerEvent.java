/*
 * Copyright 2020, Stefan Uebe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.vaadin.stefan.fullcalendar;

import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import lombok.ToString;

import java.util.Optional;

/**
 * This type extends the normal timeslot selected event by providing the selected resource (if there's one).
 * <br><br>
 * Client side event: select
 */
@DomEvent("select")
@ToString(callSuper = true)
public class TimeslotsSelectedSchedulerEvent extends TimeslotsSelectedEvent {

    private Resource resource;

    /**
     * New instance. Awaits the selected dates (time) as iso string (e.g. "2018-10-23" or "2018-10-23T13:30").
     *
     * @param source     the source component
     * @param fromClient <code>true</code> if the event originated from the client
     * @param start      start time slot as iso string
     * @param end        end time slot as iso string
     * @param allDay    all day event
     * @param resourceId optional resource id
     */
    public TimeslotsSelectedSchedulerEvent(FullCalendarScheduler source, boolean fromClient, @EventData("event.detail.start") String start, @EventData("event.detail.end") String end, @EventData("event.detail.allDay") boolean allDay, @EventData("event.detail.resource") String resourceId) {
        super(source, fromClient, start, end, allDay);

        if (resourceId != null) {
            this.resource = source.getResourceById(resourceId).orElseThrow(IllegalArgumentException::new);
        }
    }

    /**
     * If the select event has occured in the scheduler, the selected resource will be returned.
     *
     * @return resource
     */
    public Optional<Resource> getResource() {
        return Optional.ofNullable(resource);
    }


}
