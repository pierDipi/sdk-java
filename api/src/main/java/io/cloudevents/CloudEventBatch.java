package io.cloudevents;

import java.util.Collection;
import java.util.Iterator;

/**
 * Interface representing an in memory read only representation of a CloudEvent batch.
 */
public interface CloudEventBatch extends Collection<CloudEvent> {


    static CloudEventBatch from(Collection<CloudEvent> events) {
        return new CloudEventBatch() {
            @Override
            public int size() {
                return events.size();
            }

            @Override
            public boolean isEmpty() {
                return events.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return events.contains(o);
            }

            @Override
            public Iterator<CloudEvent> iterator() {
                return events.iterator();
            }

            @Override
            public Object[] toArray() {
                return events.toArray();
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return events.toArray(a);
            }

            @Override
            public boolean add(CloudEvent cloudEvent) {
                return events.add(cloudEvent);
            }

            @Override
            public boolean remove(Object o) {
                return events.remove(o);
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return events.containsAll(c);
            }

            @Override
            public boolean addAll(Collection<? extends CloudEvent> c) {
                return events.addAll(c);
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return events.removeAll(c);
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return events.retainAll(c);
            }

            @Override
            public void clear() {
                events.clear();
            }
        };
    }
}
